package com.exam.myapplication.ui.features.add;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.exam.myapplication.App;
import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.data.repository.SickRepository;

public class AddViewModel extends ViewModel {
    private final SickRepository sickRepository;

    private AddViewModel(SickRepository sickRepository) {
        this.sickRepository = sickRepository;
    }

    public LiveData<SickUserModel> getNode(long id) {
        return sickRepository.getSickUser(id);
    }

    public void addSickUser(String fio) {
        sickRepository.addSickUser(new SickUserModel(fio, null, true));
    }

    static final ViewModelInitializer<AddViewModel> initializer = new ViewModelInitializer<>(
            AddViewModel.class,
            creationExtras -> {
                App app = (App) creationExtras.get(APPLICATION_KEY);
                assert app != null;
                return new AddViewModel(app.getNodeRepository());
            }
    );
}