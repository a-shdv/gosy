package com.exam.myapplication.data.repository;

import androidx.lifecycle.LiveData;

import com.exam.myapplication.data.model.SickUserModel;

import java.util.List;

public interface SickRepository {
    LiveData<List<SickUserModel>> getSickUsersLiveData();

    LiveData<SickUserModel> getSickUser(long id);

    void addSickUser(SickUserModel sickUserModel);

    void updateSickUser(SickUserModel sickUserModel);

    void deleteSickUser(long id);
}
