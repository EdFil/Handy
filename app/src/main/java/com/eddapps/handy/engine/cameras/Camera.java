package com.eddapps.handy.engine.cameras;

import android.util.Log;

/**
 * Created by Edgar on 10/07/2014.
 */
public abstract class Camera {

    private float mPositionX, mPositionY;
    private float mRotation;

    protected float[] mViewMatrix;
    protected float[] mProjectionMatrix;

    private boolean mNeedsUpdate;

    //Constructors
    public Camera(int screenWidth, int screenHeight){
        mPositionX = mPositionY = 0.0f;
        mRotation = 0.0f;
        mViewMatrix = new float[16];
        mProjectionMatrix = new float[16];
        mNeedsUpdate = false;
        generateProjectionMatrix(screenWidth, screenHeight);
        generateViewMatrix();
    }

    public void onScreenReshape(int newScreenWidth, int newScreenHeight){
        generateProjectionMatrix(newScreenWidth, newScreenHeight);
        generateViewMatrix();
    }

    protected abstract void generateViewMatrix();
    protected abstract void generateProjectionMatrix(int screenWidth, int screenHeight);

    //Update Function
    public void update(){
            generateViewMatrix();
        //TODO: Updates the view matrix with the player's position
    }

    //Getters
    public float[] getViewMatrixArray() { return mViewMatrix; }
    public float[] getProjectionMatrixArray() { return mProjectionMatrix; }
    public float getPositionX() { return mPositionX; }
    public float getPositionY() { return mPositionY; }
    public float getRotation() { return mRotation; }

    //Setters
    public void setViewMatrixArray(float[] viewMatrix) {
        mViewMatrix = viewMatrix;
        mNeedsUpdate = true;
    }
    public void setProjectionMatrixArray(float[] projectionMatrix) {
        mProjectionMatrix = projectionMatrix;
        mNeedsUpdate = true;
    }
    public void setPositionX(float positionX){
        mPositionX = positionX;
        mNeedsUpdate = true;
    }
    public void setPositionY(float positionY){
        mPositionY = positionY;
        mNeedsUpdate = true;
    }
    public void setPosition(float positionX, float positionY){
        mPositionX = positionX;
        mPositionY = positionY;
        mNeedsUpdate = true;
    }
    public void setRotation(float rotation){
        mRotation = rotation;
        mNeedsUpdate = true;
    }

    //Transformations
    public void translate(float deltaX, float deltaY){
        mPositionX += deltaX;
        mPositionY += deltaY;
        mNeedsUpdate = true;
        Log.v("asdasd", this.toString());
    }

    public String toString(){
        return "Camera: X=" + mPositionX + " Y=" + mPositionY;
    }
}
