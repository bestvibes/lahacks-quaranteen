package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.TeamDataViewModel;

public class SharePartyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_party);

        // until we can implement this
        findViewById(R.id.shareButton).setVisibility(View.GONE);

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharePartyActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Log.d("ShareParty", "code: " + TeamDataViewModel.getTeamData().getValue().getJoinCode());
        TextView code = findViewById(R.id.uniqueCodeTextView);
        code.setText(TeamDataViewModel.getTeamData().getValue().getJoinCode());

    }

}
