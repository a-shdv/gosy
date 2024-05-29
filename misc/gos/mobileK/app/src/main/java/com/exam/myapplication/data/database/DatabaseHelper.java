package com.exam.myapplication.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "sick_users";
    public static final String ID = "id";
    public static final String FIO = "fio";
    public static final String DOCTOR_FIO = "doctor_fio";
    public static final String IS_IN_HOSPITAL = "is_in_hospital";
    static final String DB_NAME = "examsdb";
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIO + " TEXT," +
                DOCTOR_FIO + " TEXT," +
                IS_IN_HOSPITAL + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
