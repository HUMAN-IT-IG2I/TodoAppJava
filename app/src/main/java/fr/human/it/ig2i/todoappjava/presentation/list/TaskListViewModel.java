package fr.human.it.ig2i.todoappjava.presentation.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.human.it.ig2i.todoappjava.MainApplication;
import fr.human.it.ig2i.todoappjava.data.model.Task;

public class TaskListViewModel extends ViewModel {

    private final MutableLiveData<List<Task>> tasks = new MutableLiveData<>(MainApplication.dataBase.getTasks());

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void refreshTasks() {
        tasks.postValue(MainApplication.dataBase.getTasks());
    }
}
