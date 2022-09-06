package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.main.Data;
import com.example.myapplication.main.MyIntent;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.instance();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onResume() {
        super.onResume();
        requestPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void requestPermission() {
        if (!Environment.isExternalStorageManager()) {
            Snackbar.make(findViewById(android.R.id.content), "Permission needed!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Settings", v -> {
                        Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                        startActivity(intent);
                    }).show();
        }
    }

    public void loginClick(View v) {
        MyIntent.intent(this, LoginActivity.class);
    }

    public void registerClick(View v) {
        MyIntent.intent(this, RegisterActivity.class);
    }
}