package com.example.mob.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob.entity.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoRepo {
    private DbHelper dbHelper;

    public AutoRepo(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void createAuto(Auto auto) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.AUTOS_MODEL, auto.getModel());
        if (auto.getClientId() != null) {
            values.put(DbHelper.AUTOS_CLIENT_ID, auto.getClientId());
        } else {
            values.putNull(DbHelper.AUTOS_CLIENT_ID);
        }
        db.insert(DbHelper.AUTOS_TABLE, null, values);
        db.close();
    }


    public void deleteAuto(int autoId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DbHelper.AUTOS_TABLE, DbHelper.ID + "=?", new String[]{String.valueOf(autoId)});
        db.close();
    }

    // Метод для получения всех автомобилей клиента
    public List<Auto> findAutosByClientId(int clientId) {
        List<Auto> autos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.ID, DbHelper.AUTOS_MODEL, DbHelper.AUTOS_CLIENT_ID},
                String.format("%s=?", DbHelper.AUTOS_CLIENT_ID),
                new String[]{String.valueOf(clientId)},
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Auto auto = new Auto();
                auto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.ID)));
                auto.setModel(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_MODEL)));
                auto.setClientId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_CLIENT_ID)));
                autos.add(auto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return autos;
    }

    public List<Auto> findAllAutosWithClientId() {
        List<Auto> autos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.ID, DbHelper.AUTOS_MODEL, DbHelper.AUTOS_CLIENT_ID},
                String.format("%s IS NOT NULL", DbHelper.AUTOS_CLIENT_ID),
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Auto auto = new Auto();
                auto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.ID)));
                auto.setModel(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_MODEL)));
                auto.setClientId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_CLIENT_ID)));
                autos.add(auto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return autos;
    }

    public List<Auto> findAllAutosWithClientIdZero() {
        List<Auto> autos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.ID, DbHelper.AUTOS_MODEL, DbHelper.AUTOS_CLIENT_ID},
                String.format("%s IS NULL", DbHelper.AUTOS_CLIENT_ID), // Check for null values
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Auto auto = new Auto();
                auto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.ID)));
                auto.setModel(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_MODEL)));
                auto.setClientId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_CLIENT_ID)));
                autos.add(auto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return autos;
    }

    // Метод для получения всех автомобилей
    public List<Auto> findAllAutos() {
        List<Auto> autos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.ID, DbHelper.AUTOS_MODEL, DbHelper.AUTOS_CLIENT_ID},
                null, // Нет условия WHERE, т.е. выбрать все записи
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                Auto auto = new Auto();
                auto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.ID)));
                auto.setModel(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_MODEL)));
                auto.setClientId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_CLIENT_ID)));
                autos.add(auto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return autos;
    }



    // Inside AutoRepo
    public Auto findAutoById(int autoId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.AUTOS_MODEL, DbHelper.AUTOS_CLIENT_ID},
                DbHelper.ID + "=?",
                new String[]{String.valueOf(autoId)},
                null, null, null);

        Auto auto = null;
        if (cursor.moveToFirst()) {
            String model = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_MODEL));
            int clientId = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.AUTOS_CLIENT_ID));
            auto = new Auto(autoId, model, clientId);
        }
        cursor.close();
        db.close();
        return auto;
    }

    public void editAuto(Auto auto) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.AUTOS_MODEL, auto.getModel());
        values.put(DbHelper.AUTOS_CLIENT_ID, auto.getClientId());
        db.update(DbHelper.AUTOS_TABLE, values, DbHelper.ID + "=?", new String[]{String.valueOf(auto.getId())});
        db.close();
    }

    // Метод для проверки существования модели
    public boolean isAutoModelExists(String model) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.AUTOS_TABLE,
                new String[]{DbHelper.ID},
                String.format("%s=?", DbHelper.AUTOS_MODEL),
                new String[]{model},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
}
