package com.eddapps.handy.engine.objects.sprite;

import android.opengl.GLES20;

import com.eddapps.handy.engine.objects.primitive.Quad;
import com.eddapps.handy.engine.objects.sprite.vbo.SpriteVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shader.programs.PositionColorTextureShaderProgram;
import com.eddapps.handy.engine.opengl.texture.Texture;
import com.eddapps.handy.engine.opengl.texture.TextureManager;

/**
 * Created by edgar on 14-11-2014.
 */
public class Sprite extends Quad {

    private Texture mTexture;
    private PositionColorTextureShaderProgram mShaderProgram;

    public Sprite() {
        super(new SpriteVertexBufferObject(), ShaderProgramManager.getPositionColorTextureShader());
        mShaderProgram = (PositionColorTextureShaderProgram)getShaderProgram();
        mTexture = new Texture();
    }

    public void setTexture(TextureManager textureManager, int resourceID){
        mTexture.loadTextureFromResource(textureManager, resourceID);
    }


    @Override
    public void uniformSets(){
        super.uniformSets();
        mShaderProgram.setTexture(0);
    }

    @Override
    public void draw(){
        mTexture.bind();
        super.draw();
    }
}
