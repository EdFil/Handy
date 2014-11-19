package com.eddapps.handy.engine.opengl.vbo;

import com.eddapps.handy.engine.objects.Entity;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by edgar on 14-11-2014.
 */
public abstract class VertexBufferObject {

    protected static float DEFAULT_X_SCALE = 1.0f;
    protected static float DEFAULT_Y_SCALE = 1.0f;

    protected int mVertexBufferID[];
    protected ByteBuffer mByteBuffer;
    protected FloatBuffer mFloatBuffer;

    public abstract void sendToHardware();
    public abstract void unloadFromHardware();

    public abstract void draw();


}
