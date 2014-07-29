package com.eddapps.handy;

import android.opengl.GLES20;
import android.renderscript.Matrix4f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Edgar on 09/07/2014.
 */
public class Sprite {
    private static final float DEFAULT_SIZE_SPRITE = 0.1f;

    private float _positionX, _positionY;
    private float _scaleX, _scaleY;
    private float _rotation;

    private Matrix4f _modelMatrix;
    private int[] _vertexBufferID;

    public Sprite(){
//        _positionX = _positionY = 0.0f;
        _positionX = (float) (Math.random() - 0.5f) * 3.0f;
        _positionY = (float) (Math.random() - 0.5f) * 2.0f;
        _scaleX = _scaleY = 1.0f;
        _rotation = 0.0f;
        _modelMatrix = new Matrix4f();
        createVertexBuffer();
    }

    private void createVertexBuffer(){
        //Create the vertices
        float[] vertices = new float[]{
                -DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
                 DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
                -DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
                 DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
                -DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
                 DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f
        };

        //Create the vertexBuffer
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4); //4 bytes per float
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        //Send the vertexBuffer to OpenGL
        _vertexBufferID = new int[1];
        GLES20.glGenBuffers(1, _vertexBufferID, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, _vertexBufferID[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertices.length * Float.SIZE / 8, vertexBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(0, 4, GLES20.GL_FLOAT, false, 4 * Float.SIZE / 8, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    public void draw(){
        ShaderProgramManager.getDefaultShader().useProgram();
        ShaderProgramManager.getDefaultShader().setModelMatrix(_modelMatrix.getArray());
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, _vertexBufferID[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    public void update(){
        _modelMatrix.loadIdentity();
        _modelMatrix.translate(_positionX, _positionY, 0.0f);
        _modelMatrix.scale(_scaleX, _scaleY, 1.0f);
        _modelMatrix.rotate(_rotation, 0.0f, 0.0f, 1.0f);
    }

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
}
