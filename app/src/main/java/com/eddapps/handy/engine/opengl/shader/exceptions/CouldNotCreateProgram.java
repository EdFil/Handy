package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotCreateProgram extends ShaderException {

    public CouldNotCreateProgram(String shaderName){
        super("Could not create program in " + shaderName);
    }
}
