package com.example.mob.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "task_db";

    public static final String ID = "id";

    public static final String CLIENTS_TABLE = "clients";
    public static final String CLIENTS_NAME = "name";


    public static final String AUTOS_TABLE = "autos";
    public static final String AUTOS_MODEL = "model";
    public static final String AUTOS_CLIENT_ID = "client_id";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableClients = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL" +
                        ");",
                CLIENTS_TABLE, ID, CLIENTS_NAME);
        String createTableAutos = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER," +
                        "FOREIGN KEY (%s) REFERENCES %s (%s) ON DELETE CASCADE" +
                        ");",
                AUTOS_TABLE, ID, AUTOS_MODEL, AUTOS_CLIENT_ID, AUTOS_CLIENT_ID, CLIENTS_TABLE, ID);
        db.execSQL(createTableClients);
        db.execSQL(createTableAutos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableAutos = String.format("DROP TABLE IF EXISTS %s", AUTOS_TABLE);
        String dropTableClients = String.format("DROP TABLE IF EXISTS %s", CLIENTS_TABLE);

        db.execSQL(dropTableAutos);
        db.execSQL(dropTableClients);

        onCreate(db);
    }
}
