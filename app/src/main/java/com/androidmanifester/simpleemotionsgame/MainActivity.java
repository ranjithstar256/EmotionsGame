package com.androidmanifester.simpleemotionsgame;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
    TextView tv1,tv2,tv4,tv5,tv6,tv13,tv8,tv9,tv10,tv11,tv12;

    TextView tvscore;
    String SelectedWord;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int score;
    private SoundHelper mSoundHelper;
    Animation animatn;
    private Toast toast;
    private long lastBackPressTime = 0;
    ToggleButton toggleButton;
    ImageView  imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        if(((getIntent().getIntExtra("ori",1))==1)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

      //  setRequestedOrientation(Integer.parseInt(getIntent().getStringExtra("ori")));


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
        tv8 = (TextView)findViewById(R.id.textView8);
        tv9 = (TextView)findViewById(R.id.textView9);
        tv10 = (TextView)findViewById(R.id.textView10);
        tv11 = (TextView)findViewById(R.id.textView11);
        tv12 = (TextView)findViewById(R.id.textView12);
        tv13 = (TextView)findViewById(R.id.textView13);
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
        tvs.add(tv6);
        tvs.add(tv8);
        tvs.add(tv9);
        tvs.add(tv10);
        tvs.add(tv11);
        tvs.add(tv12);
        tvs.add(tv13);

        //contented, content, cheerful, cheery, merry, joyful
        Happy.add("Contented");
        Happy.add("Cheerful");
        Happy.add("Cheery");
        Happy.add("Merry");
        Happy.add("Joyful");
        //	unhappy, sorrowful, dejected, regretful, depressed, downcast,
        Sad.add("unhappy");
        Sad.add("Sorrowful");
        Sad.add("Dejected");
        Sad.add("Regretful");
        Sad.add("Depressed");
        //	fine, of high quality, of a high standard, quality, superior
        Good.add("Fine");
        Good.add("Of high quality");
        Good.add("Of a high standard");
        Good.add("Quality");
        Good.add("Superior");
        //	substandard, poor, inferior, second-rate, second-class, unsatisfactory, inadequate, unacceptable,
        // not up to scratch, not up to par,
        // deficient, imperfect, defective, faulty, shoddy, amateurish, careless, negligent
        Bad.add("Poor");
        Bad.add("Imperfect");
        Bad.add("Defective");
        Bad.add("Faulty");
        Bad.add("Substandard");
        //	uncanny, eerie, unnatural, preternatural, supernatural, unearthly,
        // other-worldly, unreal, ghostly, mysterious
        Weird.add("Mysterious");
        Weird.add("Supernatural");
        Weird.add("Unearthly");
        Weird.add("Unreal");
        Weird.add("Eerie");
        All.add("Contented");
        All.add("Cheerful");
        All.add("Cheery");
        All.add("Merry");
        All.add("Joyful");
        All.add("Unhappy");
        All.add("Sorrowful");
        All.add("Dejected");
        All.add("Regretful");
        All.add("Depressed");
        All.add("Fine");
        All.add("Of high quality");
        All.add("Of a high standard");
        All.add("Quality");
        All.add("Superior");
        All.add("Poor");
        All.add("Imperfect");
        All.add("Defective");
        All.add("Faulty");
        All.add("Substandard");
        All.add("Mysterious");
        All.add("Supernatural");
        All.add("Unearthly");
        All.add("Unreal");
        All.add("Eerie");

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
        }, 1500);

        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 2800);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startgame();
            }
        }, 3450);
        //startgame();
    }

    @Override
    public void onBackPressed() {
         super.onBackPressed();
          mSoundHelper.stopMusic();
      ///  finish();
       // System.exit(0);


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

        mover.setDuration(5000);
        //mover.clone();

        //mover.setRepeatCount(0);
if(mover.isRunning()){

}else {
    t.setText(v);
   // t.setWidth(100);
    mover.start();
}
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
                    //mover.cancel();
                    mover.end();
                    //t.startAnimation(animatn);
                }else {
                    score=score-10;
                    tvscore.setText(score+"");
                    mSoundHelper.playSound(t,1);
//                    mover.cancel();
                    mover.end();

                    //t.startAnimation(animatn);
                }
                t.setClickable(false);
               /// Toast.makeText(getApplicationContext(), "is "+state, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
