package com.eddapps.handy.engine.objects.primitives;

import android.graphics.Shader;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.opengl.shaders.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.shaders.programs.ShaderProgram;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;

/**
 * Created by edgar on 14-11-2014.
 */
public abstract class Primitive extends Entity {

    private static final float DEFAULT_RED = 0.5f;
    private static final float DEFAULT_GREEN = 0.5f;
    private static final float DEFAULT_BLUE = 0.5f;
    private static final float DEFAULT_ALPHA = 1.0f;

    private final static String TAG = Primitive.class.getSimpleName();

    private float mRed, mGreen, mBlue, mAlpha;

    private VertexBufferObject mVertexBufferObject;
    private PositionColorShaderProgram mShaderProgram;

    public Primitive(VertexBufferObject vertexBufferObject, PositionColorShaderProgram shaderProgram){
        super();
        mVertexBufferObject = vertexBufferObject;
        mShaderProgram = shaderProgram;
        mRed = DEFAULT_RED;
        mBlue = DEFAULT_BLUE;
        mGreen = DEFAULT_GREEN;
        mAlpha = DEFAULT_ALPHA;
    }

    public void draw(){
        mShaderProgram.useProgram();
        mShaderProgram.setColor(mRed, mGreen, mBlue, mAlpha);
        mShaderProgram.setModelMatrix(getModelMatrix());
        mVertexBufferObject.draw();
    }

    //Getters
    public VertexBufferObject getVertexBufferObject(){ return mVertexBufferObject; }
    public PositionColorShaderProgram getShaderProgram() { return mShaderProgram; }
}
