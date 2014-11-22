package com.eddapps.handy.engine.objects.sprite;

import com.eddapps.handy.engine.objects.primitive.Quad;
import com.eddapps.handy.engine.objects.sprite.vbo.SpriteVertexBufferObject;
import com.eddapps.handy.engine.opengl.shader.constants.UniformVariables;
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
        super(new SpriteVertexBufferObject(), new PositionColorTextureShaderProgram());
        mShaderProgram = (PositionColorTextureShaderProgram)getShaderProgram();
        mTexture = new Texture();
    }

    public void setTexture(TextureManager textureManager, int resourceID){
        mTexture.loadTextureFromResource(textureManager, resourceID);
    }


    @Override
    public void uniformSets(){
        super.uniformSets();
        mShaderProgram.setUniform(UniformVariables.TEXTURE, 1);
    }

    @Override
    public void draw(float[] viewMatrix, float[] projectionMatrix){
        mTexture.bind();
        super.draw(viewMatrix, projectionMatrix);
    }
}
