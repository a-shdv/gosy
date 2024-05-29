package com.exam.myapplication.ui.features.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.exam.myapplication.App;
import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.data.repository.SickRepository;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import java.util.List;

public class ListViewModel extends ViewModel {
    private final SickRepository sickRepository;
    public String jsonString = "";
    private ListViewModel(SickRepository sickRepository) {
        this.sickRepository = sickRepository;
    }

    LiveData<List<SickUserModel>> getNotesLiveData() {
        return sickRepository.getSickUsersLiveData();
    }

    static final ViewModelInitializer<ListViewModel> initializer = new ViewModelInitializer<>(
            ListViewModel.class,
            creationExtras -> {
                App app = (App) creationExtras.get(APPLICATION_KEY);
                assert app != null;
                return new ListViewModel(app.getNodeRepository());
            }
    );
}