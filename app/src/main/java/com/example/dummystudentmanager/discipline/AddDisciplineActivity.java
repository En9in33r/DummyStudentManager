package com.example.dummystudentmanager.discipline;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

import com.example.dummystudentmanager.R;

public class AddDisciplineActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText teacherEdittext;
    EditText taskEdittext;
    Switch debtSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline);

        nameEditText = findViewById(R.id.nameDiscipline);
        teacherEdittext = findViewById(R.id.teacherName);
        taskEdittext = findViewById(R.id.taskDescription);
        debtSwitch = findViewById(R.id.isDebtSwitch);
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
                DisciplinesDB helper_inside = new DisciplinesDB(this);
                SQLiteDatabase database_inside = helper_inside.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", nameEditText.getText().toString());
                values.put("teacher", teacherEdittext.getText().toString());
                values.put("description", taskEdittext.getText().toString());
                values.put("is_debt", debtSwitch.isChecked());
                database_inside.insert(DisciplinesDB.TABLE_FAVOURITES, null, values);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
