package com.eddapps.handy;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by Edgar on 10/07/2014.
 */
public class Shader {
    private int _programID;
    private int _modelMatrixLocation;
    private int _viewMatrixLocation;
    private int _projectionMatrixLocation;


    Shader() {
        _programID = createProgram(kVertexShader, kFragmentShader);
        GLES20.glBindAttribLocation(_programID, 0, "in_Position");
        //GLES20.glBindAttribLocation(_programID, 1, "in_TextureCoordinate");
        GLES20.glLinkProgram(_programID);
        _modelMatrixLocation = GLES20.glGetUniformLocation(_programID, "ModelMatrix");
        _viewMatrixLocation = GLES20.glGetUniformLocation(_programID, "ViewMatrix");
        _projectionMatrixLocation = GLES20.glGetUniformLocation(_programID, "ProjectionMatrix");
    }

    public void useProgram() {
        GLES20.glUseProgram(_programID);
    }

    public void setModelMatrix(float[] modelMatrix) {
        GLES20.glUniformMatrix4fv(_modelMatrixLocation, 1, false, modelMatrix, 0);
    }

    public void setViewMatrix(float[] viewMatrix) {
        GLES20.glUniformMatrix4fv(_viewMatrixLocation, 1, false, viewMatrix, 0);
    }

    public void setProjectionMatrix(float[] projectionMatrix) {
        GLES20.glUniformMatrix4fv(_projectionMatrixLocation, 1, false, projectionMatrix, 0);
    }

    private static int getShader(String source, int type) {
        int shader = GLES20.glCreateShader(type);
        if (shader == 0) return 0;

        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        int[] compiled = { 0 };
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("[Shader compilation]", GLES20.glGetShaderInfoLog(shader));
        }
        return shader;
    }

    private static int createProgram(String vertexShader, String fragmentShader) {
        int vs = getShader(vertexShader, GLES20.GL_VERTEX_SHADER);
        int fs = getShader(fragmentShader, GLES20.GL_FRAGMENT_SHADER);
        if (vs == 0 || fs == 0) return 0;

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vs);
        GLES20.glAttachShader(program, fs);
        GLES20.glLinkProgram(program);

        int[] linked = { 0 };
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linked, 0);
        if (linked[0] == 0) {
            Log.e("[Shader compilation]", GLES20.glGetProgramInfoLog(program));
            return 0;
        }
        return program;
    }


    private static final String kVertexShader =
            "attribute vec4 in_Position;                                                \n" +
           // "attribute vec2 in_UV;                                                      \n" +
            "uniform mat4 ModelMatrix;                                                  \n" +
            "uniform mat4 ViewMatrix;                                                   \n" +
            "uniform mat4 ProjectionMatrix;                                             \n" +
            "                                                                           \n" +
            "void main() {                                                              \n" +
            "  gl_Position = ProjectionMatrix * ViewMatrix * ModelMatrix * in_Position; \n" +
            "}                                                                          \n";

    private static final String kFragmentShader =
            "void main() {                              " +
            "  gl_FragColor = vec4(0.5,0,0,1);          " +
            "}                                          ";
}
