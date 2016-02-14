/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by assafbt on 14/02/2016.
 */
public class CardAdapter extends ArrayAdapter<Card> {

    private LayoutInflater layoutInflater;//  read the xml file
    private Context context;

    public CardAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }




    public View getView(int position, View convertView, ViewGroup parent, Card Obj) {

        View view;
        view = layoutInflater.inflate(R.layout.activity_game, null);
        String Rid;
        /*for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {

                // chang the images on the table

            }
        }

*/


                Card card = new Card();//getItem(position);

                //sRid = "R.id.img" + i + "" + j;
                ImageView picture = (ImageView) view.findViewById(R.id.img00);

                Picasso.with(context).load(card.getImgUp()).placeholder(R.drawable.images).fit().into(picture);



        return view;

    }

}
