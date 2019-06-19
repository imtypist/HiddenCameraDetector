package com.faisal.hiddencameradetector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import com.faisal.hiddencameradetector.TipsContent.Bathroom;
import com.faisal.hiddencameradetector.TipsContent.Bedroom;
import com.faisal.hiddencameradetector.TipsContent.ChangingRoom;
import com.faisal.hiddencameradetector.TipsContent.Outside;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class TipsTricks extends AppCompatActivity {

    GridLayout mainGrid;
    private InterstitialAd mInterstitialAd;

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_tricks_main);
        mainGrid = (GridLayout) findViewById(R.id.gridTips);
        openActivities(mainGrid);

        MobileAds.initialize(this, "ca-app-pub-7747740414798372~6605206806");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7747740414798372/5713908919");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //admob
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



    }

    private void openActivities(GridLayout mainGrid) {

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int final1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (final1 == 0) {
                        Intent intent = new Intent(TipsTricks.this, Bathroom.class);
                        startActivity(intent);
                    } else if (final1 == 1) {
                        Intent intent = new Intent(TipsTricks.this, ChangingRoom.class);
                        startActivity(intent);
                    } else if (final1 == 2) {
                        Intent intent = new Intent(TipsTricks.this, Bedroom.class);
                        startActivity(intent);
                    } else if (final1 == 3) {
                        Intent intent = new Intent(TipsTricks.this, Outside.class);
                        startActivity(intent);
                    }
                }
            });
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