package com.test.datafetchmultiple;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Shree on 10/25/2016.
 */
public class Customadapter extends ArrayAdapter<String> {
    private String[] androidosnames;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;
    public Customadapter(Activity context,    String[] androidosnames,  Bitmap[] bitmaps  ) {
        super(context, R.layout.layout, androidosnames);
        this.context = context;
        // this.urls = urls;
        this.bitmaps = bitmaps;
        this.androidosnames = androidosnames;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout, null, true);
        TextView  androidos = (TextView) listViewItem.findViewById(R.id.tvandroidosnames);
        // TextView textView = (TextView) listViewItem.findViewById(R.id.tvurl);
        //  textView.setText(urls[position] );
        androidos.setText(androidosnames[position] );
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position], 100, 50, false));
        return  listViewItem;
    }
}