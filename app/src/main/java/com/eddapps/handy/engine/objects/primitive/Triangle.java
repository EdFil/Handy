package com.eddapps.handy.engine.objects.primitive;

import com.eddapps.handy.engine.objects.primitive.vbo.TriangleVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.shader.programs.ShaderProgram;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;
import com.eddapps.handy.engine.utils.Clock;

/**
 * Created by edgar on 14-11-2014.
 */
public class Triangle extends Primitive {

    private static final float DEFAULT_RED = 0.5f;
    private static final float DEFAULT_GREEN = 0.5f;
    private static final float DEFAULT_BLUE = 0.5f;
    private static final float DEFAULT_ALPHA = 1.0f;

    private final static String TAG = Triangle.class.getSimpleName();

    private float mRed, mGreen, mBlue, mAlpha;

    private final PositionColorShaderProgram mShaderProgram;

    public Triangle(){
        this(new TriangleVertexBufferObject(), ShaderProgramManager.getPositionColorShader());
    }

    public Triangle(VertexBufferObject vertexBufferObject, ShaderProgram shaderProgram) {
        super(vertexBufferObject, shaderProgram);
        mShaderProgram = (PositionColorShaderProgram)getShaderProgram();
        mRed = DEFAULT_RED;
        mBlue = DEFAULT_BLUE;
        mGreen = DEFAULT_GREEN;
        mAlpha = DEFAULT_ALPHA;
    }

    public void update(){ super.update(); }

    public void draw(){
        super.draw();
    }

    @Override
    public void uniformSets() {
        mShaderProgram.setColor(mRed, mGreen, mBlue, mAlpha);
    }

}
