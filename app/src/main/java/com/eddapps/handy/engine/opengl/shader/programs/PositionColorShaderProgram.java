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
            "attribute vec4 " + AttributeVariables.in_Position + ";                     \n" +
            "uniform vec4 " + UniformVariables.Color + ";                               \n" +
            "uniform mat4 " + UniformVariables.ModelMatrix + ";                         \n" +
            "uniform mat4 " + UniformVariables.ViewMatrix + ";                          \n" +
            "uniform mat4 " + UniformVariables.ProjectionMatrix + ";                    \n" +
            "varying lowp vec4 ex_Color;                                                \n" +
            "                                                                           \n" +
            "void main() {                                                              \n" +
            "  ex_Color = Color;                                                        \n" +
            "  gl_Position = ProjectionMatrix * ViewMatrix * ModelMatrix * in_Position; \n" +
            "}                                                                          \n";

    private static final String mFragmentShader =
            "varying lowp vec4 ex_Color;                                                \n" +
            "void main() {                                                              \n" +
            "  gl_FragColor = ex_Color;                                                 \n" +
            "}                                                                          \n";


    private PositionColorShaderProgram() {
        super(PositionColorShaderProgram.class.getSimpleName(), mVertexShader, mFragmentShader);
    }

    public static PositionColorShaderProgram getInstance(){
        if(mInstance == null)
            mInstance = new PositionColorShaderProgram();
        return mInstance;
    }

    private static int ModelMatrixUniformLocation;
    private static int ViewMatrixUniformLocation;
    private static int ProjectionMatrixUniformLocation;
    private static int ColorUniformLocation;


    @Override
    protected void bindAttributeLocations() {
        GLES20.glBindAttribLocation(mProgramHandle, AttributeVariables.in_Position.getHandle(), AttributeVariables.in_Position.getName());
    }

    @Override
    protected void getUniformLocations() {
        ModelMatrixUniformLocation = GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ModelMatrix);
        ViewMatrixUniformLocation = GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ViewMatrix);
        ProjectionMatrixUniformLocation = GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.ProjectionMatrix);
        ColorUniformLocation = GLES20.glGetUniformLocation(mProgramHandle, UniformVariables.Color);
    }
}
