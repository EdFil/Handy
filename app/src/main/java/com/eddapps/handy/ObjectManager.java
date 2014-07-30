package com.eddapps.handy;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class ObjectManager {

    private ArrayList<GameObject> _objectList;
    private ArrayList<GameObject> _objectsToAdd;
    private boolean _isOnCycle;

    ObjectManager(){
        _isOnCycle = false;
        _objectList = new ArrayList<GameObject>();
        _objectsToAdd = new ArrayList<GameObject>();
    }

    public void addObject(Sprite sprite){
       if(!_isOnCycle)
           _objectList.add(sprite);
       else
           _objectsToAdd.add(sprite);
    }

    public void update(Camera camera){
        try {
            if (_objectsToAdd.size() > 0) {
                _objectList.addAll(_objectsToAdd);
                _objectsToAdd.clear();
            }
            _isOnCycle = true;
            for (GameObject gameObject : _objectList) {
                gameObject.collide(camera);
                gameObject.update();
            }
        }catch (Exception e) { Log.e("", "OI"); }
        //TODO: NEED to see this :S
    }

    public void draw(){
        for (GameObject gameObject : _objectList)
            gameObject.draw();
        _isOnCycle = false;
    }

    public void scaleAllObjects(float x) {
        for (GameObject gameObject : _objectList)
            gameObject.scale(x, x);
        for (GameObject gameObject : _objectsToAdd)
            gameObject.scale(x, x);
    }

    public int getNumObjects(){
        return _objectList.size();
    }

}
