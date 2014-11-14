package com.eddapps.handy.engine.opengl.shaders;

import com.eddapps.handy.engine.opengl.shaders.programs.PositionColorShaderProgram;

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

    public PositionColorShaderProgram DEFAULT_SHADER_PROGRAM = null;

    protected ShaderProgramManager(){
       DEFAULT_SHADER_PROGRAM = new PositionColorShaderProgram();
    }

    public static PositionColorShaderProgram getPositionColorShader(){
        if(_instance.DEFAULT_SHADER_PROGRAM == null)
            _instance.DEFAULT_SHADER_PROGRAM = new PositionColorShaderProgram();
        return _instance.DEFAULT_SHADER_PROGRAM;
    }
}
