package com.eddapps.handy.engine.opengl.vbo;

import android.opengl.GLES20;
import android.util.Log;

import com.eddapps.handy.engine.objects.primitive.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.objects.primitive.vbo.TriangleVertexBufferObject;
import com.eddapps.handy.engine.objects.sprite.vbo.SpriteVertexBufferObject;
import com.eddapps.handy.engine.utils.Pair;
import com.eddapps.handy.engine.utils.Utilities;

import java.util.HashMap;

/**
 * Created by edgar on 19-11-2014.
 */
public class VertexBufferObjectManager {

    private static final String TAG = VertexBufferObject.class.getSimpleName();

    private HashMap<String, Pair<Integer, Integer>> mLoadedVertexBufferObjects;
    private HashMap<String, Pair<Integer, Integer>> mVertexBufferObjectsToBeLoaded;

    public VertexBufferObjectManager(){
        mLoadedVertexBufferObjects = new HashMap<String, Pair<Integer, Integer>>();
        mVertexBufferObjectsToBeLoaded = new HashMap<String, Pair<Integer, Integer>>();
    }

    public int loadVertexBufferObject(Class vertexBufferObjectClass){

        if(!validClass(vertexBufferObjectClass))
            return -1;


        if(mLoadedVertexBufferObjects.containsKey(vertexBufferObjectClass.getName())){
            Log.d(TAG, vertexBufferObjectClass.getSimpleName() + " was already loaded.");
            mLoadedVertexBufferObjects.get(vertexBufferObjectClass.getName()).second++;
            return mLoadedVertexBufferObjects.get(vertexBufferObjectClass.getName()).first;
        }
        if(mVertexBufferObjectsToBeLoaded.containsKey(vertexBufferObjectClass.getName())){
            Log.d(TAG, vertexBufferObjectClass.getSimpleName() + " was already set to be loaded.");
            mVertexBufferObjectsToBeLoaded.get(vertexBufferObjectClass.getName()).second++;
        }else{
            Log.d(TAG, vertexBufferObjectClass.getSimpleName() + " is being prepared to be loaded.");
            int[] mVertexBufferID = new int[1];
            GLES20.glGenBuffers(1, mVertexBufferID, 0);
            mVertexBufferObjectsToBeLoaded.put(vertexBufferObjectClass.getName(), new Pair<Integer, Integer>(mVertexBufferID[0], 1));
        }

        return mVertexBufferObjectsToBeLoaded.get(vertexBufferObjectClass.getName()).first;
    }

    public void loadUnloadedVBO(){
        if(mVertexBufferObjectsToBeLoaded.size() > 0) {
            Log.d(TAG, "Loading " + mLoadedVertexBufferObjects.size() + " unloaded textures...");
            for (String className : mVertexBufferObjectsToBeLoaded.keySet()) {
                VertexBufferObject vertexBufferObject;
                vertexBufferObject = getClassForName(className);
                if(vertexBufferObject != null) {
                    Log.d(TAG, "Loading " + vertexBufferObject.getClass().getSimpleName() + ".");
                    vertexBufferObject.sendToHardware();
                }
                else {
                    Log.e(TAG, "Error loading " + className + ".");
                    mVertexBufferObjectsToBeLoaded.remove(className);
                }
            }
            Log.d(TAG, "Finished loading textures...");
            mVertexBufferObjectsToBeLoaded.putAll(mLoadedVertexBufferObjects);
            mVertexBufferObjectsToBeLoaded.clear();
        }
    }

    private boolean validClass(Class vertexBufferObjectClassName) {
        return vertexBufferObjectClassName.equals(QuadVertexBufferObject.class)     ||
               vertexBufferObjectClassName.equals(TriangleVertexBufferObject.class) ||
               vertexBufferObjectClassName.equals(SpriteVertexBufferObject.class);
    }

    private VertexBufferObject getClassForName(String vertexBufferObjectClassName){
        try {
            return (VertexBufferObject)Class.forName(vertexBufferObjectClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
