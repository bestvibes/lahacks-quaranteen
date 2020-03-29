package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.Team;
import com.example.mnallamalli97.lahacks2020.TeamDataViewModel;
import com.example.mnallamalli97.lahacks2020.UserDataViewModel;

public class CreatePartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_party);

        final EditText name = findViewById(R.id.namePartyEditText);

        TeamDataViewModel.getTeamData().observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if (team == null) {
                    Toast.makeText(CreatePartyActivity.this, "Team does not exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("CreateTeam", "Got team: " + team.getTeamName());
                Intent intent = new Intent(CreatePartyActivity.this, SharePartyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamDataViewModel.createTeam(name.getText().toString(), UserDataViewModel.getUpdatedUserLiveData().getValue().getUser_id());
            }
        });
    }
}
