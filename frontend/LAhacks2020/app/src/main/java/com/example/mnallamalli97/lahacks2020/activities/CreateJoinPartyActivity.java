package com.example.mnallamalli97.lahacks2020.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.Team;
import com.example.mnallamalli97.lahacks2020.TeamDataViewModel;
import com.example.mnallamalli97.lahacks2020.UserDataViewModel;

public class CreateJoinPartyActivity extends AppCompatActivity {

    private Button createTeamButton;
    private Button joinTeamButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_join_party);

        createTeamButton = findViewById(R.id.createTeamButton);
        joinTeamButton = findViewById(R.id.joinTeamButton);

        TeamDataViewModel.getTeamData().observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if (team == null) {
                    Toast.makeText(CreateJoinPartyActivity.this, "Team does not exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("CreateJoinTeam", "Got team: " + team.getTeamName());
                Intent intent = new Intent(CreateJoinPartyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        joinTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinTeam();
            }
        });

        createTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTeam();
            }
        });
    }

    private void joinTeam() {
        final EditText editText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Join a Team")
                .setMessage("Enter a team join code:")
                .setView(editText)
                .setPositiveButton("Join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TeamDataViewModel.joinTeam(UserDataViewModel.getUpdatedUserLiveData().getValue().getUser_id(), editText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    private void createTeam() {
        final EditText editText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Create a Team")
                .setMessage("Enter a team name:")
                .setView(editText)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TeamDataViewModel.createTeam(editText.getText().toString(), UserDataViewModel.getUpdatedUserLiveData().getValue().getUser_id());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}
