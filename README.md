# CountDownTimer

重写官方的倒计时，使用简单

##使用方法

		//SimpleTimer一个构造函数是倒计时的总时间，第二个参数是Ticker的时间间隔

        SimpleTimer simpleTimer=new SimpleTimer(50L,1L) {
            @Override
            protected void onTicker(long second) {
                //每个ticker调用，时间间隔就是SimpleTimer的第二个构造参数，运行在UI线程
            }

            @Override
            public void onFinish() {
                //倒计时结束调用，运行在UI线程

            }
        };



        //SecondTimer时间间隔默认是一秒，所只有一个构造参数，就是倒计时总时间
        
        final SecondTimer timer = new SecondTimer(5) {
            @Override
            public void onTicker(long s) {
                Log.e(TAG, "onTick : " + s);
                btnstart.setText(Html.fromHtml(getString(R.string.grader,s)));
            }

            @Override
            public void onFinish() {
                Log.e(TAG, "onFinish  ");
                btnstart.setText(getString(R.string.time, 0));
                btnstart.setEnabled(true);
            }
        };