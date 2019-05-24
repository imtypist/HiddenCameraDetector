package com.example.hiddencameradetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.anastr.speedviewlib.AwesomeSpeedometer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MagneticRadiation extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView Mtext,magnetDetecTextView;
    int magnitude = 0,x,y,z;
    int max = 0;
    MediaPlayer beep;
    AwesomeSpeedometer awesomeSpeedometer;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiation_main);

        //admob
        MobileAds.initialize(this, "ca-app-pub-7747740414798372~4877537646");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7747740414798372/7600382834");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(R.string.Important);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.MagneticRadiationMessage)
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
    protected void onResume() {
        super.onResume();
        awesomeSpeedometer= (AwesomeSpeedometer) findViewById(R.id.awesomeSpeedometer);
        awesomeSpeedometer.speedTo(max);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Mtext = findViewById(R.id.m_textView);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        magnetDetecTextView = findViewById(R.id.magnet_detect_textView);
        beep = MediaPlayer.create(MagneticRadiation.this, R.raw.beep);

        awesomeSpeedometer.setUnit("");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = (int) event.values[0];
        y = (int) event.values[1];
        z = (int )event.values[2];

        magnitude = (int) Math.sqrt(x*x+y*y+z*z);
        awesomeSpeedometer.speedTo(magnitude/10);
        if(magnitude>=90 && magnitude <=120) {
            magnetDetecTextView.setText("CAMERA DETECTED!");
            beep.start();
        }
        else if(magnitude>120) {
            magnetDetecTextView.setText("POTENTIAL CAMERA / SMALL SPEAKER DETECTED");
            beep.start();
        }
        else magnetDetecTextView.setText("");
        Mtext.setText(String.valueOf(magnitude)+"µF");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        beep.stop();
        super.onBackPressed();
    }
}
