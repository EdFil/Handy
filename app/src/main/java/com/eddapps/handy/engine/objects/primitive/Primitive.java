package com.eddapps.handy.engine.objects.primitive;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.opengl.shader.ShaderProgram;
import com.eddapps.handy.engine.opengl.shader.constants.UniformVariables;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;

/**
 * Created by edgar on 14-11-2014.
 */
public abstract class Primitive extends Entity {

    private final static String TAG = Primitive.class.getSimpleName();

    private VertexBufferObject mVertexBufferObject;
    private ShaderProgram mShaderProgram;

    public Primitive(VertexBufferObject vertexBufferObject, ShaderProgram shaderProgram){
        super();
        mVertexBufferObject = vertexBufferObject;
        mVertexBufferObject.sendToHardware();
        mShaderProgram = shaderProgram;
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix){
        mShaderProgram.bind();
        mShaderProgram.setUniform(UniformVariables.ViewMatrix, viewMatrix);
        mShaderProgram.setUniform(UniformVariables.ProjectionMatrix, projectionMatrix);
        uniformSets();
        mVertexBufferObject.draw();
    }

    public void uniformSets(){
        mShaderProgram.setUniform(UniformVariables.ModelMatrix, getModelMatrix());
    }

    //Getters
    public VertexBufferObject getVertexBufferObject(){ return mVertexBufferObject; }
    public ShaderProgram getShaderProgram() { return mShaderProgram; }
}
