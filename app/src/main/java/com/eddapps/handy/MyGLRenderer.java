package com.eddapps.handy;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Debug;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;

public class MyGLRenderer implements Renderer {

    private int _width;
    private int _height;

    private ObjectManager _objectManager;
    private Camera _camera;

    // Misc
    Context mContext;
    long _currentTime = 0, _prevTime = 0;
    long frameTime;
    int frames = 0;

    public MyGLRenderer(Context c){
        mContext = c;
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        _width = metrics.widthPixels;
        _height = metrics.heightPixels;
    }

    public void onPause()
    {
		/* Do stuff to pause the renderer */
    }

    public void onResume()
    {
		/* Do stuff to resume the renderer */
        _currentTime = SystemClock.elapsedRealtime();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        _prevTime = SystemClock.elapsedRealtime();
        Render();
        _currentTime = SystemClock.elapsedRealtime();
        frameTime = _currentTime - _prevTime;
        Log.d("", "Objects = " + _objectManager.getNumObjects() + " | time = " + frameTime);
    }

    private void Render() {
        // Clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // Bind ViewMatrix and ProjectionMatrix to the
        Clock.update();
        _camera.bindMatrices();
        _objectManager.update(_camera);
        _objectManager.draw();

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        _width = width;
        _height = height;
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        _camera.onScreenReshape(_width, _height);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        //Fase 1: Add objects to the scene
        _camera = new Camera(_width, _height);
        Clock.init();
        ShaderProgramManager.init();

        _objectManager = new ObjectManager();
        for (int i = 0; i < 100; i++)
            _objectManager.addObject(new Sprite());
    }

    public void addObject(){
        _objectManager.addObject(new Sprite());
    }

    public void scaleObjects(float x){
        _objectManager.scaleAllObjects(x);
    }
}
