package com.vector.countdowntimer_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private android.widget.Button btnstart;
    private android.widget.TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = (TextView) findViewById(R.id.tv);
        this.btnstart = (Button) findViewById(R.id.btn_start);


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

        tv.setText(Html.fromHtml(getString(R.string.grader,123)));


        btnstart.setText(Html.fromHtml(getString(R.string.grader,123)));


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                btnstart.setEnabled(false);

            }
        });
    }

    public SpannableString getColorSpannableString(String msg, int color) {
        SpannableString spannableString = new SpannableString(msg);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(colorSpan, 0, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
