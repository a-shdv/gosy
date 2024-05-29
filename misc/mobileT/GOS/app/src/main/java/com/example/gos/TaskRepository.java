package com.example.gos;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    private final MutableLiveData<Task> _currentTask = new MutableLiveData<>();
    public LiveData<Task> currentTask = _currentTask;

    private final MutableLiveData<List<Task>> _filteredTaskList = new MutableLiveData<>();
    public LiveData<List<Task>> filteredTaskList = _filteredTaskList;

    public TaskRepository(Application application) {
        taskDao = AppDatabase.getInstance(application).taskDao();
    }

    public void insert(String value) {
        AppDatabase.databaseExecutor.execute(() -> {
            taskDao.insert(new Task(DEFAULT_TASK_ID, value));
        });
    }

    public LiveData<List<Task>> getTaskList() {
        return taskDao.loadTaskList();
    }

    public void update(int id, String value) {
        AppDatabase.databaseExecutor.execute(() -> {
            taskDao.update(id, value);
        });
    }

    public void requestCurrentTask(int id) {
        AppDatabase.databaseExecutor.execute(() -> {
            _currentTask.postValue(taskDao.loadTask(id));
        });
    }

    public void filterByTextLength() {
        AppDatabase.databaseExecutor.execute(() -> {
            _filteredTaskList.postValue(taskDao.filterByTextLength());
        });
    }

    private final int DEFAULT_TASK_ID = 0;
}
