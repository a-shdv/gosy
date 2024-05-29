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

    // Метод для вставки автомобиля
    public long addAuto(Auto auto) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("model", auto.getModel());
        values.put("clientId", auto.getClientId());
        long autoId = db.insert("Auto", null, values);
        db.close();
        return autoId;
    }

    // Метод для получения всех автомобилей клиента
    public List<Auto> getAutosByClientId(int clientId) {
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
}
