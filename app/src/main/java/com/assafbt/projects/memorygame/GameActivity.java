package com.assafbt.projects.memorygame;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class GameActivity extends AppCompatActivity {

    Card card00;
    ImageView im00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        card00 = new Card();
        card00.setImgUp("http://openweathermap.org/img/w/10d.png");

        //System.out.println("###" + card00.getImgUp());

        im00 = (ImageView)findViewById(R.id.img03);

            Picasso.with(getApplicationContext()).load(card00.getImgUp())
                    .placeholder(R.drawable.ic_launcher) // optional
                    .into(im00);




    }
}
