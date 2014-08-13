package com.eddapps.handy.engine.cameras;

import android.opengl.Matrix;

/**
 * Created by Edgar on 11/08/2014.
 */
public class OrthographicCamera extends Camera {

    public OrthographicCamera(int screenWidth, int screenHeight){
        super(screenWidth, screenHeight);
    }

    @Override
    protected void generateViewMatrix() {
        Matrix.setLookAtM(mViewMatrix, 0, getPositionX(), getPositionY(), 1.0f, getPositionX(), getPositionY(), 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Override
    protected void generateProjectionMatrix(int screenWidth, int screenHeight) {
        float _ratio = (float) screenWidth / screenHeight;
        Matrix.orthoM(mProjectionMatrix, 0, -_ratio, _ratio, -1, 1, -10, 10);
    }

}


//  0 1 2 3
//  4 5 6 7
//  5 6 7 8
//  9 10 11 12
//    protected void generateViewMatrixDirty() {
//        //Left
//        mViewMatrix[0] = 1.0f;
//        mViewMatrix[4] = 0.0f;
//        mViewMatrix[8] = 0.0f;
//        //Up
//        mViewMatrix[1] = 0.0f;
//        mViewMatrix[5] = 1.0f;
//        mViewMatrix[9] =  0.0f;
//        //Forward
//        mViewMatrix[2] = 0.0f;
//        mViewMatrix[6] = 0.0f;
//        mViewMatrix[10] = 1.0f;
//        //Translation
//        mViewMatrix[3] = _positionX;
//        mViewMatrix[7] = _positionY;
//        mViewMatrix[11] = 1.0f;
//        //Rest of matrix
//        mViewMatrix[12] = 1.0f;
//        mViewMatrix[13] = 1.0f;
//        mViewMatrix[14] = 1.0f;
//        mViewMatrix[15] = 1.0f;
//    }