package com.eddapps.handy.engine.objects;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.eddapps.handy.engine.opengl.shaders.ShaderProgramManager;
import com.eddapps.handy.engine.utils.Clock;

/**
 * Created by Edgar on 12/08/2014.
 */
public class MovingObject extends GameObject {

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
        rotate(100 * Clock.getDelta());
        Matrix.setIdentityM(getModelMatrix(), 0);
        Matrix.translateM(getModelMatrix(), 0, getPositionX(), getPositionY(), 0.0f);
        Matrix.rotateM(getModelMatrix(), 0, getRotation(), 0, 0, 1);
        Matrix.scaleM(getModelMatrix(), 0, getScaleX(), getScaleY(), 1.0f);
    }
}
