package fr.human.it.ig2i.todoappjava.presentation.create;

import androidx.lifecycle.ViewModel;

import fr.human.it.ig2i.todoappjava.MainApplication;

public class CreateTaskViewModel extends ViewModel {

    public boolean addTask(String content) {
        return MainApplication.dataBase.addTask(content);
    }
}
