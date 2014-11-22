package com.eddapps.handy.engine.opengl.shader;

import android.graphics.Shader;
import android.util.Log;

import com.eddapps.handy.engine.opengl.shader.exceptions.NullShaderException;
import com.eddapps.handy.engine.opengl.shader.exceptions.ShaderException;

import java.util.ArrayList;

/**
 * Created by edgar on 29-07-2014.
 */
public class ShaderProgramManager {

    private static final String TAG = ShaderProgramManager.class.getSimpleName();

    // <Shader, usages>
    private ArrayList<ShaderProgram> mLoadedShaderPrograms;

    public ShaderProgramManager(){
        mLoadedShaderPrograms = new ArrayList<ShaderProgram>();
    }

    public void loadShaderProgram(ShaderProgram shaderProgram){
        if (shaderProgram == null)
           throw new NullShaderException();
        else if (shaderProgram.isCompiled())
            Log.d(TAG, "Shader " + shaderProgram.getName() + " already compiled.");
        else if (mLoadedShaderPrograms.contains(shaderProgram)){
            Log.d(TAG, "Shader " + shaderProgram.getName() + " already loaded.");
        }else {
            Log.d(TAG, "Shader " + shaderProgram.getName() + " was added.");
            mLoadedShaderPrograms.add(shaderProgram);
        }
    }

    public void destroy(){
        for(int i = 0; i < mLoadedShaderPrograms.size(); i++)
            mLoadedShaderPrograms.get(i).delete();
        mLoadedShaderPrograms.clear();
    }

    public void update(){
        for(int i = 0; i < mLoadedShaderPrograms.size(); i++)
            mLoadedShaderPrograms.get(i).compile();
    }

    public void onReload(){
        Log.d(TAG, "--> Reloading " + mLoadedShaderPrograms.size() + " shaders.");
        for(int i = 0; i < mLoadedShaderPrograms.size(); i++)
            mLoadedShaderPrograms.get(i).reloadShader();
    }

    public void printSize(){ Log.d(TAG, "" + mLoadedShaderPrograms.size()); }

}
