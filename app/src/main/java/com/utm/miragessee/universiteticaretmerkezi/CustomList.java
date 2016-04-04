package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by miragessee on 4.04.2016.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;

    public CustomList(Activity context,
                      String[] web) {
        super(context, R.layout.list_ekle, web);
        this.context = context;
        this.web = web;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_ekle, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        txtTitle.setText(web[position]);

        return rowView;
    }
}
