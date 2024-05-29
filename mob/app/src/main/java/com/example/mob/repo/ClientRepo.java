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

    public boolean addClient(Client client) {
        if (isClientNameExists(client.getName())) {
            return false; // Имя клиента уже существует
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", client.getName());
        db.insert(DbHelper.CLIENTS_TABLE, null, values);
        db.close();
        return true; // Клиент успешно добавлен
    }

    // Метод для проверки существования имени клиента
    public boolean isClientNameExists(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.CLIENTS_TABLE,
                new String[]{DbHelper.ID},
                "name = ?",
                new String[]{name},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Метод для проверки существования клиента по clientId
    public boolean isClientExists(int clientId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.CLIENTS_TABLE,
                new String[]{DbHelper.ID},
                "id = ?",
                new String[]{String.valueOf(clientId)},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
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

    // Метод для удаления клиента
    public void deleteClient(int clientId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DbHelper.CLIENTS_TABLE, "id = ?", new String[]{String.valueOf(clientId)});
        db.close();
    }
}
