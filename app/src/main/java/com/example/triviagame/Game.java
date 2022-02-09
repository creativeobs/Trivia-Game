package com.example.triviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView quest_num, question, timer, scored;
    RadioButton choice1,choice2,choice3,choice4;
    Questions qa = new Questions();
    Button next;
    RadioGroup rgroup;
    Database db = new Database(this);
    ImageView image;
    int question_counter = 0;
    MediaPlayer right, wrong;

    private static final long START_TIME_IN_MILLIS = 10000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    float score = 0;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
    }

    public void init(){
        quest_num = findViewById(R.id.question_numberGAME);
        question = findViewById(R.id.questionGAME);
        timer = findViewById(R.id.timerGAME);
        choice1 = findViewById(R.id.fchoiceGAME);
        choice2 = findViewById(R.id.schoiceGAME);
        choice3 = findViewById(R.id.tchoiceGAME);
        choice4 = findViewById(R.id.fochoiceGAME);
        next = findViewById(R.id.btnNEXTGAME);
        scored = findViewById(R.id.scoreGAME);
        intent = new Intent(this,Ending.class);
        rgroup = findViewById(R.id.radioGroup2);
        image = findViewById(R.id.imageView7);
        next.setVisibility(View.INVISIBLE);

        startQuiz();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(next.getText().toString().equals("Finish")){
                    String id = getIntent().getStringExtra("id");
                    String sScore = String.valueOf(score);
                    db.add_Score(id, sScore);
                    intent.putExtra("id", id);
                    intent.putExtra("score", sScore);
                    startActivity(intent);
                    finish();
                    if (mCountDownTimer != null) {
                        mCountDownTimer.cancel();
                    }
                }else{
                    next.setVisibility(View.INVISIBLE);
                    startQuiz();
                }
            }
        });
    }

    public void startQuiz(){
        quest_num.setText("Question: " + (question_counter+1));
        question.setText(qa.getQuestion(question_counter));
        choice1.setText(qa.getchoice1(question_counter));
        choice2.setText(qa.getchoice2(question_counter));
        choice3.setText(qa.getchoice3(question_counter));
        choice4.setText(qa.getchoice4(question_counter));
        startTimer();

        RadioButton rbSelected = findViewById(rgroup.getCheckedRadioButtonId());
        int answerQa = (question_counter == 0) ? Integer.parseInt(qa.getAnswer(question_counter)) : Integer.parseInt(qa.getAnswer(question_counter-1));
        RadioButton rightAnswer = findViewById(rgroup.getChildAt(answerQa-1).getId());
        try{
            rbSelected.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
        }
        rightAnswer.setBackgroundColor(Color.TRANSPARENT);


        rgroup.clearCheck();

        int image_selector = Integer.parseInt(qa.getImage(question_counter));
        if(image_selector == 1)
            image.setBackgroundResource(R.drawable.sample1);
        if(image_selector == 2)
            image.setBackgroundResource(R.drawable.sample2);
        if(image_selector == 3)
            image.setBackgroundResource(R.drawable.sample3);
        if(image_selector == 4)
            image.setBackgroundResource(R.drawable.sample4);
        if(image_selector == 5)
            image.setBackgroundResource(R.drawable.sample5);

    }

    public void checkAnswer(){
        RadioButton rbSelected = findViewById(rgroup.getCheckedRadioButtonId());
        int answerNr = rgroup.indexOfChild(rbSelected) + 1;
        int answerQa = Integer.parseInt(qa.getAnswer(question_counter));
        RadioButton rightAnswer = findViewById(rgroup.getChildAt(answerQa-1).getId());

        right = MediaPlayer.create(this, R.raw.right);
        wrong = MediaPlayer.create(this, R.raw.wrong);

        if(answerNr == answerQa){
            score += 0.2;
            scored.setText("SCORE: \n" + score);
            right.start();
            rbSelected.setBackgroundColor(Color.GREEN);
        }else{
            wrong.start();
            try{
                rbSelected.setBackgroundColor(Color.RED);
            }catch (Exception e){
            }
            rightAnswer.setBackgroundColor(Color.GREEN);
        }
        question_counter+=1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        right.release();
        wrong.release();
    }

    private void Timer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                checkAnswer();
                next.setVisibility(View.VISIBLE);

                if (question_counter < 5) {
                    next.setText("Next");
                } else {
                    next.setText("Finish");
                }
            }
        }.start();
        mTimerRunning = true;
    }

    private void startTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        Timer();
    }

    @Override
    public void onBackPressed() {
        finish();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }
}