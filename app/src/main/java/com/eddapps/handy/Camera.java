package com.eddapps.handy;

import android.opengl.Matrix;
import android.renderscript.Matrix4f;

/**
 * Created by Edgar on 10/07/2014.
 */
public class Camera {

    private float _positionX, _positionY;
    private float _rotation;

    private float[] _viewMatrix;
    private float[] _projectionMatrix;

    //Constructors
    public Camera(int screenWidth, int screenHeight){
        _positionX = _positionY = 0.0f;
        _rotation = 0.0f;
        _viewMatrix = new float[16];
        _projectionMatrix = new float[16];
        generateOrthographicProjection(screenWidth, screenHeight);
        generateViewMatrixDirty();
    }

    public void onScreenReshape(int newScreenWidth, int newScreenHeight){
        generateOrthographicProjection(newScreenWidth, newScreenHeight);
        generateViewMatrixDirty();
    }
//  0 1 2 3
//  4 5 6 7
//  5 6 7 8
//  9 10 11 12
    private void generateViewMatrixDirty(){
//        //Left
//        _viewMatrix[0] = 1.0f;
//        _viewMatrix[4] = 0.0f;
//        _viewMatrix[8] = 0.0f;
//        //Up
//        _viewMatrix[1] = 0.0f;
//        _viewMatrix[5] = 1.0f;
//        _viewMatrix[9] =  0.0f;
//        //Forward
//        _viewMatrix[2] = 0.0f;
//        _viewMatrix[6] = 0.0f;
//        _viewMatrix[10] = 1.0f;
//        //Translation
//        _viewMatrix[3] = _positionX;
//        _viewMatrix[7] = _positionY;
//        _viewMatrix[11] = 1.0f;
//        //Rest of matrix
//        _viewMatrix[12] = 1.0f;
//        _viewMatrix[13] = 1.0f;
//        _viewMatrix[14] = 1.0f;
//        _viewMatrix[15] = 1.0f;
        Matrix.setLookAtM(_viewMatrix, 0, _positionX, _positionY, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    private void generateOrthographicProjection(int screenWidth, int screenHeight){
        float ratio = (float) screenWidth / screenHeight;
        Matrix.orthoM(_projectionMatrix, 0, -ratio, ratio, -1, 1, -10, 10);
    }

    public void bindMatrices(){
        ShaderProgramManager.getDefaultShader().setViewMatrix(_viewMatrix);
        ShaderProgramManager.getDefaultShader().setProjectionMatrix(_projectionMatrix);
    }

    //Update Function
    public void update(int deltaTime){
        //TODO: Updates the view matrix with the player's position
    }

    //Getters
    public float[] getViewMatrixArray() { return _viewMatrix; }
    public float[] getProjectionMatrixArray() { return _projectionMatrix; }
}
