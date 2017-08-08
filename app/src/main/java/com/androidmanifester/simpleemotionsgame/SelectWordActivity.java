package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import java.util.ArrayList;
import java.util.List;

public class SelectWordActivity extends AppCompatActivity {
    ArrayList<String> words;
    ListView listView;
    List<String> arrayAdapter;
    String SelectedWord;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ToggleButton toggleButton;
    ImageView  imageView;

    int orientatn;
    Integer[] imageId = {
            R.drawable.happy,
            R.drawable.sad,
            R.drawable.good,
            R.drawable.bad,
            R.drawable.weird

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_word);
        sharedPreferences=getSharedPreferences("sfname",MODE_PRIVATE);
        orientatn=1;
        editor=sharedPreferences.edit();
        words=new ArrayList<String>();
        words.add("Happy");
        words.add("Sad");
        words.add("Good");
        words.add("Bad");
        words.add("Weird");
        listView=(ListView) findViewById(R.id.listv);


        CustomList adapter = new
                CustomList(SelectWordActivity.this, words, imageId);

     ///   arrayAdapter=new ArrayAdapter<String>(SelectWordActivity.this,android.R.layout.simple_list_item_1,words);
        //listView.setAdapter(arrayAdapter);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedWord=words.get(position);
                editor.putString("selectedword",SelectedWord).commit();

                new MaterialDialog.Builder(SelectWordActivity.this)
                        .title("Select Orientation")
                        .content("")
                        .positiveText("portrait")

                        .negativeText("Landscape")
                        .positiveColorRes(R.color.material_red_400)
                        .negativeColorRes(R.color.material_red_400)
                        .titleGravity(GravityEnum.CENTER)
                        .titleColorRes(R.color.material_red_400)
                        .contentColorRes(android.R.color.white)
                        .iconRes(R.drawable.cool_blu)
                        .maxIconSize(50)
                      //  .backgroundColorRes(R.color.material_blue_grey_800)
                        .dividerColorRes(R.color.accent)
                       // .btnSelector(R.drawable.md_btn_selector_custom, DialogAction.POSITIVE)
                        .positiveColor(Color.WHITE)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                orientatn=1;
                                startActivity(new Intent(SelectWordActivity.this,MainActivity.class).putExtra("ori",orientatn));

                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                orientatn=0;
                                startActivity(new Intent(SelectWordActivity.this,MainActivity.class).putExtra("ori",orientatn));

                            }
                        })
                        .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                        .theme(Theme.DARK)
                        .show();





                /*final Dialog dialog = new Dialog(SelectWordActivity.this);
                dialog.setContentView(R.layout.dia);
                toggleButton= (ToggleButton) dialog.findViewById(R.id.toggleButton2);
                imageView= (ImageView) dialog.findViewById(R.id.imagev);
                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int orientation =getResources().getConfiguration().orientation;
                        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                            orientatn=1;
                        } else {
                            orientatn=0;
                        }
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        startActivity(new Intent(SelectWordActivity.this,MainActivity.class).putExtra("ori",orientatn));
                    }
                });
                dialog.show();*/

            }
        });


    }
}
