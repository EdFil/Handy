package com.eddapps.handy.engine.opengl.text;

import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.objects.primitive.Quad;

/**
 * Created by edgar on 19-11-2014.
 */
public class Text extends Quad {

    private String mText;

    public Text(){
        mText = "Example text";
    }

    public Text(String text, float positionX, float positionY){
        super(positionX, positionY);
        mText = text;
    }

    public String getText(){
        return mText;
    }
}
