package com.eddapps.handy.engine.objects.primitive.vbo;

import android.opengl.GLES20;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;
import com.eddapps.handy.engine.opengl.vbo.utils.VBOUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by edgar on 13-11-2014.
 */
public class QuadVertexBufferObject extends VertexBufferObject {

    private static final int NUM_VERTICES = 8;

    public QuadVertexBufferObject(){
        mByteBuffer = ByteBuffer.allocateDirect(NUM_VERTICES * VBOUtils.NUM_BYTES_PER_FLOAT);
        mByteBuffer.order(ByteOrder.nativeOrder());
        mFloatBuffer = mByteBuffer.asFloatBuffer();
    }


    @Override
    public void onCreate() {
        final float X1 = -DEFAULT_X_SCALE/2.0f;
        final float Y1 = -DEFAULT_Y_SCALE/2.0f;
        final float X2 = -X1;
        final float Y2 = -Y1;

        //BottomRight
        mFloatBuffer.put(X2);
        mFloatBuffer.put(Y1);
        //TopRight
        mFloatBuffer.put(X2);
        mFloatBuffer.put(Y2);
        //BottomLeft
        mFloatBuffer.put(X1);
        mFloatBuffer.put(Y1);
        //TopLeft
        mFloatBuffer.put(X1);
        mFloatBuffer.put(Y2);

        mFloatBuffer.position(0);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void sendToHardware(){
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferID[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mFloatBuffer.capacity() * Float.SIZE / 8, mFloatBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 2 * Float.SIZE / 8, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

    }

    @Override
    public void unloadFromHardware() {

    }

    @Override
    public void draw() {
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mVertexBufferID[0]);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }

}
