package com.eddapps.handy;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;

import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.cameras.OrthographicCamera;
import com.eddapps.handy.engine.objects.sprite.Sprite;
import com.eddapps.handy.engine.opengl.shader.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shader.programs.ShaderProgram;
import com.eddapps.handy.engine.opengl.texture.TextureManager;
import com.eddapps.handy.engine.utils.Clock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements Renderer {

    private static final String TAG = MyGLRenderer.class.getSimpleName();

    private int _width;
    private int _height;

    private Handler handler = new Handler();

    private Camera mCamera;
    private ObjectManager mObjectManager;
    private TextureManager mTextureManager;
    private ShaderProgramManager mShaderProgramManager;

    // Misc
    int frames = 0;
    Context mContext;
    long _currentTime = 0, _prevTime = 0;
    long frameTime;

    public MyGLRenderer(Context c){
        mContext = c;
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        _width = metrics.widthPixels;
        _height = metrics.heightPixels;
    }

    private Runnable runnable = new Runnable()
    {

        public void run()
        {
            Log.d(TAG, frames + "FPS with " + mObjectManager.getNumObjects() + " objects");
            frames = 0;
            handler.postDelayed(this, 1000);
        }
    };

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
        Render();
        frames++;
    }


    private void Render() {
        // Clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        // Bind ViewMatrix and ProjectionMatrix to the
        Clock.update();
        mCamera.update();
        mObjectManager.update();
        mTextureManager.loadUnloadedTextures(mContext);
        ShaderProgramManager.getPositionColorTextureShader().setViewMatrix(mCamera.getViewMatrixArray());
        ShaderProgramManager.getPositionColorTextureShader().setProjectionMatrix(mCamera.getProjectionMatrixArray());
        mObjectManager.draw();
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        _width = width;
        _height = height;
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        mCamera.onScreenReshape(_width, _height);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        //Fase 1: Add objects to the scene
        mCamera = new OrthographicCamera(_width, _height);
        mObjectManager = new ObjectManager();
        mTextureManager = new TextureManager();
        ShaderProgramManager.init();
        for(int i = 0; i < 600; i++) {
            Sprite sprite = new Sprite();
            sprite.setTexture(mTextureManager, R.drawable.ic_launcher);
            mObjectManager.addObject(new Sprite());
        }
        Clock.init();
        ShaderProgramManager.init();
        runnable.run();
    }

    public void addObject(){

    }

    public void moveCamera(float dx, float dy) {
        mCamera.translate(-dx * Clock.getDelta() / 5, dy * Clock.getDelta() / 5);
    }
}
