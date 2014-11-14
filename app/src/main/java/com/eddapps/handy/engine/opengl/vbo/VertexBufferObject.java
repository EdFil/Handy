package com.eddapps.handy.engine.opengl.vbo;

import com.eddapps.handy.engine.objects.Entity;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Created by edgar on 14-11-2014.
 */
public abstract class VertexBufferObject {

    protected int mVertexBufferID[];
    protected ByteBuffer mByteBuffer;
    protected FloatBuffer mFloatBuffer;

    public abstract void updateBuffer(Entity entity);

    public abstract void draw();


}
