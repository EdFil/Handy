package com.eddapps.handy;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class ObjectManager {

    private ArrayList<Sprite> _objectList;

    ObjectManager(){
        _objectList = new ArrayList<Sprite>();
    }

    public void addObject(Sprite sprite){
        _objectList.add(sprite);
    }

    public void update(){
        for (Sprite sprite : _objectList) {
            sprite.rotate(100.0f * Clock.getDelta());
            //sprite.translate(1.0f * Clock.getDelta(), 0.0f);
            sprite.update();
        }
    }

    public void draw(){
        for (Sprite sprite : _objectList)
            sprite.draw();
    }


}
