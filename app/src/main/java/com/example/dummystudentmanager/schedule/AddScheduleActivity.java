package com.example.dummystudentmanager.schedule;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.dummystudentmanager.R;
import com.example.dummystudentmanager.discipline.DisciplinesDB;

import java.util.ArrayList;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity {

    Spinner subjectSpinner;
    Spinner daySpinner;
    Spinner timeSpinner;
    EditText auditoryEditText;
    Switch chetSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        subjectSpinner = findViewById(R.id.spinnerSubject); // заполняется значениями из базы данных
        daySpinner = findViewById(R.id.spinnerDay); // заполняется здесь, все, что ниже - тоже
        timeSpinner = findViewById(R.id.spinnerTime);
        auditoryEditText = findViewById(R.id.editTextAuditory);
        chetSwitch = findViewById(R.id.switchChet);

        DisciplinesDB helper_inside = new DisciplinesDB(this); // нужно вытащить из базы данных предметов (дисциплин) все строки, поместить имена в этот спиннер
        SQLiteDatabase database_inside = helper_inside.getReadableDatabase();
        Cursor cursor = database_inside.query(DisciplinesDB.TABLE_FAVOURITES,
                null,
                null,
                null,
                null, null, null);


        // magic code from StackOverflow
        List<String> subjectArray =  new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            subjectArray.add(cursor.getString(cursor
                    .getColumnIndex(DisciplinesDB.KEY_NAME)));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, subjectArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subjectSpinner.setAdapter(adapter);

        //again

        List<String> dayArray =  new ArrayList<>();

        dayArray.add("Понедельник");
        dayArray.add("Вторник");
        dayArray.add("Среда");
        dayArray.add("Четверг");
        dayArray.add("Пятница");
        dayArray.add("Суббота");


        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, dayArray);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter1);

        // and again

        List<String> timeArray =  new ArrayList<>();

        timeArray.add("8:15");
        timeArray.add("9:55");
        timeArray.add("11:35");
        timeArray.add("13:05");
        timeArray.add("13:45");
        timeArray.add("15:25");
        timeArray.add("17:05");
        timeArray.add("18:25");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, timeArray);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeSpinner.setAdapter(adapter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_button_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.submit:
                ScheduleDB helper_inside = new ScheduleDB(this);
                SQLiteDatabase database_inside = helper_inside.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("disc_id", subjectSpinner.getSelectedItemPosition());
                values.put("day", daySpinner.getSelectedItemPosition());
                values.put("chet", chetSwitch.isChecked()); // true - нечетная, false - четная
                values.put("time", timeSpinner.getSelectedItemPosition());
                values.put("auditory", auditoryEditText.getText().toString());
                database_inside.insert(ScheduleDB.TABLE_FAVOURITES, null, values);
                finish();
                // добавить в базу данных расписаний
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
