package com.example.triviagame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation top,bot;
    ImageView image;
    TextView text;
    Intent intent;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bot = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image = findViewById(R.id.header);
        text = findViewById(R.id.loadingText);
        image.setAnimation(top);
        text.setAnimation(bot);

        intent = new Intent(this, Signup.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}