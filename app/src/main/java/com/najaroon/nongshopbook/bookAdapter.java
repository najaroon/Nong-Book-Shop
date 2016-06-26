package com.najaroon.nongshopbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Najaroon on 26/6/2559.
 */
public class bookAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] nameStrings, priceStrings, coverStrings;

    public bookAdapter(Context context, String[] nameStrings, String[] priceStrings, String[] coverStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
        this.coverStrings = coverStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.book_listview, parent, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.textView7);
        TextView priceTextView = (TextView) view.findViewById(R.id.textView8);
        ImageView coverImageView = (ImageView) view.findViewById(R.id.imageView2);

        nameTextView.setText(nameStrings[position]);
        priceTextView.setText(priceStrings[position]);

        Picasso.with(context).load(coverStrings[position]).resize(150, 180).into(coverImageView);

        return view;
    }
}   //  Main Class
