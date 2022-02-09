package com.example.triviagame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class forgotPassword extends AppCompatActivity {

    TextView newPass,renewPass, username;
    MaterialButton button;
    TextInputLayout layout1,layout2;
    Database db = new Database(this);
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        init();
        clicked();
    }

    public void init(){
        username = findViewById(R.id.etUsernameCurrentChange);
        newPass = findViewById(R.id.etPasswordChange);
        renewPass = findViewById(R.id.etPasswordagainChange);
        button = findViewById(R.id.buttonChangePassChange);
        layout1 = findViewById(R.id.textInputLayoutforgot);
        layout2 = findViewById(R.id.textInputLayout2forgot);
        newPass.setVisibility(View.INVISIBLE);
        renewPass.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
    }

    public void clicked(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = db.search_Username_ID(username.getText().toString());

                if(button.getText().equals("FIND")){
                    if(id.equals("")){
                        displayMessage("Warning", "No Username found!");
                        return;
                    }
                    button.setText("SUBMIT");
                    newPass.setVisibility(View.VISIBLE);
                    renewPass.setVisibility(View.VISIBLE);
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    username.setEnabled(false);
                }
                else if(button.getText().equals("SUBMIT")){
                    String errors = checker(newPass.getText().toString(), renewPass.getText().toString(), "Password");
                    if(!errors.isEmpty()){
                        displayMessage("Warning", errors);
                        return;
                    }
                    db.updatePassword(id, newPass.getText().toString());
                    displayMessage("Success!", "Password Modified Successfully!");
                    finish();
                }
            }
        });
    }

    public String checker(String input, String inputre, String name){
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