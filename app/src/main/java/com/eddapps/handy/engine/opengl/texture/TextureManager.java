package com.eddapps.handy.engine.opengl.texture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.eddapps.handy.engine.utils.Pair;
import com.eddapps.handy.engine.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by edgar on 15-11-2014.
 */
public class TextureManager {

    private static final String TAG = TextureManager.class.getSimpleName();

    private HashMap<Integer, Pair<Integer, Integer>> mLoadedTextures;
    private HashMap<Integer, Pair<Integer, Integer>> mTexturesToBeLoaded;

    public TextureManager(){
        mLoadedTextures = new HashMap<Integer, Pair<Integer, Integer>>();
        mTexturesToBeLoaded = new HashMap<Integer, Pair<Integer, Integer>>();
    }

    public int loadTextureFromResource(final int resourceID){
        if(mLoadedTextures.containsKey(resourceID)){
            Log.d(TAG, resourceID + " was already loaded.");
            //If texture already loaded
            mLoadedTextures.get(resourceID).second++;
            return mLoadedTextures.get(resourceID).first;

        }
        if(mTexturesToBeLoaded.containsKey(resourceID)){
            Log.d(TAG, resourceID + " was already set to be loaded.");
            //If texture is going to be loaded already
            mTexturesToBeLoaded.get(resourceID).second++;
        }else{
            Log.d(TAG, resourceID + " is being prepared to be loaded.");
            //If never seen this resourceID
            int[] textureHandle = new int[1];
            GLES20.glGenTextures(1, textureHandle, 0);
            mTexturesToBeLoaded.put(resourceID, new Pair<Integer, Integer>(textureHandle[0], 1));
        }

        return mTexturesToBeLoaded.get(resourceID).first;
    }

    public void loadUnloadedTextures(Context context){
        if(mTexturesToBeLoaded.size() > 0) {
            Log.d(TAG, "Loading " + mTexturesToBeLoaded.size() + " unloaded textures...");
            for (int resourceID : mTexturesToBeLoaded.keySet()) {
                Log.d(TAG, "Loading " + resourceID + ".");
                Utilities.loadTexture(context, resourceID);
            }
            Log.d(TAG, "Finished loading textures...");
            mLoadedTextures.putAll(mTexturesToBeLoaded);
            mTexturesToBeLoaded.clear();
        }
    }
}
