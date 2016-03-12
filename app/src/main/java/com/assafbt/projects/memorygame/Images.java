/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.provider.BaseColumns;

/**
 * Created by assafbt on 12/03/2016.
 */
public class Images {

    public static  abstract  class ImageEntery implements BaseColumns {
        public static final String TABLE_NAME = "ImagesDB";
        public static final String NUMBER = "image_number";
        public static final String ADDRESS = "image_address";


    }
}
