package com.example.demoexamwear.wear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.demoexamwear.R;

import java.util.Timer;
import java.util.TimerTask;

public class Starter extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Starter.this, SignIn.class);
                startActivity(intent);
            }
        };
        timer.schedule(timerTask, 2000l);
    }
}