package com.vector.countdowntimer_demo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by Vector on 2016/8/25 0025.
 */
public abstract class SimpleTimer extends CountDownTimer {
    private final long mCell;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public SimpleTimer(long secondsInFuture, long countDownInterval) {
        super(secondsInFuture * 1000, countDownInterval * 1000);
        this.mCell = countDownInterval;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int i = (int) Math.ceil(millisUntilFinished * 1.0f / 1000);
        if (i == 2 * mCell) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onTick(mCell);
                }
            }, mCell * 1000);
        }
        onTicker(i);
    }

    protected abstract void onTicker(long second);
}
