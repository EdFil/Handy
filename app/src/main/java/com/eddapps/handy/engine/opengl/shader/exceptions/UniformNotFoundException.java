package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class UniformNotFoundException extends ShaderException {

    public UniformNotFoundException(String attributeName) {
        super("The uniform '" + attributeName + "' was not found.");
    }
}
