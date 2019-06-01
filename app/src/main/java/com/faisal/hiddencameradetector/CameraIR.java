package com.faisal.hiddencameradetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class CameraIR extends AppCompatActivity {

    CameraPreview mPreview;
    FrameLayout preview;
    private InterstitialAd mInterstitialAd;
    public static final String PREFS_NAME = "MyPrefsFile2";
    public CheckBox dontShowAgain;
    private AdView mAdView;

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
        MobileAds.initialize(this, "ca-app-pub-7747740414798372~6605206806");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7747740414798372/5713908919");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        //admob
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //dialog box
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater adbInflater = LayoutInflater.from(this);
        View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");
        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
        alertDialogBuilder.setView(eulaLayout);
        // set title
        alertDialogBuilder.setTitle(R.string.Important);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.CameraIRMessage)
                .setCancelable(false)
                .setPositiveButton(R.string.OK,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String checkBoxResult = "NOT checked";

                        if (dontShowAgain.isChecked()) {
                            checkBoxResult = "checked";
                        }

                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();

                        editor.putString("skipMessage", checkBoxResult);
                        editor.apply();
                        editor.commit();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        if (!skipMessage.equals("checked")) {
            alertDialog.show();
        }

    }


    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        super.onBackPressed();

    }
}

