package com.assafbt.projects.memorygame;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GameActivity extends AppCompatActivity {

    Card card[] = new Card[8];
    ImageView im00, im01; //X16


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        //here we should choose 8 pictures from the gallery and then enter their addresses into pisSet
        for(int i =0; i<8;i++)
            card[i].setImgUp("http://openweathermap.org/img/w/10d.png");

        //X16
        im00 = (ImageView)findViewById(R.id.img00);
        setPicture(card, im00);






    }


    private void setPicture(Card[] picStock, ImageView iv){
        int i = 0;
        Picasso.with(getApplicationContext()).load(picStock[i].getImgUp())
                .placeholder(R.drawable.ic_launcher) // optional
                .into(iv);

    }
}
