package com.eddapps.handy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by Edgar on 09/07/2014.
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private float mPreviousX, mPreviousY;
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new MyGLRenderer(context);
        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        super.onPause();
        mRenderer.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRenderer.onResume();
    }

    @SuppressLint("ClickableViewAccessibility") @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                mRenderer.moveCamera(dx, dy);
        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}
