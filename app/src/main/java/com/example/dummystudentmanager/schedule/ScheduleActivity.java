package com.example.dummystudentmanager.schedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dummystudentmanager.R;
import com.example.dummystudentmanager.discipline.DisciplineItem;
import com.example.dummystudentmanager.discipline.DisciplinesDB;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    FloatingActionButton fab;

    ArrayList<ScheduleItem> scheduleItems;
    ListView listView;
    ScheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this, AddScheduleActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.scheduleListView);
        scheduleItems = new ArrayList<>();
        adapter = new ScheduleAdapter(this, scheduleItems);
        listView.setAdapter(adapter);

        ScheduleDB helper_inside = new ScheduleDB(this);
        SQLiteDatabase database_inside = helper_inside.getReadableDatabase();
        Cursor cursor = database_inside.query(ScheduleDB.TABLE_FAVOURITES,
                null,
                null,
                null,
                null, null, null);


        // magic code from StackOverflow
        // List<String> subjectArray =  new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            scheduleItems.add(new ScheduleItem(cursor.getInt(cursor
                    .getColumnIndex(ScheduleDB.KEY_DISC_ID)),
                    cursor.getInt(cursor.getColumnIndex(ScheduleDB.KEY_DAY)),
                    cursor.getString(cursor.getColumnIndex(ScheduleDB.KEY_TIME)),
                    cursor.getInt(cursor.getColumnIndex(ScheduleDB.KEY_AUDITORY)),
                    cursor.getString(cursor.getColumnIndex(ScheduleDB.KEY_CHET))));

            cursor.moveToNext();
        }
        cursor.close();
    }
}
