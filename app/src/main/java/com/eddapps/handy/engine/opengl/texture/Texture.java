package com.eddapps.handy.engine.opengl.texture;

import android.opengl.GLES20;

/**
 * Created by edgar on 16-11-2014.
 */
public class Texture {

    private int mTextureHandle;

    public Texture(){
        mTextureHandle = -1;
    }

    public void loadTextureFromResource(final TextureManager textureManager, final int resourceID){
        mTextureHandle = textureManager.loadTextureFromResource(resourceID);
    }

    public void bind(){
        if(mTextureHandle != -1) {
            // Set the active texture unit to texture unit 0.
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            // Bind the texture to this unit.
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureHandle);
            // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
            GLES20.glUniform1i(mTextureHandle, 0);
        }
    }

}
