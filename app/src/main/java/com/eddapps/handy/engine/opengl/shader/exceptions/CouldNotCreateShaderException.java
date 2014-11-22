package com.eddapps.handy.engine.opengl.shader.exceptions;

import com.eddapps.handy.engine.opengl.shader.utils.ShaderUtils;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotCreateShaderException extends ShaderException {

    public CouldNotCreateShaderException(String shaderName, int shaderType) {
        super("Could not create " + ShaderUtils.translateShaderType(shaderType) + " in " + shaderName);
    }
}
