package com.eddapps.handy.engine.utils;

/**
 * Created by Edgar on 30/07/2014.
 */
public class FPSCounter {

    long[] _frameTime;
    int size;
    int index;

    public FPSCounter(int size){
        _frameTime = new long[size];
        for (int i = 0; i < size; i++) {
            _frameTime[i] = 0;
        }
        this.size = size;
        index = 0;
    }

    public void addFrameTime(long frameTime){
        _frameTime[index] = frameTime;
        frameTime = (frameTime + 1) % size;
    }

    public double getAverage(){
        long sum = 0;
        for (int i = 0; i < size; i++) {
            sum += _frameTime[i];
        }
        return sum/(float)size;
    }
}
