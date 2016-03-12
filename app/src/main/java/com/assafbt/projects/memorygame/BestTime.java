/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.provider.BaseColumns;

/**
 * Created by assafbt on 18/02/2016.
 */
public class BestTime {


    public static  abstract  class TimeEntry implements BaseColumns {
        public static final String TABLE_NAME = "bestTimeDB";
        public static final String FIRST = "first_record";
        public static final String SECOND = "second_record";
        public static final String THIRD = "third_record";

    }

}
