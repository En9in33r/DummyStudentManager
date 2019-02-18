package com.example.dummystudentmanager.schedule;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dummystudentmanager.R;
import com.example.dummystudentmanager.discipline.DisciplineItem;
import com.example.dummystudentmanager.discipline.DisciplinesDB;

import java.util.ArrayList;
import java.util.List;

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

        // необходимо вытащить из базы данных Предметы элемент, у которого ID равно полученному disc_id, и вытащить имя

        DisciplinesDB helper_inside = new DisciplinesDB(context); // нужно вытащить из базы данных предметов (дисциплин) все строки, поместить имена в этот спиннер
        SQLiteDatabase database_inside = helper_inside.getReadableDatabase();
        Cursor cursor = database_inside.query(DisciplinesDB.TABLE_FAVOURITES,
                null,
                "_id = ?",
                new String[] { Integer.toString(d.disc_id + 1) },
                null, null, null);


        // magic code from StackOverflow

        cursor.moveToFirst();
        if (!cursor.isAfterLast())
        {
            //subjectArray.add(cursor.getString(cursor
            //        .getColumnIndex(DisciplinesDB.KEY_NAME)));
            //cursor.moveToNext();

            ((TextView)view.findViewById(R.id.disciplineNameTextView)).setText(cursor.getString(cursor.getColumnIndex(DisciplinesDB.KEY_NAME)));
        }
        cursor.close();

        /*((TextView)view.findViewById(R.id.disciplineNameTextView)).setText(d.disc_id);
        ((TextView)view.findViewById(R.id.dayTextView)).setText(d.day);
        ((TextView)view.findViewById(R.id.timeTextView)).setText(d.time);
        ((TextView)view.findViewById(R.id.auditoryTextView)).setText(d.auditory);
        ((TextView)view.findViewById(R.id.chetTextView)).setText(d.chet + "");*/

        return view;
    }
}
