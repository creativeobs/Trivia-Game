package com.example.triviagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Registration extends AppCompatActivity {

    EditText username, name, password, repassword;
    Button login;
    Database db = new Database(this);
    AlertDialog.Builder dialog;
    ImageView im1,im2,im3,im4,im5,im6;
    int avatar_num = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        clicked();
    }

    public void init(){

        username = findViewById(R.id.etUsernameREGISTER);
        name = findViewById(R.id.etNameREGISTER);
        password = findViewById(R.id.etPasswordREGISTER);
        repassword = findViewById(R.id.etPasswordREREGISTER);
        login = findViewById(R.id.btnREGISTER);
        username.requestFocus();

        im1 = findViewById(R.id.imageView);
        im2 = findViewById(R.id.imageView2);
        im3 = findViewById(R.id.imageView3);
        im4 = findViewById(R.id.imageView4);
        im5 = findViewById(R.id.imageView5);
        im6 = findViewById(R.id.imageView6);

        intent = new Intent(this, Signup.class);
    }

    public void clicked(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errors = "";
                if(!password.getText().toString().equals(repassword.getText().toString())){
                    displayMessage("Warning", "Passwords do not match!");
                    return;
                }
                if(avatar_num == 0){
                    displayMessage("Warning", "Choose an avatar");
                    return;
                }

                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || name.getText().toString().isEmpty()){
                    displayMessage("Warning", "Please enter all fields!");
                    return;
                }

                errors += (errors.isEmpty()) ? checker(username.getText().toString(), "Username") : "\n\n" + checker(username.getText().toString(), "Username");
                errors += (errors.isEmpty()) ? checker(name.getText().toString(), "Name") : "\n\n" + checker(name.getText().toString(), "Name");
                errors += (errors.isEmpty()) ? checker(password.getText().toString(), "Password") : "\n\n" + checker(password.getText().toString(), "Password");

                if(!errors.isEmpty()){
                    displayMessage("Warning", errors);
                    return;
                }
                boolean result = db.search_Name(username.getText().toString());
                if (result == true){
                    displayMessage("Warning", "Username already Taken!");
                    return;
                }

                result = db.add_record(username.getText().toString(), password.getText().toString(), name.getText().toString(), avatar_num);
                displayMessage("Message", "Registered Successfully!");
                startActivity(intent);
                avatar_num = 0;
                finish();
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.WHITE);
                im2.setBackgroundColor(Color.TRANSPARENT);
                im3.setBackgroundColor(Color.TRANSPARENT);
                im4.setBackgroundColor(Color.TRANSPARENT);
                im5.setBackgroundColor(Color.TRANSPARENT);
                im6.setBackgroundColor(Color.TRANSPARENT);
                avatar_num = 1;
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.TRANSPARENT);
                im2.setBackgroundColor(Color.WHITE);
                im3.setBackgroundColor(Color.TRANSPARENT);
                im4.setBackgroundColor(Color.TRANSPARENT);
                im5.setBackgroundColor(Color.TRANSPARENT);
                im6.setBackgroundColor(Color.TRANSPARENT);
                avatar_num = 2;
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.TRANSPARENT);
                im2.setBackgroundColor(Color.TRANSPARENT);
                im3.setBackgroundColor(Color.WHITE);
                im4.setBackgroundColor(Color.TRANSPARENT);
                im5.setBackgroundColor(Color.TRANSPARENT);
                im6.setBackgroundColor(Color.TRANSPARENT);
                avatar_num = 3;
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.TRANSPARENT);
                im2.setBackgroundColor(Color.TRANSPARENT);
                im3.setBackgroundColor(Color.TRANSPARENT);
                im4.setBackgroundColor(Color.WHITE);
                im5.setBackgroundColor(Color.TRANSPARENT);
                im6.setBackgroundColor(Color.TRANSPARENT);
                avatar_num = 4;
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.TRANSPARENT);
                im2.setBackgroundColor(Color.TRANSPARENT);
                im3.setBackgroundColor(Color.TRANSPARENT);
                im4.setBackgroundColor(Color.TRANSPARENT);
                im5.setBackgroundColor(Color.WHITE);
                im6.setBackgroundColor(Color.TRANSPARENT);
                avatar_num = 5;
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setBackgroundColor(Color.TRANSPARENT);
                im2.setBackgroundColor(Color.TRANSPARENT);
                im3.setBackgroundColor(Color.TRANSPARENT);
                im4.setBackgroundColor(Color.TRANSPARENT);
                im5.setBackgroundColor(Color.TRANSPARENT);
                im6.setBackgroundColor(Color.WHITE);
                avatar_num = 6;
            }
        });
    }

    public String checker(String input, String name){
        String errors = "";
        int space =0, num=0, min=0, max=0;

        for(int x = 0;  x < input.length(); x++){
            if(input.charAt(x) == ' ' && space == 0){
                errors += (errors.isEmpty()) ? name+"\nNo space" : "\nNo space";
                space =1;
            }
            if(name == "Name"){
                if(Character.isDigit(input.charAt(x)) && num == 0){
                    errors += (errors.isEmpty()) ? name+"\nNo numbers" : "\nNo numbers";
                    num = 1;
                }
            }
            if(input.length() < 3 && min == 0){
                errors += (errors.isEmpty()) ? name+"\ncharacters should not be less than 3" : "\ncharacters should not be  less than 3";
                min =1;
            }
            if(input.length() > 10 && max == 0){
                errors += (errors.isEmpty()) ? name+"\ncharacters should not be greater than 10" : "\ncharacters should not be greater than 10";
                max = 1;
            }
        }
        return  errors;
    }

    public void displayMessage(String title, String message){
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }
}