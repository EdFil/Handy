package com.eddapps.handy.engine;

import android.opengl.GLES20;

import com.eddapps.handy.Scene;
import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.opengl.texture.TextureManager;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by edgar on 21-11-2014.
 */
public class Engine {

    private static final String TAG = Engine.class.getSimpleName();

    //Surface data
    private int mSurfaceWidth;
    private int mSurfaceHeight;

    private Camera camera;
    private Scene mScene;
    private TextureManager mTextureManager;
    private VertexBufferObjectManager mVertexObjectManager;

    public Engine(){
        mScene = new Scene(this);
        mTextureManager = new TextureManager();
        mVertexObjectManager = new VertexBufferObjectManager();
    }

    public void onDrawFrame(){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.1f,0.3f,0.6f,1.0f);
    }

    public void onSurfaceCreated(){
        //Stuff to reload engine
    }

    //Getters
    public final VertexBufferObjectManager getVertexBufferObjectManager(){ return mVertexObjectManager; }
    public final TextureManager getTextureManager(){ return mTextureManager; }

    //Setters
    public void setSurfaceDimensions(final int surfaceWidth,final int surfaceHeight){
        mSurfaceWidth = surfaceWidth;
        mSurfaceHeight = surfaceHeight;
        GLES20.glViewport(0, 0, mSurfaceWidth, mSurfaceHeight);
    }

}
