package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mnallamalli97.lahacks2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChallengeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_challenges:
                    Intent friendsIntent = new Intent(ChallengeActivity.this, ChallengeActivity.class);
                    startActivity(friendsIntent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent dashboardIntent = new Intent(ChallengeActivity.this, MainActivity.class);
                    startActivity(dashboardIntent);
                    return true;
                case R.id.leaderboard_button:
                    Intent leaderboardIntent = new Intent(ChallengeActivity.this, LeaderboardActivity.class);
                    startActivity(leaderboardIntent);
                    return true;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(ChallengeActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenges_screen);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.navigation_challenges).setChecked(true);

        TextView t1 = findViewById(R.id.textView3);
        TextView t2 = findViewById(R.id.textView5);
        TextView t3 = findViewById(R.id.textView6);
        t1.setText("Iron Chef");
        t2.setText("Quarantine and Chill");
        t3.setText("Quaranthings To Do");
    }
}
