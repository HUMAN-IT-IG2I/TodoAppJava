package fr.human.it.ig2i.todoappjava.presentation.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;
import java.util.Optional;

import fr.human.it.ig2i.todoappjava.MainApplication;
import fr.human.it.ig2i.todoappjava.data.InMemoryDataBase;
import fr.human.it.ig2i.todoappjava.data.model.Task;

public class TaskDetailsViewModel extends ViewModel {

    @NonNull
    private final MutableLiveData<Task> currentTask = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Boolean> isTaskFinished = new MutableLiveData<>(null);

    @NonNull
    private final InMemoryDataBase dataBase = MainApplication.dataBase;

    public void setCurrentTaskId(int id) {
        Optional<Task> researchResult = dataBase.getTaskById(id);
        if (researchResult.isPresent()) {
            currentTask.postValue(researchResult.get());
        } else {
            currentTask.postValue(null);
        }
    }

    @NonNull
    public LiveData<Task> getCurrentTask() {
        return currentTask;
    }

    public void markTaskAsDone() {
        boolean isTaskFinished = dataBase.finishTask(Objects.requireNonNull(currentTask.getValue()));
        this.isTaskFinished.postValue(isTaskFinished);
    }

    public LiveData<Boolean> isTaskFinished() {
        return isTaskFinished;
    }

}
