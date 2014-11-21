package com.eddapps.handy.engine.opengl.shader.exceptions;

import com.eddapps.handy.engine.utils.Utilities;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotCreateShader extends ShaderException {

    public CouldNotCreateShader(String shaderName, int shaderType) {
        super("Could not create " + Utilities.translateShaderType(shaderType) + " in " + shaderName);
    }
}
