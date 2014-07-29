package com.eddapps.handy;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class ObjectManager {

    private ArrayList<Sprite> _objectList;
    private int _objectListSize;

    ObjectManager(){
        _objectList = new ArrayList<Sprite>();
        _objectListSize = 0;
    }

    public void addObject(Sprite sprite){
        _objectListSize++;
        _objectList.add(sprite);
    }

    public void update(){
        //TODO: Figure out what makes garbage collector go all crazy
        for (int i = 0; i < _objectListSize ; i++) {
            _objectList.get(i).update();
        }
    }

    public void draw(){
        for (int i = 0; i < _objectListSize ; i++) {
            _objectList.get(i).draw();
        }
    }


}
