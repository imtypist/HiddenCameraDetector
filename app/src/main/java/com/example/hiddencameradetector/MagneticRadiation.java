package com.example.hiddencameradetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MagneticRadiation extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView Mtext,magnetDetecTextView;
    int magnitude = 0,x,y,z;
    int max = 200;
    ProgressBar progressBar;
    MediaPlayer beep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiation_main);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
        Mtext = findViewById(R.id.m_textView);
        magnetDetecTextView = findViewById(R.id.magnet_detect_textView);
        progressBar = findViewById(R.id.magnetic_progressbar);
        beep = MediaPlayer.create(MagneticRadiation.this, R.raw.beep);

        progressBar.setMax(max);

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
    public void onSensorChanged(SensorEvent event) {
        x = (int) event.values[0];
        y = (int) event.values[1];
        z = (int )event.values[2];

        magnitude = (int) Math.sqrt(x*x+y*y+z*z);
        max = (magnitude>max)? magnitude : max;
        progressBar.setMax(max);
        progressBar.setProgress(magnitude);
        if(magnitude>=90 && magnitude <=120) {
            magnetDetecTextView.setText("CAMERA DETECTED!");
            beep.start();
        }
        else if(magnitude>120) {
            magnetDetecTextView.setText("POTENTIAL CAMERA / SMALL SPEAKER DETECTED");
            beep.start();
        }
        else magnetDetecTextView.setText("");
        Mtext.setText(String.valueOf(magnitude)+"µT");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
