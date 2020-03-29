package com.example.mnallamalli97.lahacks2020.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.example.mnallamalli97.lahacks2020.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChallengeActivity extends AppCompatActivity {

    private Button cameraButton;
    Uri imageUri;
    String mCameraFileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_screen);
        cameraButton = findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open the camera and take a picture
                cameraIntent();
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
