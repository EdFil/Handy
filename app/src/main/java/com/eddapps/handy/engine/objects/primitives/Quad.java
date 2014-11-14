package com.eddapps.handy.engine.objects.primitives;

import android.util.Log;

import com.eddapps.handy.engine.objects.primitives.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.opengl.shaders.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shaders.programs.PositionColorShaderProgram;
import com.eddapps.handy.engine.opengl.shaders.programs.ShaderProgram;
import com.eddapps.handy.engine.opengl.vbo.VertexBufferObject;
import com.eddapps.handy.engine.utils.Clock;

/**
 * Created by edgar on 13-11-2014.
 */
public class Quad extends Primitive {

    private final static String TAG = Quad.class.getSimpleName();

    //References
    private final QuadVertexBufferObject mVertexBufferObject;
    private final PositionColorShaderProgram mShaderProgram;

    public Quad(){
        super(new QuadVertexBufferObject(), ShaderProgramManager.getPositionColorShader());
        mShaderProgram = getShaderProgram();
        mVertexBufferObject = (QuadVertexBufferObject)getVertexBufferObject();
        mVertexBufferObject.updateBuffer(this);
    }

    public void update(){
        //rotate(10 * Clock.getDelta());
        reloadModelMatrix();
    }

    public void draw(){
        super.draw();
    }

}
