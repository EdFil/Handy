package com.eddapps.handy;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;

import com.eddapps.handy.engine.Engine;
import com.eddapps.handy.engine.cameras.OrthographicCamera;
import com.eddapps.handy.engine.objects.primitive.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.objects.primitive.vbo.TriangleVertexBufferObject;
import com.eddapps.handy.engine.objects.sprite.Sprite;
import com.eddapps.handy.engine.objects.sprite.vbo.SpriteVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.texture.TextureManager;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObjectManager;
import com.eddapps.handy.engine.utils.Clock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements Renderer {

    private static final String TAG = MyGLRenderer.class.getSimpleName();
    private Engine mEngine;

    // Misc
    Context mContext;

    public MyGLRenderer(Context c){
        mContext = c;
        mEngine = new Engine();
    }

    //Is called by SurfaceView
    public void onPause(){
        Log.d(TAG, "onPause was called.");
		//mEngine.pause();
    }

    //Is called by SurfaceView
    public void onResume(){
        Log.d(TAG, "onResume was called.");
		//mEngine.resume();
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        mEngine.onDrawFrame();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged was called.");
        mEngine.setSurfaceDimensions(width, height);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
       Log.d(TAG, "onSurfaceCreated was called.");
       mEngine.onSurfaceCreated();
    }
}
