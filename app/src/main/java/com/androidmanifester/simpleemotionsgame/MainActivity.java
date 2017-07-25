package com.androidmanifester.simpleemotionsgame;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> Happy;
    ArrayList<String> Sad;
    ArrayList<String> Good;
    ArrayList<String> Bad;
    ArrayList<String> Weird;
    ArrayList<String> All;
    ArrayList<Integer> BalloonColor;
    ArrayList<TextView> tvs;

    int height,width ;
    TextView tv1,tv2,tv4,tv5,tv6;

    TextView tvscore;
    String SelectedWord;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int score;
    private SoundHelper mSoundHelper;
    Animation animatn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        mSoundHelper = new SoundHelper(this);

        mSoundHelper.prepareMusicPlayer(this);
        mSoundHelper.playMusic();
        animatn= AnimationUtils.loadAnimation(MainActivity.this,R.anim.explode);

        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);
        sharedPreferences=getSharedPreferences("sfname",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        tvscore= (TextView) findViewById(R.id.textView3);
        score=0;
        SelectedWord=sharedPreferences.getString("selectedword","None");

        Happy=new ArrayList<String>();
        Sad=new ArrayList<String>();
        Good=new ArrayList<String>();
        Bad=new ArrayList<String>();
        Weird=new ArrayList<String>();
        All=new ArrayList<String>();
        BalloonColor=new ArrayList<Integer>();

        tvs=new ArrayList<TextView>();

        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv4);
        tvs.add(tv5);
        tvs.add(tv6);

        //contented, content, cheerful, cheery, merry, joyful
        Happy.add("Contented");
        Happy.add("Cheerful");
        Happy.add("Cheery");
        Happy.add("Merry");
        Happy.add("Joyful");
        //	unhappy, sorrowful, dejected, regretful, depressed, downcast,
        Sad.add("unhappy");
        Sad.add("sorrowful");
        Sad.add("dejected");
        Sad.add("regretful");
        Sad.add("depressed");
        //	fine, of high quality, of a high standard, quality, superior
        Good.add("fine");
        Good.add("of high quality");
        Good.add("of a high standard");
        Good.add("quality");
        Good.add("superior");
        //	substandard, poor, inferior, second-rate, second-class, unsatisfactory, inadequate, unacceptable,
        // not up to scratch, not up to par,
        // deficient, imperfect, defective, faulty, shoddy, amateurish, careless, negligent
        Bad.add("poor");
        Bad.add("imperfect");
        Bad.add("defective");
        Bad.add("faulty");
        Bad.add("substandard");
        //	uncanny, eerie, unnatural, preternatural, supernatural, unearthly,
        // other-worldly, unreal, ghostly, mysterious
        Weird.add("mysterious");
        Weird.add("supernatural");
        Weird.add("unearthly");
        Weird.add("unreal");
        Weird.add("eerie");
        All.add("Contented");
        All.add("Cheerful");
        All.add("Cheery");
        All.add("Merry");
        All.add("Joyful");
        All.add("unhappy");
        All.add("sorrowful");
        All.add("dejected");
        All.add("regretful");
        All.add("depressed");
        All.add("fine");
        All.add("of high quality");
        All.add("of a high standard");
        All.add("quality");
        All.add("superior");
        All.add("poor");
        All.add("imperfect");
        All.add("defective");
        All.add("faulty");
        All.add("substandard");
        All.add("mysterious");
        All.add("supernatural");
        All.add("unearthly");
        All.add("unreal");
        All.add("eerie");

        BalloonColor.add(R.drawable.balloon_blue);
        BalloonColor.add(R.drawable.balloon_green);
        BalloonColor.add(R.drawable.balloon_orange);
        BalloonColor.add(R.drawable.balloon_red);
        BalloonColor.add(R.drawable.balloon_yellow);

        startgame();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 2000);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 5000);
        startgame();

        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 2000);

        final Handler handler4 = new Handler();
        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 5000);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSoundHelper.stopMusic();

    }
    public void startgame() {
        Timer t2 = new Timer();
//Set the schedule function and rate
        t2.scheduleAtFixedRate(new TimerTask() {

           @Override
           public void run() {
//Called each time when 4000 milliseconds (4 second) (the period parameter)
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
//stuff that updates ui
                       Collections.shuffle(tvs, new Random(System.nanoTime()));
                       tvs.get(0).setGravity(Gravity.CENTER);
                       Collections.shuffle(BalloonColor, new Random(System.nanoTime()));
                       tvs.get(0).setBackgroundResource(BalloonColor.get(0));
                       Collections.shuffle(All, new Random(System.nanoTime()));
                       animat(tvs.get(0),All.get(0));
                   }
               });
           }

        },//Set how long before to start calling the TimerTask (in milliseconds)
        0,//Set the amount of time between each execution (in milliseconds)
        4000);
    }
    public void animat(final TextView t, String v){
        final ObjectAnimator mover = ObjectAnimator.ofFloat(t, "translationY",0, -height);
        mover.setDuration(4000);
        mover.clone();
        t.setText(v);
        mover.setRepeatCount(0);
        mover.start();

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buttonText = t.getText().toString();
                boolean state ;
                switch (SelectedWord){
                    case "Happy":
                        state =Happy.contains(buttonText);
                        break;
                    case "Sad":
                        state =Sad.contains(buttonText);
                        break;
                    case "Good":
                        state =Good.contains(buttonText);
                        break;
                    case "Bad":
                        state =Bad.contains(buttonText);
                        break;
                    case "Weird":
                        state =Weird.contains(buttonText);
                        break;
                    default:
                        state=false;
                        break;
                }
                if(state){
                    score=score+10;
                    tvscore.setText(score+"");
                    mSoundHelper.playSound(t,0);
                    t.startAnimation(animatn);
                }else {
                    score=score-10;
                    tvscore.setText(score+"");
                    mSoundHelper.playSound(t,1);
                    t.startAnimation(animatn);
                }
                t.setClickable(false);
               /// Toast.makeText(getApplicationContext(), "is "+state, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
