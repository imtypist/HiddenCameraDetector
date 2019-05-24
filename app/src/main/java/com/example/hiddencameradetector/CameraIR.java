package com.example.hiddencameradetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class CameraIR extends AppCompatActivity {

    CameraPreview mPreview;
    FrameLayout preview;
    private InterstitialAd mInterstitialAd;

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

        //admob
        MobileAds.initialize(this, "ca-app-pub-7747740414798372~4877537646");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7747740414798372/7600382834");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

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


    @Override
    public void onBackPressed() {

        mInterstitialAd.show();
        super.onBackPressed();
    }
}

