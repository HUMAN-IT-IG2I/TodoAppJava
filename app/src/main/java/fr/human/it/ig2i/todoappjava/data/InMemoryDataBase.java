package fr.human.it.ig2i.todoappjava.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import fr.human.it.ig2i.todoappjava.data.model.Task;

public class InMemoryDataBase {

    @NonNull
    private final Calendar calendar = Calendar.getInstance();

    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task(
                    1,
                    "Benchmark Android native development solutions",
                    Calendar.getInstance().getTime(),
                    false
            ),
            new Task(
                    2,
                    "Realize a TodoApp in Java",
                    Calendar.getInstance().getTime(),
                    true
            ),
            new Task(
                    3,
                    "Realize a TodoApp in Kotlin",
                    Calendar.getInstance().getTime(),
                    true
            ),
            new Task(
                    4,
                    "Find out which solution is the best",
                    Calendar.getInstance().getTime(),
                    true
            )
    ));

    public List<Task> getTasks() {
        return tasks.stream().filter(Task::isActive).collect(Collectors.toList());
    }

    private int getLastUsedId() {
        List<Integer> usedIds = tasks.stream().map(Task::getId).sorted().collect(Collectors.toList());
        if (usedIds.isEmpty()) {
            return 0;
        }
        return usedIds.get(usedIds.size() - 1);
    }

    public boolean addTask(String content) {
        return tasks.add(
                new Task(
                        getLastUsedId(),
                        content,
                        calendar.getTime(),
                        true
                )
        );
    }

}
