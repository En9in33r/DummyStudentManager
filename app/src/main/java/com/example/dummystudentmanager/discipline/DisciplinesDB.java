package com.example.dummystudentmanager.discipline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DisciplinesDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "DisciplinesDB";
    public static final String TABLE_FAVOURITES = "disciplines";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IS_DEBT = "is_debt";

    public DisciplinesDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table " + TABLE_FAVOURITES + "(" + KEY_ID +
                " integer primary key," + KEY_NAME + " text," + KEY_TEACHER + " text," + KEY_DESCRIPTION + " text," + KEY_IS_DEBT + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_FAVOURITES);
        onCreate(sqLiteDatabase);
    }
}
