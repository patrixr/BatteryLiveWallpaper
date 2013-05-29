package com.livewallpaper.batterywallpaper;

import android.graphics.Canvas;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import java.lang.Override;

public class BatteryWallpaperService extends WallpaperService
{

    @Override
    public Engine onCreateEngine()
    {
        return new BatteryWallpaperEngine();
    }

    private class BatteryWallpaperEngine extends Engine {

        private BatteryDisplay myDisplay = new BatteryDisplay();
        private boolean mVisible = false;
        private final Handler mHandler = new Handler();
        private final Runnable mUpdateDisplay = new Runnable() {
            public void run() {
                draw();
            }
        };

        public BatteryWallpaperEngine() {
            super();
        }

        private void draw() {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas c = null;
            try {
                c = holder.lockCanvas();

                myDisplay.update(100);
                myDisplay.draw(c);

            } finally {
                if (c != null)
                    holder.unlockCanvasAndPost(c);
            }
            mHandler.removeCallbacks(mUpdateDisplay);
            if (mVisible)
                mHandler.postDelayed(mUpdateDisplay, 100);
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            mVisible = visible;
            if (visible) {
                draw();
            } else {
                mHandler.removeCallbacks(mUpdateDisplay);
            }
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder,
                                     int format, int width, int height) {
            draw();
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            mVisible = false;
            mHandler.removeCallbacks(mUpdateDisplay);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mVisible = false;
            mHandler.removeCallbacks(mUpdateDisplay);
        }
    }
}