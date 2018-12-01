package com.example.aniltaskiran.calendar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReminderListAdapter extends BaseAdapter {

    public ArrayList<Reminder> adapterArrayList;
    public Activity context;

    public ReminderListAdapter(ArrayList<Reminder> adapterArrayList, Activity context) {
        this.adapterArrayList = adapterArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return adapterArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return adapterArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Reminder reminder = (Reminder) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {


            LayoutInflater inflater = context.getLayoutInflater();

            convertView = inflater.inflate(R.layout.reminder_list_row, null);

            TextView titleTextView = convertView.findViewById(R.id.titleTextView);
            TextView detailTextView = convertView.findViewById(R.id.detailTextView);


            titleTextView.setText(reminder.title);
            detailTextView.setText(reminder.detail);

            return convertView;
        } else {
            return null;

        }
    }
}
