package com.eddapps.handy.engine.objects.primitives;

import android.opengl.Matrix;
import android.util.Log;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.objects.primitives.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.shaders.ShaderProgramManager;
import com.eddapps.handy.engine.shaders.programs.ShaderProgram;
import com.eddapps.handy.engine.utils.Clock;

/**
 * Created by edgar on 13-11-2014.
 */
public class Quad extends Entity {

    private final static String TAG = Entity.class.getSimpleName();
    private QuadVertexBufferObject mVertexBufferObject;
    private ShaderProgram mShaderProgram;

    public Quad(float positionX, float positionY, float sizeX, float sizeY){
        super(positionX, positionY);
        mShaderProgram = ShaderProgramManager.getDefaultShader();
        mVertexBufferObject = new QuadVertexBufferObject(this);
    }

    public void update(){
        rotate(10 * Clock.getDelta());
        reloadModelMatrix();
        Log.d(TAG, getRotation() + "");
    }

    public void draw(){
        mShaderProgram.useProgram();
        mShaderProgram.setModelMatrix(getModelMatrix());
        mVertexBufferObject.draw();
    }

}
