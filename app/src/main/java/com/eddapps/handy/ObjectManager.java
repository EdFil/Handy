package com.eddapps.handy;

import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.objects.MovingObject;
import com.eddapps.handy.engine.objects.StaticObject;
import com.eddapps.handy.engine.objects.sprite.Sprite;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class ObjectManager {

    private ArrayList<StaticObject> mStaticObjectList;
    private ArrayList<MovingObject> mMovingObjectList;

    ObjectManager(){
        mMovingObjectList = new ArrayList<MovingObject>();
        mStaticObjectList = new ArrayList<StaticObject>();
    }

    public void addObject(Sprite sprite){

    }

    public void update(Camera camera){

    }

    public void draw(){

    }

    @Override
    public String toString(){
        return "";
    }

}
