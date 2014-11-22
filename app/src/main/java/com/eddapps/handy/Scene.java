package com.eddapps.handy;

import com.eddapps.handy.engine.Engine;
import com.eddapps.handy.engine.objects.Entity;

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
