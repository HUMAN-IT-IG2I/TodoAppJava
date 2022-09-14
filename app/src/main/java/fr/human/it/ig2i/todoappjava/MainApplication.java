package fr.human.it.ig2i.todoappjava;

import android.app.Application;

import androidx.annotation.NonNull;

import fr.human.it.ig2i.todoappjava.data.InMemoryDataBase;

public class MainApplication extends Application {

    @NonNull
    public static final InMemoryDataBase dataBase = new InMemoryDataBase();

}
