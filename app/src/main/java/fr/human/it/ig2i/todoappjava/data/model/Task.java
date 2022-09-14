package fr.human.it.ig2i.todoappjava.data.model;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public class Task {
    private int id;
    @NonNull
    private String content;
    @NonNull
    private Date creationDate;
    @NonNull
    private Boolean isActive;

    public Task(int id, @NonNull String content, @NonNull Date creationDate, @NonNull Boolean isActive) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public Date getCreationDate() {
        return creationDate;
    }

    @NonNull
    public Boolean isActive() {
        return isActive;
    }

    public void finishTask() {
        isActive = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return getId() == task.getId() && getContent().equals(task.getContent()) && getCreationDate().equals(task.getCreationDate()) && isActive.equals(task.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getCreationDate(), isActive);
    }
}
