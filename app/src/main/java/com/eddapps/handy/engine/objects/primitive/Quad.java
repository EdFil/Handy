package com.eddapps.handy.engine.objects.primitive;

import com.eddapps.handy.engine.objects.primitive.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.shader.ShaderProgram;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;

/**
 * Created by edgar on 13-11-2014.
 */
public class Quad extends Primitive {

    private static final float DEFAULT_RED = 0.5f;
    private static final float DEFAULT_GREEN = 0.5f;
    private static final float DEFAULT_BLUE = 0.5f;
    private static final float DEFAULT_ALPHA = 1.0f;

    private final static String TAG = Quad.class.getSimpleName();

    private float mRed, mGreen, mBlue, mAlpha;

    //References
    private final PositionColorShaderProgram mShaderProgram;

    public Quad(){
        this(new QuadVertexBufferObject(), new PositionColorShaderProgram());
    }

    public Quad(float positionX, float positionY){
        this(new QuadVertexBufferObject(), new PositionColorShaderProgram());
    }

    public Quad(VertexBufferObject vertexBufferObject, ShaderProgram shaderProgram){
        super(vertexBufferObject, shaderProgram);
        mShaderProgram = (PositionColorShaderProgram)getShaderProgram();
        mRed = DEFAULT_RED;
        mBlue = DEFAULT_BLUE;
        mGreen = DEFAULT_GREEN;
        mAlpha = DEFAULT_ALPHA;
    }

    public void update(){
        super.update();
    }

    public void draw(){
        super.draw();
    }

    @Override
    public void uniformSets() {
        mShaderProgram.setColor(mRed, mGreen, mBlue, mAlpha);
    }

}
