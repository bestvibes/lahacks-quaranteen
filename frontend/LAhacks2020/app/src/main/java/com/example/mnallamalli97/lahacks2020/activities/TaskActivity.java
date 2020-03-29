package com.example.mnallamalli97.lahacks2020.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.mnallamalli97.lahacks2020.R;
import com.example.mnallamalli97.lahacks2020.TaskInstance;
import com.example.mnallamalli97.lahacks2020.TaskViewModel;
import com.example.mnallamalli97.lahacks2020.UserDataViewModel;
import com.example.mnallamalli97.lahacks2020.Verification;
import com.example.mnallamalli97.lahacks2020.VerificationViewModel;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskActivity extends AppCompatActivity {

    private Button cameraButton;
    private TextView textView2;
    Uri imageUri;
    String mCameraFileName;
    TaskInstance task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_screen);

        task = TaskViewModel.getUserTasks().getValue().get(getIntent().getIntExtra("task", 0));
        TextView header = findViewById(R.id.taskNameTextview);
        header.setText(task.getMasterTask().getName());
        textView2 = findViewById(R.id.textView2);
        cameraButton = findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open the camera and take a picture
                cameraIntent();
            }
        });

        VerificationViewModel.getVerification().observe(this, new Observer<Verification>() {
            @Override
            public void onChanged(Verification verification) {
                if (verification == null){
                    Toast.makeText(TaskActivity.this, "Verification successful!", Toast.LENGTH_SHORT).show();
                    textView2.setText("Completed");
                    return;
                }
                Log.d("Verification", "verified " + verification.isVerified());
            }
        });
    }

    private void cameraIntent() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("-mm-ss");

        String newPicFile = df.format(date) + ".jpg";
        String outPath = "/sdcard/" + newPicFile;
        File outFile = new File(outPath);

        mCameraFileName = outFile.toString();
        Uri outuri = Uri.fromFile(outFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                if (data != null) {
                    imageUri = data.getData();
                }
                if (imageUri == null && mCameraFileName != null) {
                    imageUri = Uri.fromFile(new File(mCameraFileName));
                }
                //File file = new File(mCameraFileName);
                File file = new File(imageUri.getPath());

                if (UserDataViewModel.getUpdatedUserLiveData().getValue() != null){
                    int user_id = UserDataViewModel.getUpdatedUserLiveData().getValue().getUser_id();
                    String verificationURL = task.getMasterTask().getVerificationMLModelURL();
                    if (verificationURL.equals("/verify/apple")){
                        VerificationViewModel.verifyApple(user_id, file);
                    }
                else if (verificationURL.equals("/verify/video")){
                        VerificationViewModel.verifyVideo(user_id, file);
                    }
                    else if (verificationURL.equals("/verify/book")){
                        VerificationViewModel.verifyBook(user_id, file);
                    }
                    else if (verificationURL.equals("/verify/sunrise")){
                        VerificationViewModel.verifySunrise(user_id, file);
                    }
                }
                else{
                    Log.e("VerificationError", "unable to get userId");
                }

                if (!file.exists()) {
                    file.mkdir();
                }

            }
        }
    }


//    private void openCameraIntent() {
//        Intent pictureIntent = new Intent(
//                MediaStore.ACTION_IMAGE_CAPTURE
//        );
//        if(pictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(pictureIntent,
//                    REQUEST_CAPTURE_IMAGE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CAPTURE_IMAGE &&
//                resultCode == RESULT_OK) {
//            if (data != null && data.getExtras() != null) {
//                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
//
//            }
//        }
//    }
}
