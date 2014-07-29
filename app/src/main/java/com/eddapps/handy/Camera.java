package com.eddapps.handy;

import android.opengl.Matrix;
import android.renderscript.Matrix4f;

/**
 * Created by Edgar on 10/07/2014.
 */
public class Camera {

    private float _positionX, _positionY;
    private float _rotation;

    private Matrix4f _viewMatrix;
    private Matrix4f _projectionMatrix;

    //Constructors
    public Camera(int screenWidth, int screenHeight){
        _positionX = _positionY = 0.0f;
        _rotation = 0.0f;
        _viewMatrix = new Matrix4f();
        _projectionMatrix = new Matrix4f();
        generateOrthographicProjection(screenWidth, screenHeight);
        generateViewMatrixDirty();
    }

    public void onScreenReshape(int newScreenWidth, int newScreenHeight){
        generateOrthographicProjection(newScreenWidth, newScreenHeight);
        generateViewMatrixDirty();
    }

    private void generateViewMatrixDirty(){
        //Left
        _viewMatrix.set(0,0, 1.0f);
        _viewMatrix.set(1,0, 0.0f);
        _viewMatrix.set(2,0, 0.0f);
        //Up
        _viewMatrix.set(0,1, 0.0f);
        _viewMatrix.set(1,1, 1.0f);
        _viewMatrix.set(2,1, 0.0f);
        //Forward
        _viewMatrix.set(0,2, 0.0f);
        _viewMatrix.set(1,2, 0.0f);
        _viewMatrix.set(2,2, 1.0f);
        //Translation
        _viewMatrix.set(0,3, _positionX);
        _viewMatrix.set(1,3, _positionY);
        _viewMatrix.set(2,3, 0.0f);
    }

    private void generateOrthographicProjection(int screenWidth, int screenHeight){
        float ratio = (float) screenWidth / screenHeight;
        _projectionMatrix.loadOrtho(-ratio, ratio, -1, 1, -10, 10);
    }

    public void bindMatrices(){
        ShaderProgramManager.getDefaultShader().setViewMatrix(_viewMatrix.getArray());
        ShaderProgramManager.getDefaultShader().setProjectionMatrix(_projectionMatrix.getArray());
    }

    //Update Function
    public void update(int deltaTime){
        //TODO: Updates the view matrix with the player's position
    }

    //Getters
    public float[] getViewMatrixArray() { return _viewMatrix.getArray(); }
    public float[] getProjectionMatrixArray() { return _projectionMatrix.getArray(); }
}
