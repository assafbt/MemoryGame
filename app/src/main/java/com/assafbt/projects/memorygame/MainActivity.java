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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainActivity extends Activity {
    Button start, select;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
  //  public String picUri[] = new String[8];
    int step = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("shPref", Context.MODE_PRIVATE);
        editor = prefs.edit();


           start = (Button)findViewById(R.id.startBTN);
             start.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View v) {
                     startActivity(new Intent(getApplicationContext(), GameActivity.class));

                 }
             });

        select = (Button)findViewById(R.id.selectBTN);
        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v);
                Toast.makeText(getApplicationContext(), "one picture was loaded", Toast.LENGTH_SHORT).show();
                /*
                for (int i=0;i<8;i++) {
                    loadImagefromGallery(v);
                    Toast.makeText(getApplicationContext(), "picture number "+i+" from 8 was loaded", Toast.LENGTH_SHORT).show();
                }
*/
            }
        });


    }

    public void loadImagefromGallery(View view) {

            // Create intent to Open Image applications like Gallery, Google Photos
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Start the Intent

            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
           // Toast.makeText(this, "picture number "+i+" from 8 was loaded", Toast.LENGTH_SHORT).show();

    }//loadImagefromGallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                // System.out.println("###  selected uri "+selectedImage);
                //  picUri[step]=""+selectedImage;
                /*System.out.println("### picUri  " + picUri[step]);*/

                //  editor.putInt(arrayName +"_size", array.length);
                // for(int i=0;i<8;i++)
                editor.putString("pic_" + step, selectedImage+"");
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



        /*

        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                // System.out.println("###  selected uri "+selectedImage);
                //  picUri[step]=""+selectedImage;
                /*//*System.out.println("### picUri  " + picUri[step]);*//**//*

                //  editor.putInt(arrayName +"_size", array.length);
                // for(int i=0;i<8;i++)
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
        }*/

    }//onActivityResult




/*
    public void loadImagefromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image*//*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMG);
*//*
        for(int i = 0;i<8;i++) {

        }*//*
    }//loadImagefromGallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
               // System.out.println("###  selected uri "+selectedImage);
              //  picUri[step]=""+selectedImage;
                *//*System.out.println("### picUri  " + picUri[step]);*//*

              //  editor.putInt(arrayName +"_size", array.length);
               // for(int i=0;i<8;i++)
                editor.putString("pic_" + step, selectedImage+"");
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

    }//onActivityResult*/

/*    public String[] getPicUri(){
        for(int i=0;i<8;i++)
        System.out.println("### getPicUri  "+picUri[i]);
        return picUri;
    }*/
}
