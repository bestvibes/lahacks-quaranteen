package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mnallamalli97.lahacks2020.R;

public class CreateJoinPartyActivity extends AppCompatActivity {

    private Button createTeamButton;
    private Button joinTeamButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_join_party);

        createTeamButton = findViewById(R.id.createTeamButton);
        joinTeamButton = findViewById(R.id.joinTeamButton);

        joinTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateJoinPartyActivity.this, JoinPartyActivity.class);
                startActivity(intent);
            }
        });

        createTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateJoinPartyActivity.this, CreatePartyActivity.class);
                startActivity(intent);
            }
        });
    }
}

