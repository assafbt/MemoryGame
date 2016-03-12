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

import java.util.Random;


public class GameActivity extends AppCompatActivity {

    boolean openCard = false;
    String last;
    int lastInt;
    int pairsToGo = 8;
    int itemPerRow = 4;
    int countFaceUp;
    Card card[] = new Card[16];
    String imagUri[][]= new String[8][2];

    ImageView   im00,im01,im02,im03,
                im10,im11,im12,im13,
                im20, im21, im22, im23,
                im30, im31, im32, im33;

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
    int mins=0;
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
        for(int i =0; i<8;i++) {
            imagUri[i][0] = new String();
            imagUri[i][0]=prefs.getString("pic_" + i, null);
            imagUri[i][1]="0";

        }

        correctTime = (TextView) findViewById(R.id.scoreView);
        start();

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


        newOrder();
        allFaceDown();

        showPictureOnClick();

        rstBtn = (Button)findViewById(R.id.restartBTN);
        rstBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i=0;i<16;i++){
                    card[i].setFaceUp(false);
                }

                restart();
            }
        });



    }//onCreate




    private void showPictureOnClick(){

            //1st row
            im00.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(0, im00);
                    }
                }
            });
            im01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(1, im01);
                    }

                }
            });
            im02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(2, im02);
                    }
                }
            });
            im03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(3, im03);
                    }
                }
            });

            //2nd row
            im10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(4, im10);
                    }
                }
            });
            im11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(5, im11);
                    }
                }
            });
            im12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(6, im12);
                    }
                }
            });
            im13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(7, im13);
                    }
                }
            });

            //3rd row
            im20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(8, im20);
                    }
                }
            });
            im21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(9, im21);
                    }
                }
            });
            im22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(10, im22);
                    }
                }
            });
            im23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(11, im23);
                    }
                }
            });

            //4th row
            im30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(12, im30);
                    }
                }
            });
            im31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(13, im31);
                    }
                }
            });
            im32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(14, im32);
                    }
                }
            });
            im33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countFaceUp<2) {
                        countFaceUp++;
                        showPicture(15, im33);
                    }
                }
            });


    }//showPictureOnClick

    private void showPicture(int i, ImageView iv){

        Picasso.with(getApplicationContext()).load(card[i].getImgUp())
                .placeholder(R.drawable.ic_launcher) // optional
                .into(iv);

            if ((!openCard)) {
                last = card[i].getImgUp();
                lastInt = i;
                openCard = true;
            } else {
                openCard = false;
                if (card[i].getImgUp().equals(last)) {
                    pairsToGo--;
                    countFaceUp=0;
                    card[i].setFaceUp(true);
                    card[lastInt].setFaceUp(true);
                    if (pairsToGo == 0) {
                        stop(this.editor);

                        // dalObj.updateRecordShort(updatedTime);
                        Log.e("updateRecordShort", dalObj.getFirst() + "");
                        dalObj.updateRecord(updatedTime);
                        Log.e("updateRecord", dalObj.getFirst() + "");


                    }

                } else {

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            //countFaceUp=0;
                            allFaceDown();
                        }
                    };

                    Handler h = new Handler();
                    h.postDelayed(r, 1000); // <-- the "1000" is the delay time in miliseconds
                }

            }
    }//showPicture

    private void newOrder(){
        System.out.println("### enter new order");
        Random r = new Random();
        int num;
        for(int i =0; i<16;i++)
            while(true) {
                System.out.println("###  new orders i "+i);

                num = r.nextInt(8);

                if(imagUri[num][1].equals("0") || imagUri[num][1].equals("1")) {
                    imagUri[num][1]=strPlusOne(imagUri[num][1]);
                    card[i] = new Card();
                    card[i].setImgUp(imagUri[num][0]);

                    break;
                }
            }
       // allFaceDown();
    }//newOrder

    private void allFaceDown(){
        countFaceUp=0;
        //1st row
        if(!card[0].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im00);
        if(!card[1].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im01);
        if(!card[2].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im02);
        if(!card[3].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im03);

        //2nd row
        if(!card[4].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im10);
        if(!card[5].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im11);
        if(!card[6].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im12);
        if(!card[7].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im13);

        //3rd row
        if(!card[8].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im20);
        if(!card[9].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im21);
        if(!card[10].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im22);
        if(!card[11].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im23);

        //4th row
        if(!card[12].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im30);
        if(!card[13].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im31);
        if(!card[14].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im32);
        if(!card[15].getFaceUp())
        Picasso.with(getApplicationContext()).load(R.drawable.images)
                .placeholder(R.drawable.ic_launcher) // optional
                .into(im33);

        Log.i("## Face Down ##", " ALL ");

    }//allFaceDown

    private void restart(){
        stop(this.editor);
        allFaceDown();
        start();

    }

    private void start(){
        if (!isRunning) {
            Log.i("Context", "let the game begin");
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
        }
    }//stop

    // time running
    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedTime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedTime % 1000);
            correctTime.setText(" " + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));

            customHandler.postDelayed(this, 0);

        }//run

    };//updateTimerThread

    private String strPlusOne(String str){
        int num = Integer.parseInt(str);
        num ++;
        return num+"";
    }

}//GameActivity
