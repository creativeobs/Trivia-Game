package com.example.triviagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoards extends AppCompatActivity {

    Database db = new Database(this);
    ArrayList<Members> members = new ArrayList<>();
    Cursor cursor = null;
    TextView usernameTV;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_boards);

        init();
    }

    public void init(){
        recyclerView = findViewById(R.id.recycler_viewLeaderBoards);
        usernameTV = findViewById(R.id.username_recyclerview_layout);
        displayData();
        Collections.sort(members, new Comparator<Members>() {
            @Override
            public int compare(Members o1, Members o2) {
                return Float.valueOf(o2.score).compareTo(o1.score);
            }
        });
        customAdapter = new CustomAdapter(this, members);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void displayData(){
        cursor = db.getAllData();
        while(cursor.moveToNext()){
            members.add(new Members(cursor.getString(3),
                    cursor.getString(1),
                    Float.parseFloat(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5))));
        }
    }
}