package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotCreateProgramException extends ShaderException {

    public CouldNotCreateProgramException(String shaderName){
        super("Could not create program in " + shaderName);
    }
}
