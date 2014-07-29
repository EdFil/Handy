package com.eddapps.handy;

/**
 * Created by edgar on 29-07-2014.
 */
public abstract class GameObject {

    private float _positionX, _positionY;
    private float _scaleX, _scaleY;
    private float _rotation;

    private float _directionX, _directionY;
    private float _velocityX, _velocityY;

    private float[] _modelMatrix;
    private float[] _color;

    public GameObject(){
        _positionX = (float) (Math.random() - 0.5f) * 3.0f;
        _positionY = (float) (Math.random() - 0.5f) * 2.0f;
        _velocityX = (float) (Math.random());
        _velocityY = (float) (Math.random());
        _directionX = (float) (Math.random() * 2 - 1f);
        _directionY = (float) (Math.random() * 2 - 1f);
        _color = new float[] {(float)Math.random(), (float)Math.random(), (float)Math.random(), 1.0f};
        _scaleX = _scaleY = 1.0f;
        _rotation = 0.0f;
        _modelMatrix = new float[16];
    }

    public abstract void update();
    public abstract void draw();

    public float[] getPosition() { return new float[] { _positionX, _positionY }; }
    public float getPositionX() { return _positionX; }
    public float getPositionY() { return _positionY; }
    public float getScaleX() { return _scaleX; }
    public float getScaleY() { return _scaleY; }
    public float getRotation() { return _rotation; }
    public float[] getColor(){ return _color; }
    public float getDirectionX() { return _directionX; }
    public float getDirectionY() { return _directionY; }
    public float getVelocityX() { return _velocityX; }
    public float getVelocityY() { return _velocityY; }
    protected float[] getModelMatrix() { return _modelMatrix; }

    public void setPosition(float x, float y){
        _positionX = x;
        _positionY = y;
    }

    public void setRotation(float rotation){
        _rotation = rotation;
    }

    public void setScale(float x, float y){
        _scaleX = x;
        _scaleY = y;
    }

    public void translate(float deltaX, float deltaY){
        _positionX += deltaX;
        _positionY += deltaY;
    }

    public void scale(float deltaX, float deltaY){
        _scaleX += deltaX;
        _scaleY += deltaY;
    }

    public void rotate(float deltaRotation){
        _rotation += deltaRotation;
    }

    public void collide(Camera camera){
        if((_positionX >= (camera.getPositionX() + camera.getRatio() - Sprite.DEFAULT_SIZE_SPRITE * _scaleX) && _directionX > 0) ||
            _positionX <= (camera.getPositionX() - camera.getRatio() + Sprite.DEFAULT_SIZE_SPRITE * _scaleY) && _directionX < 0)
            _directionX = -_directionX;
        else if ((_positionY >= (camera.getPositionY() + 1 - Sprite.DEFAULT_SIZE_SPRITE * _scaleX) && _directionY > 0) ||
                  _positionY <= (camera.getPositionY() - 1 - Sprite.DEFAULT_SIZE_SPRITE * _scaleY) && _directionY < 0)
            _directionY = - _directionY;
    }

}
