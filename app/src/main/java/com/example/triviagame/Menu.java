package com.example.triviagame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class Menu extends AppCompatActivity {

    TextView name, score;
    ImageButton profile;
    Database db = new Database(this);
    BottomNavigationView bottomNavigationView;
    NavController navController;
    Intent intent, intent2, intent3;
    String id = "";
    MaterialButton mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        clicked();
    }
    public void init(){
        name = findViewById(R.id.etWelcomeNameMENU);
        profile = findViewById(R.id.imgbtnProfileMENU);
        mb = findViewById(R.id.button);
        score = findViewById(R.id.textView2);
        id = getIntent().getStringExtra("id");
        name.setText("Welcome, " + db.search_Id_name(id));
        score.setText("Score: \n" + db.search_Score(id));

        int image =  Integer.parseInt(db.search_Image(id));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment_menu);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if(image == 1 )
            profile.setBackgroundResource(R.drawable.people4);
        else if (image == 2)
            profile.setBackgroundResource(R.drawable.people1);
        else if (image == 3)
            profile.setBackgroundResource(R.drawable.people2);
        else if (image == 4)
            profile.setBackgroundResource(R.drawable.people3);
        else if (image == 5)
            profile.setBackgroundResource(R.drawable.people5);
        else if (image == 6)
            profile.setBackgroundResource(R.drawable.people6);

        intent = new Intent(this, Profile.class);
        intent2 = new Intent(this, loading.class);
        intent3 = new Intent(this, LeaderBoards.class);
    }

    public void clicked(){
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent2.putExtra("id", id);
                startActivity(intent2);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.aboutFragment2:
                        mb.setEnabled(false);
                        setFragment(new aboutFragment());
                        return true;
                    case R.id.homeFragment2:
                        mb.setEnabled(true);
                        setFragment(new homeFragment());
                        return true;
                    case R.id.leaderBoardFragment:
                        startActivity(intent3);
                }
                return false;
            }
        });
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_menu, fragment);
        fragmentTransaction.commit();
    }
}