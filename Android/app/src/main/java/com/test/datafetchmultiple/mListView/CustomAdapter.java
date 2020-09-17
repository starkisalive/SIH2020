package com.test.datafetchmultiple.mListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.squareup.picasso.Picasso;
import com.test.datafetchmultiple.AfterTrackC;
import com.test.datafetchmultiple.R;
import com.test.datafetchmultiple.mData.TVShow;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<TVShow> tvShows;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<TVShow> tvShows) {
        this.c = c;
        this.tvShows = tvShows;
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }

    @Override
    public Object getItem(int position) {
        return tvShows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView landmark= (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView lati = (TextView) convertView.findViewById(R.id.latTextView);
        TextView date= (TextView) convertView.findViewById(R.id.lonTextView);
        TextView longi= (TextView) convertView.findViewById(R.id.longi);
        ImageView img = (ImageView) convertView.findViewById(R.id.teacherImageView);

        final String name=tvShows.get(position).getName();
        final String image=tvShows.get(position).getImage();
        final String desc = tvShows.get(position).getDesc();
        String date1 = tvShows.get(position).getDate();
        String lon = tvShows.get(position).getLon();
        String lat = tvShows.get(position).getLat();

        id.setText(name);
        Picasso.get().load(tvShows.get(position).getImage()).placeholder(R.drawable.placeholder).into(img);
        landmark.setText(desc);
        lati.setText(lat);
        longi.setText(lon);
        date.setText(date1);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(c,name,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("message");
                intent.putExtra("image",image);
                intent.putExtra("land",desc);
                LocalBroadcastManager.getInstance(c).sendBroadcast(intent);
                c.startActivity(new Intent(c, AfterTrackC.class));
            }
        });

        return convertView;
    }
}
