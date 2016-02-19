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
 * Created by assafbt on 18/02/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME ="bestTimeDB.db";


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+BestTime.TimeEntry.TABLE_NAME+" ( "+
                        BestTime.TimeEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        BestTime.TimeEntry.FIRST+" INTEGER, "+
                        BestTime.TimeEntry.SECOND+" INTEGER "+
                        BestTime.TimeEntry.THIRD+" INTEGER "+

                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+BestTime.TimeEntry.TABLE_NAME);
        onCreate(db);
    }
}
