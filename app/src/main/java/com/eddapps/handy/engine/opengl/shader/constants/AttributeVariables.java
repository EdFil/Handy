package com.eddapps.handy.engine.opengl.shader.constants;

/**
 * Created by edgar on 21-11-2014.
 */
public enum AttributeVariables {

    in_Position(0, "in_Position"),
    in_TexCoordinate(1, "in_TexCoordinate");

    private int mHandle;
    private String mName;

    private AttributeVariables(int handle, String name) {
        mHandle = handle;
        mName = name;
    }

    public int getHandle() {
        return mHandle;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString(){
        return mName;
    }

}
