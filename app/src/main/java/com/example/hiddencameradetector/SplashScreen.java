package com.example.hiddencameradetector;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        if (checkPermissions().size() == 0) {
            delayEnter();
        } else {
            requestPermissions();
        }
    }

    private void delayEnter() {
        new CountDownTimer(1200, 1200) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }.start();
    }

    private List<String> checkPermissions() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA);
        }
        return permissions;
    }

    private void requestPermissions() {
        List<String> permissions = checkPermissions();
        if (permissions.size() > 0) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        for (int i = 0; i < permissions.length; i++) {
//            String permission = permissions[i];
//            if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                FileLogManager.init();
//                break;
//            }
//        }
        delayEnter();
    }
}