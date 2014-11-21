package com.eddapps.handy.engine.opengl.shader.exceptions;

import android.opengl.GLES20;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotCompileException extends ShaderException{

    public CouldNotCompileException(){
        super("Could not compile shader");
    }

    public CouldNotCompileException(String shaderProgramName, String errorMessage) {
        super("Could not compile the " + shaderProgramName + "\n" + errorMessage);
    }
}
