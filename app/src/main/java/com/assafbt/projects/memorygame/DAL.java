/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by assafbt on 18/02/2016.
 */

public class DAL {
    SQLiteDatabase db;
    DBHelper dbHelper;
    Cursor crs;




    public DAL(Context c){
        dbHelper = new DBHelper(c);


    }//DAL

    // first init
    public void initRecords(){
        //get db
        db = dbHelper.getWritableDatabase();

        //set data
        ContentValues values = new ContentValues();;

        int zeroRecord=0;

        values.put(BestTime.TimeEntry.FIRST, zeroRecord);
        Log.e("INIT", "init first " + zeroRecord);
        values.put(BestTime.TimeEntry.SECOND, zeroRecord);
        Log.e("INIT", "init second " + zeroRecord);
        values.put(BestTime.TimeEntry.THIRD, zeroRecord);
        Log.e("INIT", "init third " + zeroRecord);

        //insert database
        db.insert(BestTime.TimeEntry.TABLE_NAME, null, values);

        Log.e("INIT", "init finish ");
        db.close();
        Log.e("INIT", "after close ");

    }

    // update the value of record
    public void updateRecord(int time1)  {
        int third = getThird();
        int second = getSecond();
        int first = getFirst();
        boolean is1st,is2nd,is3rd;

        is1st= true;
        is2nd=is3rd=false;


        db = dbHelper.getWritableDatabase();

        if ((is1st)&&((first >= time1) || (first== 0))){

            //set data
            ContentValues values = new ContentValues();
            values.put(BestTime.TimeEntry.FIRST, time1);
            values.put(BestTime.TimeEntry.SECOND, first);
            values.put(BestTime.TimeEntry.THIRD, second);
/*
            String where = BestTime.TimeEntry.LVL_CMPX + "=?";
            String[] whereArgs = {place +""};*/
            db.update(BestTime.TimeEntry.TABLE_NAME, values, null, null);

            Log.e("updateRecord", "record on first place");

        }//if 1st

        else{
            is1st =false;
            is2nd=true;
        }//else 1st

        if ((is2nd)&&((second >= time1) || (second== 0))){

            //set data
            ContentValues values = new ContentValues();
            values.put(BestTime.TimeEntry.SECOND, time1);
            values.put(BestTime.TimeEntry.THIRD, second);
/*
        String where = BestTime.TimeEntry.LVL_CMPX + "=?";
        String[] whereArgs = {place +""};*/
            db.update(BestTime.TimeEntry.TABLE_NAME, values, null, null);

            Log.e("updateRecord", "record on second place");

        }//if 2nd
        else {
            is2nd=false;
            is3rd=true;
        }//else 2nd


        if ((is3rd)&&((third >= time1) || (third== 0))){
            //set data
            ContentValues values = new ContentValues();
            values.put(BestTime.TimeEntry.THIRD, time1);
                    /*
            String where = BestTime.TimeEntry.LVL_CMPX + "=?";
            String[] whereArgs = {place +""};*/
            db.update(BestTime.TimeEntry.TABLE_NAME, values, null, null);

            Log.e("updateRecord", "record on third place");

        }//if 3rd
        else {
                    is3rd= false;
        }//else 3rd
        db.close();



    }//updateRecord

    // get the First Best Record value
    public int getFirst(){
        db = dbHelper.getReadableDatabase();

        int recordReturn = 0;

        String table = BestTime.TimeEntry.TABLE_NAME;
        crs = db.rawQuery("SELECT * FROM " + table, null);

        recordReturn = crs.getColumnIndex(BestTime.TimeEntry.FIRST);

        db.close();
        return recordReturn;
    }//getFirst


    // get the Second  Best Record value
    public int getSecond(){
        db = dbHelper.getReadableDatabase();

        String table = BestTime.TimeEntry.TABLE_NAME;
        crs = db.rawQuery("SELECT * FROM " + table, null);

        int recordReturn = crs.getColumnIndex(BestTime.TimeEntry.SECOND);

        db.close();
        return recordReturn;
    }//getSecond


    // get the Third  Best Record value
    public int getThird(){
        db = dbHelper.getReadableDatabase();

        int recordReturn = 0;

        String table = BestTime.TimeEntry.TABLE_NAME;
        crs = db.rawQuery("SELECT * FROM " + table, null);

        recordReturn = crs.getColumnIndex(BestTime.TimeEntry.THIRD);

        db.close();
        return recordReturn;
    }//getThird


    //Convert To Time  Format
    public String convertToTimeStringFormat(int dbRecord ){
        int secs,milliseconds;
        secs = (int) (dbRecord / 1000);
        secs = secs % 60;
        milliseconds = (int) (dbRecord % 1000);

        String dBT = String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
        Log.e("convertTime", "convert " + dbRecord);
        return dBT;

    }//convertToTimeStringFormat


    // check if the DB is empty
    public boolean isBDEmpty() {
        //get db
        db = dbHelper.getReadableDatabase();
        crs = db.rawQuery("SELECT * FROM " + BestTime.TimeEntry.TABLE_NAME, null);
        if (crs.getCount() > 0) {
            db.close();
            return false;
        }
        db.close();
        return true;
    }//isBDEmpty
}
