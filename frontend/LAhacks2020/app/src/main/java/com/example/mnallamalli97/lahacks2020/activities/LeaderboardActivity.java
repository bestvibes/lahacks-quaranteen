package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mnallamalli97.lahacks2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class LeaderboardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_challenges:
                    Intent friendsIntent = new Intent(LeaderboardActivity.this, TaskActivity.class);
                    startActivity(friendsIntent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(LeaderboardActivity.this, MainActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.leaderboard_button:
                    Intent leaderboardIntent = new Intent(LeaderboardActivity.this, LeaderboardActivity.class);
                    startActivity(leaderboardIntent);
                    return true;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(LeaderboardActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.leaderboard_button).setChecked(true);

    }
}
