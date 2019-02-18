package com.example.dummystudentmanager.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScheduleDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ScheduleDB";
    public static final String TABLE_FAVOURITES = "schedule";

    public static final String KEY_ID = "_id";
    public static final String KEY_DISC_ID = "disc_id";
    public static final String KEY_DAY = "day";
    public static final String KEY_CHET = "chet";
    public static final String KEY_TIME = "time";
    public static final String KEY_AUDITORY = "auditory";

    public ScheduleDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_FAVOURITES + "(" + KEY_ID +
                " integer primary key," + KEY_DISC_ID + " text," + KEY_DAY + " text," + KEY_CHET + " text," + KEY_TIME + " text," + KEY_AUDITORY + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_FAVOURITES);
        onCreate(db);
    }
}
