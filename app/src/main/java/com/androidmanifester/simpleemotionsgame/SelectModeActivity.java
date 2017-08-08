package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class SelectModeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


    }

    public void gotogame(View view) {
        float t = ratingBar.getRating();
        editor.putFloat("skilevel", t);
        startActivity(new Intent(SelectModeActivity.this, AboutGameMode.class));
    }

    public void gotolearn(View view) {
        Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
    }
}
