package com.example.mnallamalli97.lahacks2020.activities;

import android.os.Bundle;
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

        TextView code = findViewById(R.id.uniqueCodeTextView);
        code.setText(TeamDataViewModel.getTeamData().getValue().getJoinCode());

        // until we can implement this
        findViewById(R.id.shareButton).setVisibility(View.GONE);
    }

}
