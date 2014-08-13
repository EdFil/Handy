package com.eddapps.handy.engine.objects;

/**
 * Created by Edgar on 11/08/2014.
 */
public class Sprite extends MovingObject {
    public static final float DEFAULT_SIZE_SPRITE = 0.01f;

    public static final float[] VERTEX_ARRAY =  new float[]{
            -DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
             DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
            -DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
             DEFAULT_SIZE_SPRITE, -DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
            -DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f,
             DEFAULT_SIZE_SPRITE,  DEFAULT_SIZE_SPRITE, 0.0f, 1.0f
    };

    public Sprite(){
        super();
        setVertexBuffer(VERTEX_ARRAY);
    }
}