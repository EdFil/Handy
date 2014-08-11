package com.eddapps.handy.engine.shaders;

import com.eddapps.handy.engine.shaders.programs.DefaultShaderProgram;

/**
 * Created by edgar on 29-07-2014.
 */
public class ShaderProgramManager {

    // ******** Singleton Code ********

    private static ShaderProgramManager _instance = null;

    public static void init(){
        if(_instance == null)
            _instance = new ShaderProgramManager();
    }

    // ******** Shader Manager ********

    public DefaultShaderProgram DEFAULT_SHADER_PROGRAM = null;

    protected ShaderProgramManager(){
       DEFAULT_SHADER_PROGRAM = new DefaultShaderProgram();
    }

    public static DefaultShaderProgram getDefaultShader(){
        if(_instance.DEFAULT_SHADER_PROGRAM == null)
            _instance.DEFAULT_SHADER_PROGRAM = new DefaultShaderProgram();
        return _instance.DEFAULT_SHADER_PROGRAM;
    }
}
