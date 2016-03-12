/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by assafbt on 12/03/2016.
 */
public class Images_DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =0;
    public static final String DATABASE_NAME ="ImagesDB.db";

    public Images_DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+ Images.ImageEntery.TABLE_NAME + " ( " +
                        Images.ImageEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        Images.ImageEntery.NUMBER+ " INTEGER, " +
                        Images.ImageEntery.ADDRESS + " TEXT, " +
                        " ); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+BestTime.TimeEntry.TABLE_NAME);
        onCreate(db);
    }
}
