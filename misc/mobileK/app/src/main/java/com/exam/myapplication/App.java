package com.exam.myapplication;

import android.app.Application;

import com.exam.myapplication.data.database.SickUserDatabaseManager;
import com.exam.myapplication.data.repository.SickRepository;
import com.exam.myapplication.data.repository.SickRepositoryImpl;

public class App extends Application {
    private SickRepository sickRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        SickUserDatabaseManager databaseManager = new SickUserDatabaseManager(this);
        sickRepository = new SickRepositoryImpl(databaseManager.open());
    }

    public SickRepository getNodeRepository() {
        return sickRepository;
    }
}
