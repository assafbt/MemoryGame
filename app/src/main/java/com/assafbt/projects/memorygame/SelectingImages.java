/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.database.Cursor;
import android.provider.MediaStore;


public class SelectingImages extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 8;
    int step = 0;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Button slct1, slct2,slct3,slct4,slct5,slct6,slct7,slct8, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_images);


        prefs = getSharedPreferences("shPref", Context.MODE_PRIVATE);
        editor = prefs.edit();

        slct1 = (Button)findViewById(R.id.select1);
        slct1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,1);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });

        slct2 = (Button)findViewById(R.id.select2);
        slct2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,2);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });


        slct3 = (Button)findViewById(R.id.select3);
        slct3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,3);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });

        slct4 = (Button)findViewById(R.id.select4);
        slct4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,4);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });



        //


        slct5 = (Button)findViewById(R.id.select5);
        slct5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,5);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });

        slct6 = (Button)findViewById(R.id.select6);
        slct6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,6);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });


        slct7 = (Button)findViewById(R.id.select7);
        slct7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,7);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });

        slct8 = (Button)findViewById(R.id.select8);
        slct8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                loadImagefromGallery(v,8);
                //   Toast.makeText(getApplicationContext(), "one picture at the time", Toast.LENGTH_SHORT).show();
            }
        });
/*

        btnBack = (Button)findViewById(R.id.BackBTN);
                btnBack.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        startActivity(getIntent());

                    }
                });
*/

    }//onCreate


    public void loadImagefromGallery(View view, int place) {

        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        step = place;
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);



    }//loadImagefromGallery


    //Convert the image URI to the direct file system path of the image file
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Log.i("onActivityResult ","try");
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                Log.i("onActivityResult ","if");
                // Get the Image from data
                Uri selectedImage = data.getData();

                Log.i("onActivityResult ","uri= " + selectedImage);
                Log.i("onActivityResult ","uri= " + getRealPathFromURI(selectedImage));
                //step= place;


                editor.putString("pic_" + step, getRealPathFromURI(selectedImage) + "");
//                editor.putString("pic_" + step, selectedImage + "");

                Log.i("onActivityResult ", "putString, step: "+step);
                editor.commit();
                Log.i("onActivityResult ", "commit");
               // step++;

            } else {
                Log.i("onActivityResult ","else");
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.i("onActivityResult ","catch: "+ e);
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }//onActivityResult


}//SelectingImages
