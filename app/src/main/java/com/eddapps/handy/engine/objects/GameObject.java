package com.eddapps.handy.engine.objects;

/**
 * Created by edgar on 29-07-2014.
 */
public abstract class GameObject {

    private float mPositionX, mPositionY;
    private float mScaleX, mScaleY;
    private float mRotation;

    private float mDirectionX, mDirectionY;
    private float mVelocityX, mVelocityY;
    private float mAccelerationX, mAccelerationY;

    private float[] mModelMatrix;
    private float[] _color;

    public GameObject(){
        mPositionX = (float) (Math.random() - 0.5f) * 3.0f;
        mPositionY = (float) (Math.random() - 0.5f) * 2.0f;
        mVelocityX = (float) (Math.random());
        mVelocityY = (float) (Math.random());
        mDirectionX = (float) (Math.random() * 2 - 1f);
        mDirectionY = (float) (Math.random() * 2 - 1f);
        _color = new float[] {(float)Math.random(), (float)Math.random(), (float)Math.random(), 1.0f};
        mScaleX = mScaleY = 1.0f; //(float)(Math.random() * 4);;
        mRotation = 0.0f;
        mModelMatrix = new float[16];
    }

    public abstract void update();
    public abstract void draw();

    //Getters
    public float getPositionX() { return mPositionX; }
    public float getPositionY() { return mPositionY; }
    public float getScaleX() { return mScaleX; }
    public float getScaleY() { return mScaleY; }
    public float getRotation() { return mRotation; }
    public float[] getColor(){ return _color; }
    public float getDirectionX() { return mDirectionX; }
    public float getDirectionY() { return mDirectionY; }
    public float getVelocityX() { return mVelocityX; }
    public float getVelocityY() { return mVelocityY; }
    public float getAccelerationX() { return mAccelerationX; }
    public float getAccelerationY() { return mAccelerationY; }
    protected float[] getModelMatrix() { return mModelMatrix; }

    //Setters
    public void setPosition(float x, float y){ mPositionX = x; mPositionY = y; }
    public void setPositionX(float positionX) { mPositionX = positionX; }
    public void setPositionY(float positionY) { mPositionY = positionY; }
    public void setScale(float scaleX, float scaleY) { mScaleX = scaleX; mScaleY = scaleY; }
    public void setScaleX(float scaleX) { mScaleX = scaleX; }
    public void setScaleY(float scaleY) { mScaleY = scaleY; }
    public void setRotation(float rotation) { mRotation = rotation; }
    public void setColor(float[] color){ _color[0] = color[0]; _color[1] = color[1]; _color[2] = color[2]; }
    public void setDirection(float directionX, float directionY) { mDirectionX = directionX; mDirectionY = directionY; }
    public void setDirectionX(float directionX) { mDirectionX = directionX; }
    public void setDirectionY(float directionY) { mDirectionY = directionY; }
    public void setVelocity(float velocityX, float velocityY) { mVelocityX = velocityX; mVelocityY = velocityY; }
    public void setVelocityX(float velocityX) { mVelocityX = velocityX; }
    public void setVelocityY(float velocityY) { mVelocityY = velocityY; }
    public void setAcceleration(float accelerationX, float accelerationY) { mAccelerationX = accelerationX; mAccelerationY = accelerationY;}
    public void setAccelerationX(float accelerationX) { mAccelerationX = accelerationX; }
    public void setAccelerationY(float accelerationY) { mAccelerationY = accelerationY; }
    protected void setModelMatrix(float[] modelMatrix) {
        mModelMatrix[0] = modelMatrix[0];mModelMatrix[1] = modelMatrix[1];
        mModelMatrix[2] = modelMatrix[2];mModelMatrix[3] = modelMatrix[3];
        mModelMatrix[4] = modelMatrix[4];mModelMatrix[5] = modelMatrix[5];
        mModelMatrix[6] = modelMatrix[6];mModelMatrix[7] = modelMatrix[7];
        mModelMatrix[8] = modelMatrix[8];mModelMatrix[9] = modelMatrix[9];
        mModelMatrix[10] = modelMatrix[10];mModelMatrix[11] = modelMatrix[11];
        mModelMatrix[12] = modelMatrix[12];mModelMatrix[13] = modelMatrix[13];
        mModelMatrix[14] = modelMatrix[14];mModelMatrix[15] = modelMatrix[15];
    }

    //Transformations
    public void translate(float deltaX, float deltaY){
        mPositionX += deltaX;
        mPositionY += deltaY;
    }

    public void scale(float deltaX, float deltaY){
        mScaleX += deltaX;
        mScaleY += deltaY;
    }

    public void rotate(float deltaRotation){
        mRotation += deltaRotation;
    }

}
