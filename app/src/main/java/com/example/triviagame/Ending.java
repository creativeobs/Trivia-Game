package com.example.triviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Ending extends AppCompatActivity {

    Intent intent;
    TextView score, answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        Float scored = Float.parseFloat(getIntent().getStringExtra("score"));
        int final_score = (int) (scored * 5);

        score = findViewById(R.id.scoreENDING);
        answered = findViewById(R.id.answeredEnding);
        answered.setText("You have answered: \n" + final_score + "/5");
        score.setText(getIntent().getStringExtra("score"));
        intent = new Intent(this, Menu.class);
        intent.putExtra("id", getIntent().getStringExtra("id"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

}