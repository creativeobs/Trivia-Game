package com.example.triviagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class ChangePassword extends AppCompatActivity {

    TextView currentPass,newPass,renewPass;
    MaterialButton  button;
    Database db = new Database(this);
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        init();
        clicked();
    }

    public void init(){
        currentPass = findViewById(R.id.etPasswordCurrentForgot);
        newPass = findViewById(R.id.etPasswordForgot);
        renewPass = findViewById(R.id.etPasswordagainForgot);
        button = findViewById(R.id.buttonChangePass);
    }

    public void clicked(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("id");
                String errors = checker(currentPass.getText().toString(), newPass.getText().toString(), renewPass.getText().toString(), "Password");
                if(!errors.isEmpty()){
                    displayMessage("Warning", errors);
                    return;
                }
                db.updatePassword(id, newPass.getText().toString());
                displayMessage("Success!", "Password Modified Successfully!");
                finish();
            }
        });
    }

    public String checker(String currentInput, String input, String inputre, String name){
        String id = getIntent().getStringExtra("id");
        String errors = "";
        int space =0, min=0, max=0;

        for(int x = 0;  x < input.length(); x++){
            if(input.charAt(x) == ' ' && space == 0){
                errors += (errors.isEmpty()) ? name+"\nNo space" : "\nNo space";
                space =1;
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

        if(!input.equals(inputre))
            errors += (errors.isEmpty()) ? name+"\nPasswords do not match" : "\nPasswords do not match";

        if(!db.search_Password(id).equals(currentInput))
            errors += (errors.isEmpty()) ? name+"\nCurrent Password does not match" : "\nCurrent Password does not match";

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