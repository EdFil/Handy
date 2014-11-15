package com.eddapps.handy.engine.objects.sprite;

import android.opengl.GLES20;

import com.eddapps.handy.engine.objects.primitive.Quad;
import com.eddapps.handy.engine.objects.sprite.vbo.SpriteVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorTextureShaderProgram;

/**
 * Created by edgar on 14-11-2014.
 */
public class Sprite extends Quad {

    private final PositionColorTextureShaderProgram mShaderProgram;
    public int mTextureHandle;

    public Sprite() {
        super(new SpriteVertexBufferObject(), ShaderProgramManager.getPositionColorTextureShader());
        mShaderProgram = (PositionColorTextureShaderProgram)getShaderProgram();
    }

    @Override
    public void uniformSets(){
        super.uniformSets();
        mShaderProgram.setTexture(0);
    }

    @Override
    public void draw(){
        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureHandle);

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(mTextureHandle, 0);
        super.draw();
    }
}
