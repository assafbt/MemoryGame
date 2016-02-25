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
        //String DBid = BestTime.TimeEntry._ID;

       // long zeroRecord=0;
        //db.delete(BestTime.TimeEntry.TABLE_NAME ,null,null);
        for(int i =0; i<3; i++) {
            values.put(BestTime.TimeEntry.ROW1, i + "");
            values.put(BestTime.TimeEntry.SCORE, 999999 + "");
            db.insert(BestTime.TimeEntry.TABLE_NAME, null, values);
        }
       /* Log.i("INIT", "init first " + zeroRecord);
        values.put(BestTime.TimeEntry.SECOND, zeroRecord);
        Log.i("INIT", "init second " + zeroRecord);
        values.put(BestTime.TimeEntry.THIRD, zeroRecord);
        Log.i("INIT", "init third " + zeroRecord);*/


        //insert database
     //   long affectedColumnId = db.insert(BestTime.TimeEntry.TABLE_NAME, null, values);


        Log.i("INIT", "init finish ");
        db.close();
        //Log.i("INIT", "1st:"+getFirst()+"  2nd:" +getSecond()+" 3rd:"+getThird());
        Log.i("INIT", "after close ");

    }//initRecords

    // update the value of record
    public void updateRecord(int time1)  {
        int third = getRecord(2);
        int second = getRecord(1);
        int first = getRecord(0);

        /* boolean is1st,is2nd,is3rd;

        is1st= true;
        is2nd=false;
        is3rd=false;*/


        db = dbHelper.getWritableDatabase();


      //  if ((is1st)&&((first >= time1) || (first<10))){


        if(time1<first){
        //set data
        ContentValues values = new ContentValues();

        values.put(BestTime.TimeEntry.SCORE, second+"");
        values.put(BestTime.TimeEntry.ROW1, 2+"");
        String where = BestTime.TimeEntry.ROW1 + "=?";
        String[] whereArgs = {2 +""};
        db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

        values.put(BestTime.TimeEntry.SCORE, first+"");
        values.put(BestTime.TimeEntry.ROW1, 1+"");
        where = BestTime.TimeEntry.ROW1 + "=?";
        whereArgs[0] = 1+"";
        db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

        values.put(BestTime.TimeEntry.SCORE, time1+"");
        values.put(BestTime.TimeEntry.ROW1, 0+"");
        where = BestTime.TimeEntry.ROW1 + "=?";
        whereArgs[0] = 0 +"";
        db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

        Log.i("updateRecord", "record on first place");
        // return;
    }//1 row
        else if(time1<second){
            //set data
            ContentValues values = new ContentValues();

            values.put(BestTime.TimeEntry.SCORE, second+"");
            values.put(BestTime.TimeEntry.ROW1, 2+"");
            String where = BestTime.TimeEntry.ROW1 + "=?";
            String[] whereArgs = {2 +""};
            db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

            values.put(BestTime.TimeEntry.SCORE, time1+"");
            values.put(BestTime.TimeEntry.ROW1, 1+"");
            where = BestTime.TimeEntry.ROW1 + "=?";
            whereArgs[0] = 1+"";
            db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

            Log.i("updateRecord", "record on first place");
            // return;
        }//2 row
        else if(time1<third){
            //set data
            ContentValues values = new ContentValues();

            values.put(BestTime.TimeEntry.SCORE, time1+"");
            values.put(BestTime.TimeEntry.ROW1, 2+"");
            String where = BestTime.TimeEntry.ROW1 + "=?";
            String[] whereArgs = {2 +""};
            db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);

            Log.i("updateRecord", "record on first place");
            // return;
        }//3 row

/*        else{
            is1st =false;
            is2nd=true;
        }//else 1st

        if ((is2nd)&&((second >= time1) || (second<10))){

            //set data
            ContentValues values = new ContentValues();
            values.put(BestTime.TimeEntry.FIRST, first);
            values.put(BestTime.TimeEntry.SECOND, time1);
            values.put(BestTime.TimeEntry.THIRD, second);

            String DBid = BestTime.TimeEntry._ID;
            String where = BestTime.TimeEntry._ID + "=?";
            String[] whereArgs = {DBid +""};
            db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);


            Log.i("updateRecord", "record on second place");
            return;
        }//if 2nd
        else {
            is2nd=false;
            is3rd=true;
        }//else 2nd


        if ((is3rd)&&((third >= time1) || (third<10))){
            //set data
            ContentValues values = new ContentValues();
            values.put(BestTime.TimeEntry.FIRST, first);
            values.put(BestTime.TimeEntry.SECOND, second);
            values.put(BestTime.TimeEntry.THIRD, time1);
                    *//*
            String where = BestTime.TimeEntry.LVL_CMPX + "=?";
            String[] whereArgs = {place +""};*//*
            db.update(BestTime.TimeEntry.TABLE_NAME, values, null, null);

            Log.i("updateRecord", "record on third place");
            return;
        }//if 3rd
        else {
                    is3rd= false;
        }//else 3rd*/
        db.close();



    }//updateRecord

/*
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

    public void setThird(int third){
        db = dbHelper.getWritableDatabase();


    }
*/


    // get the Record value at place
    public int getRecord(int place){
        db = dbHelper.getReadableDatabase();

        int recordReturn = 0;
        int idIndex,idRecord,temp;
        String table = BestTime.TimeEntry.TABLE_NAME;
        crs = db.rawQuery("SELECT * FROM " + table, null);

        idRecord = crs.getColumnIndex(BestTime.TimeEntry.SCORE);
        idIndex = crs.getColumnIndex(BestTime.TimeEntry.ROW1);


        while (crs.moveToNext()) {
            temp = crs.getInt(idIndex);
            if (temp == place) {
                recordReturn = crs.getInt(idRecord);
                break;
            }
            Log.e("getRecord", "get record " + recordReturn + " from index " + place);
        }


        db.close();
        return recordReturn;
    }//getRecord


    //Convert To Time  Format
    public String convertToTimeStringFormat(int dbRecord ){
        Log.i("convert ", "record= "+dbRecord );
        int secs=0;
        int milliseconds=0;
        secs = (int) (dbRecord / 1000);
        secs = secs % 60;
        milliseconds = (int) (dbRecord % 1000);

        String dBT = String.format("%02d", secs) + ":" + String.format("%03d", milliseconds);
        Log.i("convertTime", "convert " + dbRecord);
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

    public void resetScors(){

            //get db
            db = dbHelper.getWritableDatabase();


            //set data
            ContentValues values = new ContentValues();;


            long zeroRecord=0;


        for(int i =0; i<3; i++) {

            values.put(BestTime.TimeEntry.SCORE, 999999+"");
            values.put(BestTime.TimeEntry.ROW1, i+"");
            String where = BestTime.TimeEntry.ROW1 + "=?";
            String[] whereArgs = {i +""};
            db.update(BestTime.TimeEntry.TABLE_NAME, values, where, whereArgs);
        }


            Log.i("reset Scors", " finish ");
            db.close();
            //Log.i("INIT", "1st:"+getFirst()+"  2nd:" +getSecond()+" 3rd:"+getThird());
            Log.i("reset Scors", "after close ");

    }//resetScors
}
