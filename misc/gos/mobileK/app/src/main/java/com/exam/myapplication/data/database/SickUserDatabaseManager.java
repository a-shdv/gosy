package com.exam.myapplication.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.exam.myapplication.data.model.SickUserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SickUserDatabaseManager {
    private DatabaseHelper dbHelper;

    private final Context context;

    private SQLiteDatabase database;

    public SickUserDatabaseManager(Context context) {
        this.context = context;
    }

    public SickUserDatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        loadSickUsers();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public MutableLiveData<List<SickUserModel>> sickUsersLiveData = new MutableLiveData<>(new ArrayList<>());

    private void loadSickUsers() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        List<SickUserModel> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                list.add(
                        new SickUserModel(
                                cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3) == 1
                        )
                );
            } while (cursor.moveToNext());
        }
        sickUsersLiveData.postValue(list);
        cursor.close();
    }

    public LiveData<SickUserModel> getSickUser(long id) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE  id=" + id, null);
        SickUserModel sickUserModel = null;
        if (cursor.moveToFirst()) {
            sickUserModel = new SickUserModel(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) == 1
            );
        }
        MutableLiveData<SickUserModel> liveData = new MutableLiveData<>();
        if (sickUserModel != null) {
            liveData.postValue(sickUserModel);
        }
        return liveData;
    }

    public void insert(SickUserModel sickUserModel) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.FIO, sickUserModel.getFio());
        contentValue.put(DatabaseHelper.DOCTOR_FIO, sickUserModel.getDoctorFio());
        contentValue.put(DatabaseHelper.IS_IN_HOSPITAL, sickUserModel.getInHospital());

        long id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);

        List<SickUserModel> sickUserModels = sickUsersLiveData.getValue();
        if (sickUserModels != null) {
            sickUserModels.add(sickUserModel.copy(id, null, null, null));
            sickUsersLiveData.postValue(sickUserModels);
        }
    }

    public void update(SickUserModel sickUserModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FIO, sickUserModel.getFio());
        contentValues.put(DatabaseHelper.DOCTOR_FIO, sickUserModel.getDoctorFio());
        contentValues.put(DatabaseHelper.IS_IN_HOSPITAL, sickUserModel.getInHospital());
        long id = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + sickUserModel.getId(), null);

        List<SickUserModel> sickUserModels = new ArrayList<>(Objects.requireNonNull(sickUsersLiveData.getValue()));
        OptionalInt optIndex = IntStream.range(0, sickUserModels.size())
                .filter(i -> sickUserModels.get(i).getId().equals(sickUserModel.getId()))
                .findFirst();
        int index = -1;
        if (optIndex.isPresent()) {
            index = optIndex.getAsInt();
        }
        if (index != -1) {
            sickUserModels.set(index, sickUserModel.copy(id, null, null, null));
            sickUsersLiveData.postValue(sickUserModels);
        }
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.ID + "=" + id, null);

        List<SickUserModel> sickUserModels = new ArrayList<>(Objects.requireNonNull(sickUsersLiveData.getValue()));
        OptionalInt optIndex = IntStream.range(0, sickUserModels.size())
                .filter(i -> sickUserModels.get(i).getId().equals(id))
                .findFirst();
        int index = -1;
        if (optIndex.isPresent()) {
            index = optIndex.getAsInt();
        }
        if (index != -1) {
            sickUserModels.remove(index);
            sickUsersLiveData.postValue(sickUserModels);
        }
    }
}
