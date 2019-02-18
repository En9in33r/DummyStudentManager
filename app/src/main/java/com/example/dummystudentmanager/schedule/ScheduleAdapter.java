package com.example.dummystudentmanager.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dummystudentmanager.R;
import com.example.dummystudentmanager.discipline.DisciplineItem;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<ScheduleItem> scheduleItems;
    View view;

    public ScheduleAdapter(Context context, ArrayList<ScheduleItem> scheduleItems) {
        this.context = context;
        this.scheduleItems = scheduleItems;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return scheduleItems.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ScheduleItem getScheduleItem(int position) {
        return (ScheduleItem)getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if (view == null)
        {
            view = inflater.inflate(R.layout.schedule_item, parent, false);
        }

        final ScheduleItem d = getScheduleItem(position);



        if (d.disc_id == 0)
        {
            ((TextView)view.findViewById(R.id.disciplineNameTextView)).setText("жопа");
        }
        /*((TextView)view.findViewById(R.id.disciplineNameTextView)).setText(d.disc_id);
        ((TextView)view.findViewById(R.id.dayTextView)).setText(d.day);
        ((TextView)view.findViewById(R.id.timeTextView)).setText(d.time);
        ((TextView)view.findViewById(R.id.auditoryTextView)).setText(d.auditory);
        ((TextView)view.findViewById(R.id.chetTextView)).setText(d.chet + "");*/

        return view;
    }
}
