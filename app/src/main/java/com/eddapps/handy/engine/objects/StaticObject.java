package com.eddapps.handy.engine.objects;

import android.opengl.GLES20;

import com.eddapps.handy.engine.opengl.shaders.ShaderProgramManager;

/**
 * Created by Edgar on 12/08/2014.
 */
public class StaticObject extends GameObject {

    @Override
    public void draw(){
        ShaderProgramManager.getPositionColorShader().useProgram();
        ShaderProgramManager.getPositionColorShader().setModelMatrix(getModelMatrix());
        ShaderProgramManager.getPositionColorShader().setColor(getColor());
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, getVertexBuffer()[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void update(){
        //Do Nothing
    }
}
