package com.example.hiddencameradetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class CameraIR extends AppCompatActivity {

    CameraPreview mPreview;
    FrameLayout preview;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_main);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        mPreview=new CameraPreview(this);
        preview=(FrameLayout)findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        //dialog box
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(R.string.Important);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.CameraIRMessage)
                .setCancelable(false)
                .setPositiveButton(R.string.OK,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();


    }
}

