package com.exam.myapplication.ui.features.update;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.exam.myapplication.App;
import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.data.repository.SickRepository;

public class SetDoctorToSickUserViewModel extends ViewModel {
    private final SickRepository sickRepository;
    public SickUserModel sickUserModel;

    private SetDoctorToSickUserViewModel(SickRepository sickRepository) {
        this.sickRepository = sickRepository;
    }

    public LiveData<SickUserModel> getSickUserModel(long id) {
        return sickRepository.getSickUser(id);
    }

    public void makeUserNotInHospital() {
        if (sickUserModel != null) {
            sickRepository.updateSickUser(
                    sickUserModel.copy(
                            null, null, null, false
                    )
            );
        }
    }

    public void setUserDoctorFio(String doctorFio) {
        if (sickUserModel != null) {
            sickRepository.updateSickUser(
                    sickUserModel.copy(
                            null, null, doctorFio, null
                    )
            );
        }
    }

    static final ViewModelInitializer<SetDoctorToSickUserViewModel> initializer = new ViewModelInitializer<>(
            SetDoctorToSickUserViewModel.class,
            creationExtras -> {
                App app = (App) creationExtras.get(APPLICATION_KEY);
                assert app != null;
                return new SetDoctorToSickUserViewModel(app.getNodeRepository());
            }
    );
}
