package com.vector.countdowntimer_demo;

/**
 * Created by Vector on 2016/8/25 0025.
 */
public abstract class SecondTimer extends SimpleTimer {
    public SecondTimer(long secondsInFuture) {
        super(secondsInFuture, 1);
    }
}
