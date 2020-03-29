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

public class JoinPartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_party);

        final EditText code = findViewById(R.id.enterCodeEditText);

        TeamDataViewModel.getTeamData().observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if (team == null) {
                    Toast.makeText(JoinPartyActivity.this, "Team does not exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("JoinTeam", "Got team: " + team.getTeamName());
                Intent intent = new Intent(JoinPartyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamDataViewModel.joinTeam(UserDataViewModel.getUpdatedUserLiveData().getValue().getUser_id(), code.getText().toString());
            }
        });
    }

}
