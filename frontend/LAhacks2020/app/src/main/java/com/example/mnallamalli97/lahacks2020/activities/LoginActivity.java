package com.example.mnallamalli97.lahacks2020.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.User;
import com.example.mnallamalli97.lahacks2020.network.GamifyClient;
import com.example.mnallamalli97.lahacks2020.network.RetrofitClientInstance;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button googleLoginButton;
    private int RC_SIGN_IN = 1214;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        googleLoginButton = findViewById(R.id.googleLoginButton);

        //init Google sign in options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //start the sign in flow now
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String name = account.getDisplayName();
            String emailID = account.getEmail();


            sendEmailIDToServer(name, emailID);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void sendEmailIDToServer(String name, String email){

        GamifyClient client = RetrofitClientInstance.getGamifyClient();
        client.login(name, email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //200 means already signed in
                //201 means new user
                //TODO: here is where we decide to take the user to another screen
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



    }

}
