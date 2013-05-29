package com.livewallpaper.batterywallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Patrick Rabier on 29/05/13.
 */
public class BatteryDisplay {

    private int width, height;

    public BatteryDisplay()
    {
        width = 0;
        height = 0;
    }

    public void     update(int elapsed_ms)
    {

    }

    public void     draw(Canvas canvas)
    {
        if (canvas == null)
            return;

        width = canvas.getWidth();
        height = canvas.getHeight();

        // DRAW
        Paint myPaint = new Paint();
        myPaint.setColor(Color.rgb(255, 0, 0));
        myPaint.setStrokeWidth(10);
        canvas.drawRect(100, 100, 200, 200, myPaint);
    }


}
