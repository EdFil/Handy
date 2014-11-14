package com.eddapps.handy.engine.opengl.shaders.programs;

import android.opengl.GLES20;

import com.eddapps.handy.engine.opengl.shaders.AttribVariable;

/**
 * Created by Edgar on 10/07/2014.
 */
public class PositionColorTextureShaderProgram extends PositionColorShaderProgram {

    private int mTextureLocation;

    private static final AttribVariable[] mProgramVariables = {
            AttribVariable.in_Position,
            AttribVariable.a_TexCoordinate
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
            "varying lowp vec2 in_TexCoordinate;                                        \n" +
            "uniform sampler2D Texture;                                                 \n" +
            "void main() {                                                              \n" +
            "  gl_FragColor = texture(Texture, in_TexCoordinate);                       \n" +
            "}                                                                          \n";


    public PositionColorTextureShaderProgram() {
        super(mVertexShader, mFragmentShader, mProgramVariables);
        mTextureLocation = GLES20.glGetUniformLocation(getProgramHandle(), "Texture");
    }

    public void setTexture(float a){
        //GLES20.glUniform4f(mColorLocation, r, g, b, a);
    }
}
