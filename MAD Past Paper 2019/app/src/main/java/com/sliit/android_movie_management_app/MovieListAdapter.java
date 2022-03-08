package com.sliit.android_movie_management_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MovieListAdapter extends BaseAdapter {

    ArrayList<String> mName, mYear;
    Context context;

    public MovieListAdapter(ArrayList<String> mName, ArrayList<String> mYear, Context context) {
        this.mName = mName;
        this.mYear = mYear;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // override other abstract methods here
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        /*if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position));*/
        return convertView;
    }
}
