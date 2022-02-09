package com.example.triviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import com.google.android.material.button.MaterialButton;

public class Profile extends AppCompatActivity {

    ImageButton back;
    MaterialButton logout, editNote;
    TextView score, name, userName, changePass, bioProfile;
    ImageView avatar;
    Database db = new Database(this);
    Intent intent,intent2,intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
        clicked();
    }

    public void init(){
        String id = getIntent().getStringExtra("id");
        back = findViewById(R.id.backArrowPROFILE);
        logout = findViewById(R.id.logoutPROFILE);
        changePass = findViewById(R.id.changePassPROFILE);
        score = findViewById(R.id.scorePROFILE);
        name = findViewById(R.id.namePROFILE);
        userName = findViewById(R.id.usernamePROFILE);
        avatar = findViewById(R.id.picturePROFILE);
        editNote = findViewById(R.id.editNote);
        bioProfile = findViewById(R.id.bioPROFILE);

        name.setText(db.search_Id_name(id));

        int image =  Integer.parseInt(db.search_Image(id));

        if(image == 1 )
            avatar.setBackgroundResource(R.drawable.people4);
        else if (image == 2)
            avatar.setBackgroundResource(R.drawable.people1);
        else if (image == 3)
            avatar.setBackgroundResource(R.drawable.people2);
        else if (image == 4)
            avatar.setBackgroundResource(R.drawable.people3);
        else if (image == 5)
            avatar.setBackgroundResource(R.drawable.people5);
        else if (image == 6)
            avatar.setBackgroundResource(R.drawable.people6);

        float score_db = db.search_Score(id);
        if(score_db < 3)
            score.setTextColor(Color.RED);
        else
            score.setTextColor(Color.GREEN);

        score.setText(String.valueOf(score_db));
        userName.setText(db.search_Username(id));
        intent = new Intent(this, Signup.class);
        intent2 = new Intent(this, ChangePassword.class);
        intent3 = new Intent(this, editNote.class);

        bioProfile.setText(db.get_Notes(id));
        bioProfile.setMovementMethod(new ScrollingMovementMethod());
    }

    public void clicked(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                intent2.putExtra("id", id);
                startActivity(intent2);
            }
        });
        editNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                intent3.putExtra("id", id);
                startActivity(intent3);
                finish();
            }
        });
    }

}