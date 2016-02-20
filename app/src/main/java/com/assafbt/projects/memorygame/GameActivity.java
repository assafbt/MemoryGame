package com.assafbt.projects.memorygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;



public class GameActivity extends AppCompatActivity {

    int itemPerRow = 4;
   // MainActivity ma = new MainActivity();
    Card card[] = new Card[8];

    ImageView   im00,im01,im02,im03,
                im10,im11,im12,im13,
                im20, im21, im22, im23,
                im30, im31, im32, im33;
    //ImageView im0[], im1[], im2[], im3[]; //X16
    TextView correctTime;
    Button rstBtn;


    boolean isRunning = false;
    private static Context mContext;

    private long startTime = 0L;
    private long bestTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs=0;
    int milliseconds=0;
    DAL dalObj = new DAL(this);
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        prefs = getSharedPreferences("shPref", Context.MODE_PRIVATE);
        editor = prefs.edit();

        // init the face down card
       // String[] tmp = ma.getPicUri();
        for(int i =0; i<8;i++) {
            card[i] = new Card();
            //card[i].setImgDown("R.drawable.a11d");
            Log.e("## ImgDown ##" + i, card[i].getImgUp());
           // array[i] = ;
     //       System.out.println("### prefs.getString "+i +"   "+prefs.getString("pic_" + i, "nothing"));
            card[i].setImgUp(prefs.getString("pic_" + i, null));

        }

        correctTime = (TextView) findViewById(R.id.scoreView);
        start();




        /*
        //here we should choose 8 pictures from the gallery and then enter their addresses into pisSet
        for(int i =0; i<8;i++) {
            card[i].setImgUp("http://openweathermap.org/img/w/10d.png");
        }*/

        //X16
        //1st row
        im00 = (ImageView)findViewById(R.id.img00);
        im01 = (ImageView)findViewById(R.id.img01);
        im02 = (ImageView)findViewById(R.id.img02);
        im03 = (ImageView)findViewById(R.id.img03);

        //2nd row
        im10 = (ImageView)findViewById(R.id.img10);
        im11 = (ImageView)findViewById(R.id.img11);
        im12 = (ImageView)findViewById(R.id.img12);
        im13 = (ImageView)findViewById(R.id.img13);

        //3rd row
        im20 = (ImageView)findViewById(R.id.img20);
        im21 = (ImageView)findViewById(R.id.img21);
        im22 = (ImageView)findViewById(R.id.img22);
        im23 = (ImageView)findViewById(R.id.img23);

        //4th row
        im30 = (ImageView)findViewById(R.id.img30);
        im31 = (ImageView)findViewById(R.id.img31);
        im32 = (ImageView)findViewById(R.id.img32);
        im33 = (ImageView)findViewById(R.id.img33);



        allFaceDown();


        //problem with the for
        for (int clickCount=1; clickCount<3;clickCount++ ){
            Log.e("clickCount", "FOR, START " +clickCount+"");
            if (clickCount==2){
                Log.e("clickCount", "FOR, IF= TRUE "+clickCount+"");
                allFaceDown();
            }
            else {
                Log.e("clickCount", "FOR, IF= FALSE "+clickCount+"");
                clickCount = showPictureOnClick(clickCount);
            }
            Log.e("clickCount", "FOR, END " +clickCount+"");
        }

        rstBtn = (Button)findViewById(R.id.restartBTN);
        rstBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                restart();
                //v.invalidate();
                //showPictureOnClick(card, im02);

            }
        });



    }//onCreate

    private int showPictureOnClick(int clickCount){
        if (clickCount<2) {
            //1st row
            im00.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im00);


                }
            });
            im01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im01);

                }
            });
            im02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im02);

                }
            });
            im03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im03);

                }
            });

            //2nd row
            im10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im10);

                }
            });
            im11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im11);

                }
            });
            im12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im12);

                }
            });
            im13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im13);

                }
            });

            //3rd row
            im20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im20);

                }
            });
            im21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im21);

                }
            });
            im22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im22);

                }
            });
            im23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im23);

                }
            });

            //4th row
            im30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im30);

                }
            });
            im31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im31);

                }
            });
            im32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im32);

                }
            });
            im33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPicture(card, im33);

                }
            });

        }//if
        return clickCount;
    }//showPictureOnClick

    private void showPicture(Card[] picStock, ImageView iv){
        int i = 2;



        Picasso.with(getApplicationContext()).load(picStock[i].getImgUp())
                .placeholder(R.drawable.ic_launcher) // optional
                .into(iv);
        Log.e("## showPicture ##", "ddd");

    }//showPicture

    private void allFaceDown(){

        //1st row
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im00);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im01);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im02);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im03);

        //2nd row
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im10);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im11);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im12);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im13);

        //3rd row
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im20);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im21);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im22);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im23);

        //4th row
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im30);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im31);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im32);

        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im33);

        Log.e("## Face Down ##", " ALL ");

        /*
        for (i=0;i<4;i++) {
            Picasso.with(getApplicationContext()).load(picStock[i].getImgDown())
                    .placeholder(R.drawable.ic_launcher) // optional
                    .into(iv0[i]);


            Picasso.with(getApplicationContext()).load(picStock[i].getImgDown())
                    .placeholder(R.drawable.ic_launcher) // optional
                    .into(iv1[i]);


            Picasso.with(getApplicationContext()).load(picStock[i].getImgDown())
                    .placeholder(R.drawable.ic_launcher) // optional
                    .into(iv2[i]);



            Picasso.with(getApplicationContext()).load(picStock[i].getImgDown())
                    .placeholder(R.drawable.ic_launcher) // optional
                    .into(iv3[i]);
        }
*/
    }//allFaceDown

    private void restart(){
        stop(this.editor);
        allFaceDown();
        start();

    }

    private void start(){
        if (!isRunning) {
            Log.e("Context", "let the game begin");
            isRunning = true;
        //    view.invalidate();
            startTime = SystemClock.uptimeMillis();
            customHandler.postDelayed(updateTimerThread, 0);
        //    ((GameView) view).resetCounter();

        }
        else {
            //do nothing;
        }


    }//start

    // stop the clock
    public void stop(SharedPreferences.Editor editor){
        customHandler.removeCallbacks(updateTimerThread);
        if (isRunning) {//running
            isRunning = false;
/*
            bestTime = dalObj.getRecord(lvl_cmpx);
            int bestTimeValue = (int)updatedTime;
            if ((bestTime == 0) || (bestTime > bestTimeValue)) { //check the best
                dalObj.updateRecord(lvl_cmpx, bestTimeValue);

                Toast.makeText(getApplicationContext(), "new record !", Toast.LENGTH_SHORT).show();
                bestResultShowTime.setText(dalObj.convertToTimeStringFormat(bestTimeValue));

            }*/
        }
    }//stop

    // time running
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedTime / 1000);
            secs = secs % 60;
            milliseconds = (int) (updatedTime % 1000);
            correctTime.setText(String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);

        }//run

    };//updateTimerThread



}//GameActivity
