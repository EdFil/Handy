package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class NullShaderException extends ShaderException {

    public NullShaderException(){
        super("Shader should not be null.");
    }

}
