package com.eddapps.handy.engine.objects.collisions;

/**
 * Created by Edgar on 12/08/2014.
 */
public class AABB {

    public float mLowerBoundX, mLowerBoundY;
    public float mUpperBoundX, mUpperBoundY;

    public AABB(final AABB other){
        mLowerBoundX = other.mLowerBoundX;
        mLowerBoundY = other.mLowerBoundY;
        mUpperBoundX = other.mUpperBoundX;
        mUpperBoundY = other.mUpperBoundY;
    }

    public AABB(float lowerBoundX, float lowerBoundY, float upperBoundX, float upperBoundY){
        mLowerBoundX = lowerBoundX;
        mLowerBoundY = lowerBoundY;
        mUpperBoundX = upperBoundX;
        mUpperBoundY = upperBoundY;
    }

    public final boolean contains(final AABB other) {
        return mLowerBoundX <= other.mLowerBoundX && mLowerBoundY <= other.mLowerBoundY
                && other.mUpperBoundX <= mUpperBoundX && other.mUpperBoundY <= mUpperBoundY;
    }

    @Override
    public final String toString() {
        return "AABB[(" + mLowerBoundX + ", " + mLowerBoundY + ") . (" + mUpperBoundX + ", " + mUpperBoundY + ")]";
    }
}
