package com.eddapps.handy.engine.opengl.shader.exceptions;

/**
 * Created by edgar on 21-11-2014.
 */
public class ShaderException extends RuntimeException {

    public ShaderException(){
        super("Error in shader");
    }

    public ShaderException(String message){
        super(message);
    }

}
