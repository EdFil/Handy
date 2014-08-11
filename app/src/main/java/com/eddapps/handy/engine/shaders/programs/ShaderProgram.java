package com.eddapps.handy.engine.shaders.programs;

import android.opengl.GLES20;

import com.eddapps.handy.engine.shaders.AttribVariable;
import com.eddapps.handy.engine.utils.Utilities;

/**
 * Created by Edgar on 11/08/2014.
 */
public abstract class ShaderProgram {

    private int mProgramHandle;
    private int mVertexShaderHandle;
    private int mFragmentShaderHandle;
    private int mModelMatrixLocation;
    private int mViewMatrixLocation;
    private int mProjectionMatrixLocation;

    public ShaderProgram(String vertexShaderCode, String fragmentShaderCode, AttribVariable[] programVariables) {
        mVertexShaderHandle = Utilities.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        mFragmentShaderHandle = Utilities.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        mProgramHandle = Utilities.createProgram(mVertexShaderHandle, mFragmentShaderHandle, programVariables);
        mModelMatrixLocation = GLES20.glGetUniformLocation(mProgramHandle, "ModelMatrix");
        mViewMatrixLocation = GLES20.glGetUniformLocation(mProgramHandle, "ViewMatrix");
        mProjectionMatrixLocation = GLES20.glGetUniformLocation(mProgramHandle, "ProjectionMatrix");
    }

    public void useProgram() {
        GLES20.glUseProgram(mProgramHandle);
    }

    public void delete() {
        GLES20.glDeleteShader(mVertexShaderHandle);
        GLES20.glDeleteShader(mFragmentShaderHandle);
        GLES20.glDeleteProgram(mProgramHandle);
    }

    //Getters
    public int getProgramHandle(){ return mProgramHandle; }

    public void setModelMatrix(float[] modelMatrix) {
        GLES20.glUniformMatrix4fv(mModelMatrixLocation, 1, false, modelMatrix, 0);
    }

    public void setViewMatrix(float[] viewMatrix) {
        GLES20.glUniformMatrix4fv(mViewMatrixLocation, 1, false, viewMatrix, 0);
    }

    public void setProjectionMatrix(float[] projectionMatrix) {
        GLES20.glUniformMatrix4fv(mProjectionMatrixLocation, 1, false, projectionMatrix, 0);
    }

}