package com.eddapps.handy.engine.opengl.shader.programs;

import com.eddapps.handy.engine.opengl.shader.ShaderProgram;

/**
 * Created by Edgar on 10/07/2014.
 */
public class PositionColorTextureShaderProgram extends ShaderProgram {



    private static final String mVertexShader =
            "attribute vec4 in_Position;                                                \n" +
            "attribute vec2 in_TexCoordinate;                                           \n" +
            "uniform vec4 Color;                                                        \n" +
            "uniform mat4 ModelMatrix;                                                  \n" +
            "uniform mat4 ViewMatrix;                                                   \n" +
            "uniform mat4 ProjectionMatrix;                                             \n" +
            "varying lowp vec4 ex_Color;                                                \n" +
            "varying lowp vec2 ex_TexCoordinate;                                        \n" +
            "                                                                           \n" +
            "void main() {                                                              \n" +
            "  ex_Color = Color;                                                        \n" +
            "  ex_TexCoordinate = in_TexCoordinate;                                     \n" +
            "  gl_Position = ProjectionMatrix * ViewMatrix * ModelMatrix * in_Position; \n" +
            "}                                                                          \n";

    private static final String mFragmentShader =
            "varying lowp vec4 ex_Color;                                                        \n" +
            "varying lowp vec2 ex_TexCoordinate;                                                \n" +
            "uniform sampler2D Texture;                                                         \n" +
            "void main() {                                                                      \n" +
            "  gl_FragColor = texture2D(Texture, ex_TexCoordinate).rgba;                        \n" +
            "}                                                                                  \n";

    public PositionColorTextureShaderProgram() {
        super(PositionColorTextureShaderProgram.class.getSimpleName(), mVertexShader, mFragmentShader);
    }


    @Override
    protected void bindAttributeLocations() {

    }

    @Override
    protected void getUniformLocations() {

    }
}
