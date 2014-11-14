package com.eddapps.handy;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.util.DisplayMetrics;

import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.cameras.OrthographicCamera;
import com.eddapps.handy.engine.objects.primitives.Primitive;
import com.eddapps.handy.engine.objects.primitives.Quad;
import com.eddapps.handy.engine.objects.primitives.Triangle;
import com.eddapps.handy.engine.objects.sprite.Sprite;
import com.eddapps.handy.engine.opengl.shaders.ShaderProgramManager;
import com.eddapps.handy.engine.utils.Clock;
import com.eddapps.handy.engine.utils.FPSCounter;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements Renderer {

    private int _width;
    private int _height;

    private FPSCounter _fpsCounter;
    private ObjectManager _objectManager;
    private Camera _camera;
    private Primitive mPrimitive;

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
        _fpsCounter.addFrameTime(frameTime);
        //Log.d("", "Objects = " + _objectManager.getNumObjects() + " | time = " + _fpsCounter.getAverage());
    }

    private void Render() {
        // Clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // Bind ViewMatrix and ProjectionMatrix to the
        Clock.update();
        _camera.update();
        ShaderProgramManager.getPositionColorShader().setViewMatrix(_camera.getViewMatrixArray());
        ShaderProgramManager.getPositionColorShader().setProjectionMatrix(_camera.getProjectionMatrixArray());
        mPrimitive.update();
        mPrimitive.draw();
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
        _camera = new OrthographicCamera(_width, _height);
        Clock.init();
        ShaderProgramManager.init();
        _fpsCounter = new FPSCounter(30);
        mPrimitive = new Triangle();
    }

    public void addObject(){

    }

    public void moveCamera(float dx, float dy) {
        _camera.translate(-dx * Clock.getDelta() / 5, dy * Clock.getDelta() / 5);
    }
}
