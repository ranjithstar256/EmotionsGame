package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectWordActivity extends AppCompatActivity {
    ArrayList<String> words;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String SelectedWord;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_word);
        sharedPreferences=getSharedPreferences("sfname",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        words=new ArrayList<String>();
        words.add("Happy");
        words.add("Sad");
        words.add("Good");
        words.add("Bad");
        words.add("Weird");
        listView=(ListView) findViewById(R.id.listv);
        arrayAdapter=new ArrayAdapter<String>(SelectWordActivity.this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedWord=words.get(position);
                editor.putString("selectedword",SelectedWord).commit();
                startActivity(new Intent(SelectWordActivity.this,MainActivity.class));
            }
        });


    }
}
