package com.eddapps.handy.engine.shaders.programs;

import android.opengl.GLES20;

import com.eddapps.handy.engine.shaders.AttribVariable;

/**
 * Created by Edgar on 10/07/2014.
 */
public class DefaultShaderProgram extends ShaderProgram {

    private int mColorLocation;

    private static final AttribVariable[] mProgramVariables = {
            AttribVariable.in_Position
    };

    private static final String mVertexShader =
            "attribute vec4 in_Position;                                                \n" +
            "uniform vec4 Color;                                                        \n" +
            "uniform mat4 ModelMatrix;                                                  \n" +
            "uniform mat4 ViewMatrix;                                                   \n" +
            "uniform mat4 ProjectionMatrix;                                             \n" +
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


    public DefaultShaderProgram() {
        super(mVertexShader, mFragmentShader, mProgramVariables);
        mColorLocation = GLES20.glGetUniformLocation(getProgramHandle(), "Color");
    }

    public void setColor(float[] color){
        GLES20.glUniform4f(mColorLocation, color[0], color[1], color[2], color[3]);
        //GLES20.glUniform4fv(mColorLocation, 4, color, 0); does not work and i don't know why
    }
}
