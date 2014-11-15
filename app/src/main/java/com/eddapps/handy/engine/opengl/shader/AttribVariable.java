package com.eddapps.handy.engine.opengl.shader;

/**
 * Created by Edgar on 11/08/2014.
 */
public enum AttribVariable {
    in_Position(0, "in_Position"),
    in_TexCoordinate(1, "in_TexCoordinate");

    private int mHandle;
    private String mName;

    private AttribVariable(int handle, String name) {
        mHandle = handle;
        mName = name;
    }

    public int getHandle() {
        return mHandle;
    }

    public String getName() {
        return mName;
    }
}