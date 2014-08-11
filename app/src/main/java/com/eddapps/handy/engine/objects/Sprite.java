package com.eddapps.handy.engine.objects;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.eddapps.handy.engine.shaders.ShaderProgramManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Edgar on 11/08/2014.
 */
public class Sprite extends GameObject {
    public static final float DEFAULT_SIZE_SPRITE = 0.01f;

    private int[] _vertexBufferID;

    public Sprite(){
        super();
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

    @Override
    public void draw(){
        ShaderProgramManager.getDefaultShader().useProgram();
        ShaderProgramManager.getDefaultShader().setModelMatrix(getModelMatrix());
        ShaderProgramManager.getDefaultShader().setColor(getColor());
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, _vertexBufferID[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    public void update(){
//        translate(getDirectionX() * getVelocityX(), getDirectionY() * getVelocityY());
        Matrix.setIdentityM(getModelMatrix(), 0);
        Matrix.translateM(getModelMatrix(), 0, getPositionX(), getPositionY(), 0.0f);
        Matrix.rotateM(getModelMatrix(), 0, getRotation(), 0, 0, 1);
        Matrix.scaleM(getModelMatrix(), 0, getScaleX(), getScaleY(), 1.0f);
    }
}