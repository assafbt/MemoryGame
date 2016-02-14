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



    @Override
    public View getView(int position, View convertView, ViewGroup parent, Card Obj) {

        View view;
        view = layoutInflater.inflate(R.layout.activity_main, null);
        String Rid;
        for (int i=0;i<=3;i++){
            for (int j=0;j<=3;j++){


        Card ca = getItem(position);
        Rid = "R.id.img"+i+""+j;
        ImageView picture = (ImageView) view.findViewById(R.id.img+""+i+""+j);

        Picasso.with(context).load(ca.getImgUp()).placeholder(R.drawable.wether3).fit().into(picture);


            }
        }


        return view;



}
