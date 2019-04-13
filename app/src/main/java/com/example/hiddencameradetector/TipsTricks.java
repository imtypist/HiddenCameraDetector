package com.example.hiddencameradetector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.example.hiddencameradetector.TipsContent.Bathroom;
import com.example.hiddencameradetector.TipsContent.Bedroom;
import com.example.hiddencameradetector.TipsContent.ChangingRoom;
import com.example.hiddencameradetector.TipsContent.Outside;

public class TipsTricks extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_tricks_main);
        mainGrid = (GridLayout) findViewById(R.id.gridTips);
        openActivities(mainGrid);
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
}