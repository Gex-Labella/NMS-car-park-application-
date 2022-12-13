package com.lizyaver.lizyaverorg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.lizyaver.lizyaverorg.ui.login.LoginActivity;

public class Splash_Screen extends AppCompatActivity    {

    private static int SPLASH_TIMER=3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_splashscreen);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent( Splash_Screen.this, LoginActivity.class);
                startActivity(intent);
                finish();


            }
        },SPLASH_TIMER);



    }
}