/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
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

    String[] premissionList = {
            "READ_EXTERNAL_STORAGE",
            "WRITE_EXTERNAL_STORAGE" };
    String path = "android.premission.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_images);


        prefs = getSharedPreferences("shPref", Context.MODE_PRIVATE);
        editor = prefs.edit();

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        slct1 = (Button)findViewById(R.id.select1);
        slct1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,1);
            }
        });

        slct2 = (Button)findViewById(R.id.select2);
        slct2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,2);
                }
        });


        slct3 = (Button)findViewById(R.id.select3);
        slct3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,3);
                }
        });

        slct4 = (Button)findViewById(R.id.select4);
        slct4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,4);
                }
        });



        //


        slct5 = (Button)findViewById(R.id.select5);
        slct5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,5);
                }
        });

        slct6 = (Button)findViewById(R.id.select6);
        slct6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,6);
                }
        });


        slct7 = (Button)findViewById(R.id.select7);
        slct7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,7);
               }
        });

        slct8 = (Button)findViewById(R.id.select8);
        slct8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPermissionGranted  = getPremission(Manifest.permission.READ_EXTERNAL_STORAGE);

                if (isPermissionGranted)
                    loadImagefromGallery(v,8);
                }
        });

        btnBack = (Button)findViewById(R.id.back_from_select_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
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

    final int premissionCheck = 34;

    private boolean getPremission( String premission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i("BuildVERSION", Build.VERSION.SDK_INT+"");
            if (ContextCompat.checkSelfPermission(this, premission) == PackageManager.PERMISSION_GRANTED){

                // the premission already approved,
                Log.i("premission"+premission,"approved");
                return true;
            } else {
                Log.i("premission"+premission," is not approved");
                requestPermissions(new String[]{premission}, premissionCheck);

                //need to check if the user approved the permission after asking him


                return false;
            }
        }
        return true;
    } //getPremission


    @Override
    public void onRequestPermissionsResult(int reqeustCode, String permissions[], int[] grantResults) {
        switch (reqeustCode) {

            case premissionCheck: {
                // if the user cancel the permissions request
                Log.i("PermissionsResult", "the user cancel the permissions request");

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // this code block will work if you confirm that you are reqeusting permission
                    Log.i("PermissionsResult", "you are reqeusting permission");
                } else {

                    // this code block will work if the user rejects your permission
                    Log.i("PermissionsResult", "the user rejects your permission");
                    Toast.makeText(getApplicationContext(), "Please appprove the permission ", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }//onRequestPermissionsResult

}//SelectingImages
