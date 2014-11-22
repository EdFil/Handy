package com.eddapps.handy.engine.opengl.shader.programs;

import android.opengl.GLES20;

import com.eddapps.handy.engine.opengl.shader.ShaderProgram;
import com.eddapps.handy.engine.opengl.shader.constants.AttributeVariables;
import com.eddapps.handy.engine.opengl.shader.constants.UniformVariables;

/**
 * Created by Edgar on 10/07/2014.
 */
public class PositionColorShaderProgram extends ShaderProgram {

    private static PositionColorShaderProgram mInstance = null;

    private static final String mVertexShader =
            "attribute vec4 " + AttributeVariables.in_Position + ";                     " +
            "uniform vec4 " + UniformVariables.Color + ";                               " +
            "uniform mat4 " + UniformVariables.ModelMatrix + ";                         " +
            "uniform mat4 " + UniformVariables.ViewMatrix + ";                          " +
            "uniform mat4 " + UniformVariables.ProjectionMatrix + ";                    " +
            "varying lowp vec4 ex_Color;                                                " +
            "                                                                           " +
            "void main() {                                                              " +
            "  ex_Color = Color;                                                        " +
            "  gl_Position = ProjectionMatrix * ViewMatrix * ModelMatrix * in_Position; " +
            "}                                                                          ";

    private static final String mFragmentShader =
            "varying lowp vec4 ex_Color;                                                " +
            "void main() {                                                              " +
            "  gl_FragColor = ex_Color;                                                 " +
            "}                                                                          ";


    private PositionColorShaderProgram() {
        super(PositionColorShaderProgram.class.getSimpleName(), mVertexShader, mFragmentShader);
    }

    public static PositionColorShaderProgram getInstance(){
        if(mInstance == null)
            mInstance = new PositionColorShaderProgram();
        return mInstance;
    }

    @Override
    protected void bindAttributeLocations() {
        GLES20.glBindAttribLocation(mProgramHandle, AttributeVariables.in_Position.getHandle(), AttributeVariables.in_Position.getName());
    }

    @Override
    protected void getUniformLocations() {
        addUniform(UniformVariables.ModelMatrix, GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ModelMatrix));
        addUniform(UniformVariables.ViewMatrix, GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ViewMatrix));
        addUniform(UniformVariables.ProjectionMatrix, GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ProjectionMatrix));
        addUniform(UniformVariables.Color, GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.Color));
    }
}
