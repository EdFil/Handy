package com.eddapps.handy;

import android.content.Context;

import com.eddapps.handy.engine.Engine;
import com.eddapps.handy.engine.cameras.Camera;
import com.eddapps.handy.engine.objects.Entity;
import com.eddapps.handy.engine.objects.primitive.Primitive;
import com.eddapps.handy.engine.objects.sprite.Sprite;
import com.eddapps.handy.engine.utils.Utilities;

import java.util.ArrayList;

/**
 * Created by Edgar on 28-07-2014.
 */
public class Scene {

    private final Engine mEngine;

    private ArrayList<Entity> mEntities;

    public Scene(final Engine engine){
        mEngine = engine;
        mEntities = new ArrayList<Entity>();
    }

    public void addEntity(Entity entity){

    }

}
