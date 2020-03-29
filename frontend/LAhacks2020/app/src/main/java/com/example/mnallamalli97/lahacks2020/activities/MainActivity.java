package com.example.mnallamalli97.lahacks2020.activities;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

import com.example.mnallamalli97.lahacks2020.MasterTask;
import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.TasksAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TasksAdapter listAdapter;
    private ArrayList<MasterTask> tasksList = new ArrayList<>();
    private RecyclerView recycler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_challenges:
                    Intent friendsIntent = new Intent(MainActivity.this, ChallengeActivity.class);
                    startActivity(friendsIntent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.leaderboard_button:
                    Intent leaderboardIntent = new Intent(MainActivity.this, LeaderboardActivity.class);
                    startActivity(leaderboardIntent);
                    return true;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recycler = findViewById(R.id.tasksRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        listAdapter = new TasksAdapter(tasksList, this);
        recycler.setAdapter(listAdapter);

        //Load the date from the network or other resources
        //into the array list asynchronously

        //tasksList.add(new MasterTask(masterChallenge, "778899009", "google.com", 30));

        listAdapter.notifyDataSetChanged();


    }

}
