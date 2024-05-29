package com.example.mob.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    private DbHelper dbHelper;

    public ClientRepo(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void addClient(Client client) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", client.getName());
        /*long clientId =*/ db.insert(DbHelper.CLIENTS_TABLE, null, values);
        db.close();
    }

    // Метод для получения всех клиентов
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {
                DbHelper.ID,
                DbHelper.CLIENTS_NAME
        };

        Cursor cursor = db.query(
                DbHelper.CLIENTS_TABLE, // Таблица
                columns,                // Столбцы
                null,                   // Условие WHERE
                null,                   // Параметры WHERE
                null,                   // Группировка
                null,                   // Имеющие
                null                    // Порядок сортировки
        );

        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.ID)));
                client.setName(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CLIENTS_NAME)));
                clients.add(client);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return clients;
    }

}
