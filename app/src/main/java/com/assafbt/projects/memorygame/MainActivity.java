/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends Activity {
    Button start, select, reset1,reset2;
    private static int RESULT_LOAD_IMG = 8;
    String imgDecodableString;
    int step = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    DAL dalObj = new DAL(this);
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("shPref", Context.MODE_PRIVATE);
        editor = prefs.edit();


        TextView best1v = (TextView) findViewById(R.id.best1View);
        TextView best2v = (TextView) findViewById(R.id.best2View);
        TextView best3v = (TextView) findViewById(R.id.best3View);


        if (dalObj.isBDEmpty()){
            dalObj.initRecords();
            Log.e("was","inside if");
            Log.e("dalObj.getFirst1", dalObj.getFirst()+"");
            Log.e("dalObj.getSecond1", dalObj.getSecond()+"");
            Log.e("dalObj.getThird1", dalObj.getThird()+"");
        }

        Log.e("was","outside if");
        Log.e("dalObj.getFirst2", dalObj.getFirst()+"");
        Log.e("dalObj.getSecond2", dalObj.getSecond() + "");
        Log.e("dalObj.getThird2", dalObj.getThird() + "");


            best1v.setText(dalObj.convertToTimeStringFormat(dalObj.getFirst()));
            best2v.setText(dalObj.convertToTimeStringFormat(dalObj.getSecond()));
            best3v.setText(dalObj.convertToTimeStringFormat(dalObj.getThird()));




           start = (Button)findViewById(R.id.startBTN);
             start.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View v) {
                     startActivity(new Intent(getApplicationContext(), GameActivity.class));
                     startActivity(getIntent());

                 }
             });

        select = (Button)findViewById(R.id.selectBTN);
        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v);
                Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });


        reset1 = (Button) findViewById(R.id.resetBTN);
        reset1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(dalObj.isBDEmpty()){
                    dalObj.initRecords();
                }else {
                    dalObj.resetScors();
                }
                Log.i("reset1", "onResume");
                onResume();

            }
        });


    }//onCreate

    @Override
    protected void onResume() {

        Log.i("onResume", "start");
        super.onResume();
        this.onCreate(null);
        Log.i("onResume", "end");

    }//onResume

    public void loadImagefromGallery(View view) {

            // Create intent to Open Image applications like Gallery, Google Photos
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            // Start the Intent
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);



    }//loadImagefromGallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();

                editor.putString("pic_" + step, selectedImage + "");
                editor.commit();
                step++;

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }//onActivityResult



}//MainActivity
