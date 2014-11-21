package com.eddapps.handy.engine.opengl.shader;

import android.graphics.Shader;
import android.opengl.GLES20;
import android.util.Log;

import com.eddapps.handy.engine.opengl.shader.exceptions.AttributeNotFoundException;
import com.eddapps.handy.engine.opengl.shader.exceptions.CouldNotCompileException;
import com.eddapps.handy.engine.opengl.shader.exceptions.CouldNotCreateProgram;
import com.eddapps.handy.engine.opengl.shader.exceptions.CouldNotCreateShader;
import com.eddapps.handy.engine.opengl.shader.exceptions.CouldNotLinkProgram;
import com.eddapps.handy.engine.opengl.shader.exceptions.ShaderException;
import com.eddapps.handy.engine.opengl.shader.exceptions.UniformNotFoundException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Edgar on 11/08/2014.
 */
public abstract class ShaderProgram {

    private static String TAG = ShaderProgram.class.getSimpleName();

    private String mName;

    private boolean mCompiled;

    private String mVertexShaderCode;
    private String mFragmentShaderCode;

    //private HashMap<String, Integer> mAttributeLocations;
    //private HashMap<String, Integer> mUniformLocations;

    protected int mProgramHandle;

    public ShaderProgram(final String name, final String vertexShaderCode, final String fragmentShaderCode) {
        mName = name;
        mProgramHandle = 0;
        mCompiled = false;
        mVertexShaderCode = vertexShaderCode;
        mFragmentShaderCode = fragmentShaderCode;
        //mAttributeLocations = new HashMap<String, Integer>();
        //mUniformLocations = new HashMap<String, Integer>();
    }


    //Getters
    public boolean isCompiled() { return mCompiled; }
    public String getName(){ return mName; }


    //Functions
    public void bind(){
        GLES20.glUseProgram(mProgramHandle);
    }

    public void delete(){
        GLES20.glDeleteProgram(mProgramHandle);
        mCompiled = false;
    }

    public void reloadShader(){
        delete();
        compile();
    }

    public void compile(){
        if(mCompiled)
            return;

        int vertexShaderHandle = -1;
        int fragmentShaderHandle = -1;
        try {
            //Compile shader code
            vertexShaderHandle = compileShader(GLES20.GL_VERTEX_SHADER, mVertexShaderCode);
            fragmentShaderHandle = compileShader(GLES20.GL_FRAGMENT_SHADER, mFragmentShaderCode);

            //Create program
            createProgram(vertexShaderHandle, fragmentShaderHandle);

            //Attach compiled shaders to created program
            GLES20.glAttachShader(mProgramHandle, vertexShaderHandle);
            GLES20.glAttachShader(mProgramHandle, fragmentShaderHandle);

            bindAttributeLocations();
            linkProgram();
            getUniformLocations();

            mCompiled = true;

            Log.d(TAG, mName + " finished compiling.");

        }catch(ShaderException e){
            Log.e(TAG, e.getMessage());
            mProgramHandle = 0;
            mCompiled = false;
        }
        finally {
            GLES20.glDeleteShader(vertexShaderHandle);
            GLES20.glDeleteShader(fragmentShaderHandle);
        }
    }

    protected abstract void bindAttributeLocations();
    protected abstract void getUniformLocations();

    private int compileShader(final int shaderType, String shaderCode) throws ShaderException{
        final int shaderHandle = GLES20.glCreateShader(shaderType);

        if(shaderHandle == 0)
            throw new CouldNotCreateShader(mName, shaderType);

        //Compile Shader
        GLES20.glCompileShader(shaderHandle);

        // Get the compilation status.
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        // If the compilation failed, deleteShader the shader.
        if (compileStatus[0] == 0){
            GLES20.glDeleteShader(shaderHandle);
            throw new CouldNotCompileException(mName, GLES20.glGetShaderInfoLog(shaderHandle));

        }

        return shaderHandle;
    }

    private void createProgram(int vertexShaderHandle, int fragmentShaderHandle) {
        //Create program
        mProgramHandle = GLES20.glCreateProgram();
        //Check if program was created
        if (mProgramHandle == 0)
            throw new CouldNotCreateProgram(mName);
    }

    private void linkProgram(){
        //Link the program
        GLES20.glLinkProgram(mProgramHandle);

        //Check is there are link errors
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(mProgramHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(mProgramHandle);
            mProgramHandle = 0;
            throw new CouldNotLinkProgram(mName, GLES20.glGetProgramInfoLog(mProgramHandle));
        }
    }
}