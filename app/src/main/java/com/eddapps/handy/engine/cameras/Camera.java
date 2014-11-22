package com.eddapps.handy.engine.cameras;

import com.eddapps.handy.engine.objects.Entity;

/**
 * Created by Edgar on 10/07/2014.
 */
public abstract class Camera extends Entity {

    private static final String TAG = Camera.class.getSimpleName();

    protected float[] mViewMatrix;
    protected float[] mProjectionMatrix;

    //Constructors
    public Camera(){
        mViewMatrix = new float[16];
        mProjectionMatrix = new float[16];
    }

    public void onScreenReshape(int newScreenWidth, int newScreenHeight){
        generateProjectionMatrix(newScreenWidth, newScreenHeight);
        generateViewMatrix();
    }

    protected abstract void generateViewMatrix();
    protected abstract void generateProjectionMatrix(int screenWidth, int screenHeight);

    //Update Function
    @Override
    public void update(){
        generateViewMatrix();
    }

    //Getters
    public float[] getViewMatrixArray() { return mViewMatrix; }
    public float[] getProjectionMatrixArray() { return mProjectionMatrix; }

    //Setters
    public void setViewMatrixArray(float[] viewMatrix) {
        mViewMatrix = viewMatrix;
    }
    public void setProjectionMatrixArray(float[] projectionMatrix) {
        mProjectionMatrix = projectionMatrix;
    }

    public String toString(){
        return "Camera: X=" + getPositionY() + " Y=" + getPositionY();
    }
}
