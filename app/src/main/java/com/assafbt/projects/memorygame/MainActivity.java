/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    Button select, reset1,reset2, aboutBtn,start, languageBtn;
    private static int RESULT_LOAD_IMG = 8;
    String imgDecodableString;
    int step = 0;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    DAL dalObj = new DAL(this);
    SQLiteDatabase db;
    Locale locale;
    boolean isEnglishLanguage;
    BestTime_DBHelper bestTimeDbHelper;
    String imagUri[][]= new String[8][2];
    int drawable_images[]= new int[]{
            R.drawable.img01,
            R.drawable.img02,
            R.drawable.img03,
            R.drawable.img04,
            R.drawable.img05,
            R.drawable.img06,
            R.drawable.img07,
            R.drawable.img09
    };


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


            aboutBtn = (Button)findViewById(R.id.infoBtn);
            aboutBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),WebViewActivity.class));
                    startActivity(getIntent());
                }
                });


           start = (Button) findViewById(R.id.startBTN);
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

                startActivity(new Intent(getApplicationContext(), SelectingImages.class));
                startActivity(getIntent());



/*
                loadImagefromGallery(v);
                Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();*/
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



        // reset to defualt images at Drawable
        reset2 = (Button) findViewById(R.id.resetIMG);
        reset2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                for (int i=0; i<8; i++){
                    editor.putString("pic_" + i, drawable_images[i] + "");
                    editor.commit();
                    Log.i("reset img", i+"");

                }
            }//onClick


        });

        languageBtn = (Button) findViewById(R.id.language_btn);
        /* setLanguage("he");
       String language = Locale.getDefault().getLanguage();
        Log.i("locale", "locale,  " + language);
        if (language == "en") {
            isEnglishLanguage = true;
        }
        else {
            isEnglishLanguage = false;
            language = "he";
        }
        languageBtn.setText(language);
        languageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("locale","locale, isEnglishLanguage = " + isEnglishLanguage);
                if (isEnglishLanguage) {
                    setLanguage("he");
                    isEnglishLanguage = false;
                    languageBtn.clearComposingText();
                    languageBtn.setText("heb");
                    Log.i("locale","locale, isEnglishLanguage changed to " + isEnglishLanguage);

                }
                else {
                    setLanguage("en");
                    isEnglishLanguage = true;
                    languageBtn.clearComposingText();
                    languageBtn.setText("en");
                    Log.i("locale","locale, isEnglishLanguage changed to " + isEnglishLanguage);
                }
            }//onClick


        });*/
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

                editor.putString("pic_" + step, getRealPathFromURI(selectedImage));
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


    public String getRealPathFromURI(Uri contentUri) {

        Cursor cursor = null;
        try {

            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getContentResolver().query(contentUri,  proj, null, null, null);
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            return cursor.getString(column_index);
        } finally {

            if (cursor != null) {
                cursor.close();
            }
        }
    }//getRealPathFromURI

    public void setLanguage(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);
        Log.i("locale","locale, language changed to " + lang);
    }
}//MainActivity
