package fr.human.it.ig2i.todoappjava.presentation.create;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import fr.human.it.ig2i.todoappjava.MainApplication;

public class CreateTaskViewModel extends ViewModel {

    public boolean addTask(@NonNull String content) {
        return MainApplication.dataBase.addTask(content);
    }

}
