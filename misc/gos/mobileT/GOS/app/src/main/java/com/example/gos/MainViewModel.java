package com.example.gos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final TaskRepository taskRepository;

    public final LiveData<List<Task>> taskList;

    public final LiveData<Task> currentTask;

    public final LiveData<List<Task>> filteredTaskList;

    public MainViewModel(Application application) {
        super(application);

        taskRepository = new TaskRepository(application);

        taskList = taskRepository.getTaskList();
        currentTask = taskRepository.currentTask;
        filteredTaskList = taskRepository.filteredTaskList;
    }

    public void insert(String value) {
        taskRepository.insert(value);
    }

    public void update(int id, String value) {
        taskRepository.update(id, value);
    }

    public void requestCurrentTask(int id) {
        taskRepository.requestCurrentTask(id);
    }

    public void filterByTextLength() {
        taskRepository.filterByTextLength();
    }

    public class MainViewModelFactory implements ViewModelProvider.Factory {

        private final Application application;

        public MainViewModelFactory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainViewModel(application);
        }
    }
}