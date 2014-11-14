package com.eddapps.handy.engine.objects.primitives;

import com.eddapps.handy.engine.objects.primitives.vbo.QuadVertexBufferObject;
import com.eddapps.handy.engine.objects.primitives.vbo.TriangleVertexBufferObject;
import com.eddapps.handy.engine.opengl.shaders.ShaderProgramManager;
import com.eddapps.handy.engine.opengl.shaders.programs.PositionColorShaderProgram;

/**
 * Created by edgar on 14-11-2014.
 */
public class Triangle extends Primitive {

    private final static String TAG = Triangle.class.getSimpleName();

    //References
    private final TriangleVertexBufferObject mVertexBufferObject;
    private final PositionColorShaderProgram mShaderProgram;

    public Triangle(){
        super(new TriangleVertexBufferObject(), ShaderProgramManager.getPositionColorShader());
        mShaderProgram = getShaderProgram();
        mVertexBufferObject = (TriangleVertexBufferObject)getVertexBufferObject();
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
