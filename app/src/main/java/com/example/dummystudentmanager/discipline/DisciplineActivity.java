package com.example.dummystudentmanager.discipline;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dummystudentmanager.R;

import java.util.ArrayList;

public class DisciplineActivity extends AppCompatActivity {

    FloatingActionButton fab;

    ArrayList<DisciplineItem> disciplineItems;
    ListView listView;
    DisciplinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline);

        fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisciplineActivity.this, AddDisciplineActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.listViewDisciplines);
        disciplineItems = new ArrayList<>();
        adapter = new DisciplinesAdapter(this, disciplineItems);
        listView.setAdapter(adapter);

        DisciplinesDB helper_inside = new DisciplinesDB(this); // нужно вытащить из базы данных предметов (дисциплин) все строки, поместить имена в этот спиннер
        SQLiteDatabase database_inside = helper_inside.getReadableDatabase();
        Cursor cursor = database_inside.query(DisciplinesDB.TABLE_FAVOURITES,
                null,
                null,
                null,
                null, null, null);


        // magic code from StackOverflow
        // List<String> subjectArray =  new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            disciplineItems.add( new DisciplineItem(cursor.getString(cursor
                    .getColumnIndex(DisciplinesDB.KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(DisciplinesDB.KEY_TEACHER)),
                    cursor.getString(cursor.getColumnIndex(DisciplinesDB.KEY_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(DisciplinesDB.KEY_IS_DEBT))) );

            cursor.moveToNext();
        }
        cursor.close();
    }
}
