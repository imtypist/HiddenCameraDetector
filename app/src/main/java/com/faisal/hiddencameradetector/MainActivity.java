package com.faisal.hiddencameradetector;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;
    //admob
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,"ca-app-pub-7747740414798372~6770941929");
        //admob
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mainGrid = (GridLayout) findViewById(R.id.grid);

        openActivities(mainGrid);

        TextView textView1 = (TextView) findViewById(R.id.app_name1);
        TextView textView2 = (TextView) findViewById(R.id.app_name2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/afternoon.ttf");
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);

    }

    private void openActivities(GridLayout mainGrid) {

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int final1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (final1 == 0) {
                        Intent intent = new Intent(MainActivity.this, CameraIR.class);
                        startActivity(intent);
                    } else if (final1 == 1) {
                        Intent intent = new Intent(MainActivity.this, MagneticRadiation.class);
                        startActivity(intent);
                    } else if (final1 == 2) {
                        Intent intent = new Intent(MainActivity.this, TipsTricks.class);
                        startActivity(intent);
                    } else if (final1 == 3) {
                        Intent intent = new Intent(MainActivity.this, Instructions.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
