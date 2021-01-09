package com.example.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class PlgListAdapter extends ArrayAdapter<Requests> {

    private static final String TAG = "PlgListAdapter";

    private Context mContext;
    private int mResource;

    public PlgListAdapter(@NonNull android.content.Context context, int resource, @NonNull ArrayList<Requests> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String type = getItem(position).getType();
        String date = getItem(position).getDateStr();
        String time = getItem(position).getTimeStr();

        Requests pledge = new Requests(type, date, time);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvType = convertView.findViewById(R.id.tv_list_type);
        TextView tvDate = convertView.findViewById(R.id.tv_list_date);
        TextView tvTime = convertView.findViewById(R.id.tv_list_time);

        tvType.setText(type);
        tvDate.setText(date);
        tvTime.setText(time);

        return convertView;
    }
}
