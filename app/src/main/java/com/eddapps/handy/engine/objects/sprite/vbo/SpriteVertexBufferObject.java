package com.eddapps.handy.engine.objects.sprite.vbo;

import android.opengl.GLES20;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by edgar on 15-11-2014.
 */
public class SpriteVertexBufferObject extends VertexBufferObject {


    public SpriteVertexBufferObject(){
        mByteBuffer = ByteBuffer.allocateDirect(16 * 4); // 8 floats, 4 bytes per float
        mByteBuffer.order(ByteOrder.nativeOrder());
        mFloatBuffer = mByteBuffer.asFloatBuffer();
    }

    @Override
    public void updateBuffer(Entity entity){
        final float X1 = -entity.getScaleX()/2.0f;
        final float Y1 = -entity.getScaleY()/2.0f;
        final float X2 = -X1;
        final float Y2 = -Y1;

        //BottomRight
        mFloatBuffer.put(X2); //Position X
        mFloatBuffer.put(Y1); //Position Y
        mFloatBuffer.put(0);  //TexCoord X
        mFloatBuffer.put(1);  //TexCoord Y
        //TopRight
        mFloatBuffer.put(X2); //Position X
        mFloatBuffer.put(Y2); //Position Y
        mFloatBuffer.put(0);  //TexCoord X
        mFloatBuffer.put(0);  //TexCoord Y
        //BottomLeft
        mFloatBuffer.put(X1); //Position X
        mFloatBuffer.put(Y1); //Position Y
        mFloatBuffer.put(1);  //TexCoord X
        mFloatBuffer.put(1);  //TexCoord Y
        //TopLeft
        mFloatBuffer.put(X1); //Position X
        mFloatBuffer.put(Y2); //Position Y
        mFloatBuffer.put(1);  //TexCoord X
        mFloatBuffer.put(0);  //TexCoord Y

        mFloatBuffer.position(0);

        mVertexBufferID = new int[1];
        GLES20.glGenBuffers(1, mVertexBufferID, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferID[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mFloatBuffer.capacity() * Float.SIZE / 8, mFloatBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glEnableVertexAttribArray(0); //Vertex Position
        GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 4 * Float.SIZE / 8, 0);
        GLES20.glEnableVertexAttribArray(1); //TextCoord
        GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 4 * Float.SIZE / 8, 2 * Float.SIZE / 8);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

    }

    @Override
    public void draw() {
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferID[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
