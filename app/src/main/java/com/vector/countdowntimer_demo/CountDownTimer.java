//package com.vector.countdowntimer_demo;
//
//import android.os.Handler;
//import android.os.Message;
//import android.os.SystemClock;
//
///**
// * Created by didik on 2016/8/3.
// * 在系统的计时器上打补丁
// * =.=
// */
//public abstract class CountDownTimer {
//    public static final int NORMAL = 0;
//    public static final int FLOOR = 1;
//    public static final int SYSTEM = 2;
//    public static final int SYSTEM_FIX = 3;
//
//    private int mCount = 0;
//
//
//    private int mode = SYSTEM_FIX;
//
//    /**
//     * Millis since epoch when alarm should stop.
//     */
//    private final long mMillisInFuture;
//
//    /**
//     * The interval in millis that the user receives callbacks
//     */
//    private final long mCountdownInterval;
//
//    private long mStopTimeInFuture;
//
//    /**
//     * boolean representing if the timer was cancelled
//     */
//    private boolean mCancelled = false;
//
//    /**
//     * @param millisInFuture The number of millis in the future from the call
//     *   to {@link #start()} until the countdown is done and {@link #onFinish()}
//     *   is called.
//     * @param countDownInterval The interval along the way to receive
//     *   {@link #onTick(long)} callbacks.
//     */
//    public CountDownTimer(long millisInFuture, long countDownInterval, int mode) {
//        mMillisInFuture = millisInFuture;
//        mCountdownInterval = countDownInterval;
//        this.mode = mode;
//    }
//
//    /**
//     * Cancel the countdown.
//     */
//    public synchronized final void cancel() {
//        mCancelled = true;
//        mHandler.removeMessages(MSG);
//    }
//
//    /**
//     * Start the countdown.
//     */
//    public synchronized final CountDownTimer start() {
//        mCancelled = false;
//        if (mMillisInFuture <= 0) {
//            onFinish();
//            return this;
//        }
//        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
//        mHandler.sendMessage(mHandler.obtainMessage(MSG));
//        return this;
//    }
//
//
//    /**
//     * Callback fired on regular interval.
//     * @param millisUntilFinished The amount of time until finished.
//     */
//    public abstract void onTick(long millisUntilFinished);
//
//    /**
//     * Callback fired when the time is up.
//     */
//    public abstract void onFinish();
//
//
//    private static final int MSG = 1;
//
//
//    // handles counting down
//    private Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            synchronized (CountDownTimer.this) {
//                switch (mode) {
//                    case 0:
//                        //normal round 向上取整
//                        doNormal(mHandler, true);
//                        break;
//                    case 1:
//                        //floor 向下取整
//                        doNormal(mHandler, false);
//                        break;
//                    case 2:
//                        //system
//                        doSYSTEM(mHandler, false);
//                        break;
//                    case 3:
//                        //system fix
//                        doSYSTEM(mHandler, true);
//                        break;
//                }
//            }
//        }
//    };
//
//    /**
//     *
//     * @param handler
//     * @param isRound true 向上取整,正常状态
//     *                false 向下取整,不推荐使用
//     */
//    private void doNormal(Handler handler, boolean isRound) {
//        if (mCancelled) {
//            return;
//        }
//        if (!isRound) {
//            mCount++;
//        }
//        final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
//
//        if (millisLeft <= 0) {
//            if (isRound) {
//                onFinish();
//            }
//        } else if (millisLeft < mCountdownInterval) {
//            if (isRound) {
//                onTick(mCountdownInterval);
//                handler.sendMessageDelayed(handler.obtainMessage(MSG), millisLeft);
//            } else {
//                onFinish();
//            }
//        } else {
//            long lastTickStart = SystemClock.elapsedRealtime();
//            onTick(mMillisInFuture - mCountdownInterval * mCount);
//
//            long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
//
//            while (delay < 0) delay += mCountdownInterval;
//
//            handler.sendMessageDelayed(handler.obtainMessage(MSG), delay);
//        }
//        if (isRound) {
//            mCount++;
//        }
//    }
//
//
//    /**
//     * 系统默认的处理方式
//     * @param handler
//     */
//    private void doSYSTEM(Handler handler, boolean fix) {
//        if (mCancelled) {
//            return;
//        }
//
//        final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
//
//        if (millisLeft <= 0) {
//            onFinish();
//        } else if (millisLeft < mCountdownInterval) {
//            // no tick, just delay until done
//            if (fix) {
//                onTick(millisLeft);
//            }
//            handler.sendMessageDelayed(handler.obtainMessage(MSG), millisLeft);
//        } else {
//            long lastTickStart = SystemClock.elapsedRealtime();
//            onTick(millisLeft);
//
//            // take into account user's onTick taking time to execute
//            long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
//
//            // special case: user's onTick took more than interval to
//            // complete, skip to next interval
//            while (delay < 0) delay += mCountdownInterval;
//
//            handler.sendMessageDelayed(handler.obtainMessage(MSG), delay);
//        }
//    }
//
//}
