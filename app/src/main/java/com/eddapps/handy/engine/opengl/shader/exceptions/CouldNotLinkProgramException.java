package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class CouldNotLinkProgramException extends ShaderException {

    public CouldNotLinkProgramException(String programName, String errorMessage){
        super("Could not link " + programName + ".\n" + errorMessage);
    }

}
