package com.eddapps.handy.engine.objects;

import android.opengl.Matrix;

import com.eddapps.handy.engine.utils.Clock;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by edgar on 13-11-2014.
 */
public class Entity {

    private static float DEFAULT_POSITION_X = 0.0f;
    private static float DEFAULT_POSITION_Y = 0.0f;
    private static float DEFAULT_SCALE_X = 1.0f;
    private static float DEFAULT_SCALE_Y = 1.0f;
    private static float DEFAULT_ROTATION = 0.0f;

    private float mPositionX, mPositionY;
    private float mScaleX, mScaleY;
    private float mRotation;

    private float[] mModelMatrix;

    public Entity(){
        this(DEFAULT_POSITION_X, DEFAULT_POSITION_Y, DEFAULT_SCALE_X, DEFAULT_SCALE_Y, DEFAULT_ROTATION);
    }

    public Entity(float positionX, float positionY){
        this(positionX, positionY, DEFAULT_SCALE_X, DEFAULT_SCALE_Y, DEFAULT_ROTATION);
    }

    public Entity(float positionX, float positionY, float scaleX, float scaleY, float rotation){
        Random rnd = new Random();
        mPositionX = rnd.nextFloat() * 3 - 1.5f;
        mPositionY = rnd.nextFloat() * 3 - 1.5f;
        mScaleX = rnd.nextFloat();
        mScaleY = rnd.nextFloat();
        mRotation = rotation;
        mModelMatrix = new float[16];
    }

    public void update(){
        //translate(0.0f, 1.0f*Clock.getDelta());
        rotate(10 * Clock.getDelta());
        reloadModelMatrix();
    }

    public void reloadModelMatrix(){
        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, mPositionX, mPositionY, 0.0f);
        Matrix.scaleM(mModelMatrix, 0, mScaleX, mScaleY, 1.0f);
        Matrix.rotateM(mModelMatrix, 0, mRotation, 0, 0, 1);
    }

    //Getters
    public float getPositionX(){ return mPositionX; }
    public float getPositionY(){ return mPositionY; }
    public float getScaleX(){ return mScaleX; }
    public float getScaleY(){ return mScaleY; }
    public float getRotation(){ return mRotation; }
    public float[] getModelMatrix(){ return mModelMatrix; }

    //Setters
    public void setPositionX(float positionX){ mPositionX = positionX; }
    public void setPositionY(float positionY){ mPositionY = positionY; }
    public void setPosition(float positionX, float positionY) { mPositionX = positionX; mPositionY = positionY; }
    public void setScaleX(float scaleX){ mScaleX = scaleX; }
    public void setScaleY(float scaleY){ mScaleY = scaleY; }
    public void setScale(float scaleX, float scaleY) { mScaleX = scaleX; mScaleY = scaleY; }
    public void setModelMatrix(float[] modelMatrix) { mModelMatrix = Arrays.copyOf(modelMatrix, modelMatrix.length); }

    //
    public void rotate(float amount){
        mRotation += amount;
    }
    public void translate(float xAmount, float yAmount){
        mPositionX += xAmount;
        mPositionY += yAmount;
    }

}

