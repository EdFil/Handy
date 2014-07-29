package com.eddapps.handy;

import android.os.SystemClock;

/**
 * Created by edgar on 28-07-2014.
 */
public class Clock {

    private static Clock instance = null;

    public float deltaTime;
    public long prevTime;
    public long currentTime;

    protected Clock(){
        deltaTime = 0;
        prevTime = 0;
        currentTime = SystemClock.elapsedRealtime();
    }

    public static Clock init() {
        if(instance == null)
            instance = new Clock();
        return instance;
    }

    public static float getDelta(){
        return instance.deltaTime;
    }

    public static void update(){
        instance.prevTime = instance.currentTime;
        instance.currentTime = SystemClock.elapsedRealtime();
        instance.deltaTime = (instance.currentTime - instance.prevTime)/1000.0f;
    }

}




