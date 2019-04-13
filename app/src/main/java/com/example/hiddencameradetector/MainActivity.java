package com.example.hiddencameradetector;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.grid);

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
