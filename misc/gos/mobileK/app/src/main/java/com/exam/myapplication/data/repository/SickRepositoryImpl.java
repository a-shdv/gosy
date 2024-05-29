package com.exam.myapplication.data.repository;

import androidx.lifecycle.LiveData;

import com.exam.myapplication.data.database.SickUserDatabaseManager;
import com.exam.myapplication.data.model.SickUserModel;

import java.util.List;

public class SickRepositoryImpl implements SickRepository {
    private final SickUserDatabaseManager databaseManager;

    public SickRepositoryImpl(SickUserDatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }
    @Override
    public LiveData<List<SickUserModel>> getSickUsersLiveData() {
        return databaseManager.sickUsersLiveData;
    }

    public LiveData<SickUserModel> getSickUser(long id) {
        return databaseManager.getSickUser(id);
    }

    @Override
    public void addSickUser(SickUserModel sickUserModel) {
        databaseManager.insert(sickUserModel);
    }

    @Override
    public void updateSickUser(SickUserModel sickUserModel) {
        databaseManager.update(sickUserModel);
    }

    @Override
    public void deleteSickUser(long id) {
        databaseManager.delete(id);
    }
}
