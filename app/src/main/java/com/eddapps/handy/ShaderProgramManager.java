package com.eddapps.handy;

/**
 * Created by edgar on 29-07-2014.
 */
public class ShaderProgramManager {

    private static ShaderProgramManager _instance = null;

    public Shader DEFAULT_SHADER = null;

    protected ShaderProgramManager(){
        DEFAULT_SHADER = new Shader();
    }

    public static void init(){
        if(_instance == null)
            _instance = new ShaderProgramManager();
    }

    public static Shader getDefaultShader(){
        return _instance.DEFAULT_SHADER;
    }
}
