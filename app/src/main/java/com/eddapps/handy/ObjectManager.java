package com.eddapps.handy;

import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.objects.GameObject;
import com.eddapps.handy.engine.objects.MovingObject;
import com.eddapps.handy.engine.objects.Sprite;
import com.eddapps.handy.engine.objects.StaticObject;

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
        if(sprite instanceof MovingObject)
            mMovingObjectList.add(sprite);
    }

    public void update(Camera camera){
        for (GameObject gameObject : mMovingObjectList) {
            gameObject.update();
        }
    }

    public void draw(){
        for (GameObject gameObject : mStaticObjectList) {
            gameObject.draw();
        }
        for (GameObject gameObject : mMovingObjectList) {
            gameObject.draw();
        }
    }

    @Override
    public String toString(){
        return "ObjectManager: (S=" + mStaticObjectList + ")(M=" + mMovingObjectList + ")";
    }

}
