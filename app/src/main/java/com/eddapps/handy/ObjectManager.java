package com.eddapps.handy;

import android.content.Context;

import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.objects.primitive.Primitive;
import com.eddapps.handy.engine.objects.sprite.Sprite;
import com.eddapps.handy.engine.utils.Utilities;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class ObjectManager {

    private ArrayList<Primitive> mObjectList;

    ObjectManager(){
        mObjectList = new ArrayList<Primitive>();
    }

    public void addObject(Primitive entity){
        mObjectList.add(entity);
    }

    public void update(){
        for(Primitive primitive : mObjectList)
            primitive.update();
    }

    public void draw(){
        for(Primitive primitive : mObjectList)
            primitive.draw();
    }

    public int getNumObjects(){
        return mObjectList.size();
    }

    @Override
    public String toString(){
        return "";
    }

}
