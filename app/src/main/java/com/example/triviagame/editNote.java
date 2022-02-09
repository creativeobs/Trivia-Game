package com.example.triviagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;

public class editNote extends AppCompatActivity {

    ImageButton back;
    EditText note;
    MaterialButton submit;
    AlertDialog.Builder builder;
    Intent intent1;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        init();
        clicked();
    }

    public void init(){
        back = findViewById(R.id.backArrowNote);
        note = findViewById(R.id.editTextNote);
        submit = findViewById(R.id.submitNote);
        intent1 = new Intent(this, Profile.class);
        String id = getIntent().getStringExtra("id");
        note.setText(db.get_Notes(id));
    }

    public void clicked(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                intent1.putExtra("id",id);
                startActivity(intent1);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                String message =  note.getText().toString();
                if(message.isEmpty()){
                    displayMessage("Warning", "No notes added!");
                    return;
                }
                db.set_Notes(id, message);
                intent1.putExtra("id",id);
                displayMessage("Success!", "Successfully added!");
                startActivity(intent1);
                finish();
            }
        });
    }

    public void displayMessage(String title, String message){
        builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}