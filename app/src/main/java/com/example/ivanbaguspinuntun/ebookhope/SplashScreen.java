package com.example.ivanbaguspinuntun.ebookhope;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class SplashScreen extends AppCompatActivity {

    private ImageView logoSplash;
    private Animation animation;
    private Thread loadingSplash;

    SharedPreferences se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        se = getSharedPreferences("MyPrefs", this.MODE_PRIVATE);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.show_logo);

        logoSplash = (ImageView) findViewById(R.id.imageview_logo);
        logoSplash.setAnimation(animation);
        Picasso.with(this).load(R.drawable.yourlogo).into(logoSplash);

        loadingSplash =new Thread(){
            public void run(){
                try{
                    sleep(2000); //set sleep 2 second
                }catch (Exception e){
                    e.printStackTrace(); //print if this thread is error
                }finally {

                    if (se.getBoolean("login", true)) {
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }

//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class); //set Intent
//                    startActivity(intent); //to start change activity
//                    finish(); //kill this activity when back press
                }
            }
        };
        loadingSplash.start(); //run this Thread
    }

}
