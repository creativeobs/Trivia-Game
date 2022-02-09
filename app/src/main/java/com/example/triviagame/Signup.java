package com.example.triviagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView register, forgotPassword;
    Database db = new Database(this);
    Intent intent, intent2, intent3;
    AlertDialog.Builder dialog;


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        clicked();
    }

    public void init(){
        intent = new Intent(this, Menu.class);
        intent2 = new Intent(this, Registration.class);
        intent3 = new Intent(this, forgotPassword.class);
        username = findViewById(R.id.etUsernameLOGIN);
        password = findViewById(R.id.etPasswordLOGIN);
        login = findViewById(R.id.btnLoginSIGNUP);
        register = findViewById(R.id.tvRegisterSIGNUP);
        forgotPassword = findViewById(R.id.forgotPassword);
        username.requestFocus();
    }

    public void clicked(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
                username.requestFocus();
                username.setText("");
                password.setText("");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = db.login(username.getText().toString(), password.getText().toString());
                if (result != 0) {
                    intent.putExtra("id", String.valueOf(result));
                    startActivity(intent);
                    finish();
                    return;
                }
                displayMessage("Warning", "Username/Password Combination Does not exist!");
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent3);
            }
        });
    }

    public void displayMessage(String title, String message){
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }
}