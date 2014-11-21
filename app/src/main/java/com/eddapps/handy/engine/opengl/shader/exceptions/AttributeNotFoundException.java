package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class AttributeNotFoundException extends ShaderException {

    public AttributeNotFoundException(String attributeName) {
        super("The attribute '" + attributeName + "' was not found.");
    }
}
