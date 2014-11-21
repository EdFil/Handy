package com.eddapps.handy.engine.opengl.shader.utils;

import android.opengl.GLES20;

/**
 * Created by edgar on 21-11-2014.
 */
public class ShaderUtils {

    public static String translateShaderType(int shaderType){
        if(shaderType == GLES20.GL_VERTEX_SHADER)
            return "Vertex Shader";
        else if (shaderType == GLES20.GL_FRAGMENT_SHADER)
            return "Fragment Shader";
        else
            return "Unknown Shader Type (" + shaderType + ")";
    }

}
