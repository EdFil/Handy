package com.eddapps.handy.engine.opengl.shader;

import com.eddapps.handy.engine.opengl.shader.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorTextureShaderProgram;

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

    public PositionColorShaderProgram POSITION_COLOR_SP = null;
    public PositionColorTextureShaderProgram POSITION_COLOR_TEXTURE_SP = null;


    protected ShaderProgramManager(){ }

    public static PositionColorShaderProgram getPositionColorShader(){
        if(_instance.POSITION_COLOR_SP == null)
            _instance.POSITION_COLOR_SP = new PositionColorShaderProgram();
        return _instance.POSITION_COLOR_SP;
    }

    public static PositionColorTextureShaderProgram getPositionColorTextureShader(){
        if(_instance.POSITION_COLOR_TEXTURE_SP == null)
            _instance.POSITION_COLOR_TEXTURE_SP = new PositionColorTextureShaderProgram();
        return _instance.POSITION_COLOR_TEXTURE_SP;
    }
}
