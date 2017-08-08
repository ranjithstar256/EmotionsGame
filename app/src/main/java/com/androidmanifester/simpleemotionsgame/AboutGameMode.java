package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutGameMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_game_mode);
    }

    public void gotogame(View view) {
        startActivity(new Intent(AboutGameMode.this, SelectWordActivity.class));
    }
}
