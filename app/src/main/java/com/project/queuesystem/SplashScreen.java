package com.project.queuesystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    private final static int EXIT_CODE = 100;

    ImageView appLogo;
    ImageView textLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        appLogo = findViewById(R.id.app_logo);
        textLogo = findViewById(R.id.text_logo);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
        appLogo.setAnimation(animation);
        textLogo.setAnimation(animation);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    GotoMainActivity();
                }
            }
        });
        thread.start();
    }


    private void GotoMainActivity() {
        startActivityForResult(new Intent(SplashScreen.this, MainActivity.class), EXIT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXIT_CODE){

            if (resultCode == RESULT_OK){
                if (data.getBooleanExtra("EXIT",true)){
                    finish();
                }
            }
        }


    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("BUGBUG","onStop( in SplashScreen");
        finish();
    }
}