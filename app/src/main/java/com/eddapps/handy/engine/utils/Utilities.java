package com.eddapps.handy.engine.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

import com.eddapps.handy.engine.opengl.shader.AttribVariable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Edgar on 11/08/2014.
 */
public class Utilities {

    public static final int BYTES_PER_FLOAT = 4;
    private static final String TAG = "Utilities";

    public static int createProgram(int vertexShaderHandle, int fragmentShaderHandle, AttribVariable[] variables) {
        int  mProgram = GLES20.glCreateProgram();

        if (mProgram != 0) {
            GLES20.glAttachShader(mProgram, vertexShaderHandle);
            GLES20.glAttachShader(mProgram, fragmentShaderHandle);

            for (AttribVariable var: variables) {
                GLES20.glBindAttribLocation(mProgram, var.getHandle(), var.getName());
            }

            GLES20.glLinkProgram(mProgram);

            final int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(mProgram, GLES20.GL_LINK_STATUS, linkStatus, 0);

            if (linkStatus[0] == 0)
            {
                Log.v(TAG, GLES20.glGetProgramInfoLog(mProgram));
                GLES20.glDeleteProgram(mProgram);
                mProgram = 0;
            }
        }

        if (mProgram == 0)
        {
            throw new RuntimeException("Error creating program.");
        }
        return mProgram;
    }

    public static int loadShader(int type, String shaderCode){
        int shaderHandle = GLES20.glCreateShader(type);

        if (shaderHandle != 0)
        {
            GLES20.glShaderSource(shaderHandle, shaderCode);
            GLES20.glCompileShader(shaderHandle);

            // Get the compilation status.
            final int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

            // If the compilation failed, delete the shader.
            if (compileStatus[0] == 0)
            {
                Log.v(TAG, "Shader fail info: " + GLES20.glGetShaderInfoLog(shaderHandle));
                GLES20.glDeleteShader(shaderHandle);
                shaderHandle = 0;
            }
        }


        if (shaderHandle == 0)
        {
            throw new RuntimeException("Error creating shader " + type);
        }
        return shaderHandle;
    }

    public static FloatBuffer newFloatBuffer(float[] verticesData) {
        FloatBuffer floatBuffer;
        floatBuffer = ByteBuffer.allocateDirect(verticesData.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        floatBuffer.put(verticesData).position(0);
        return floatBuffer;
    }

    public static void checkGLErrors(String tag) {
        int err;
        while ((err = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(tag, "OpenGL error " + err + ": " + GLU.gluErrorString(err));
        }
    }

    public static int loadTexture(Context context, final int resourceID){
        int[] textureHandle = new int[1];
        GLES20.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] != 0)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;   // No pre-scaling

            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceID, options);

            // Bind to the texture in OpenGL
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            GLES20.glEnable(GLES20.GL_BLEND);
            GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

            // Set filtering
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            // Load the bitmap into the bound texture.
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();
        }

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }

        return textureHandle[0];
    }
}
