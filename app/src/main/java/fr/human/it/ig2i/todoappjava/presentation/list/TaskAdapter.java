package fr.human.it.ig2i.todoappjava.presentation.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.human.it.ig2i.todoappjava.R;
import fr.human.it.ig2i.todoappjava.data.model.Task;
import fr.human.it.ig2i.todoappjava.databinding.TaskItemBinding;
import fr.human.it.ig2i.todoappjava.utils.DateUtils;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    @NonNull
    private final List<Task> tasks = new ArrayList<>();

    @NonNull
    private final OnTaskClickedListener onClickListener;

    public TaskAdapter(@NonNull OnTaskClickedListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void updateContent(List<Task> newTasks) {
        final TasksDiffUtil diffCallback = new TasksDiffUtil(tasks, newTasks);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        tasks.clear();
        tasks.addAll(newTasks);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {
        final Task task = tasks.get(position);
        holder.bind(task);
        holder.itemView.setOnClickListener(v -> onClickListener.onTaskClicked(task));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final TaskItemBinding binding;

        public TaskViewHolder(@NonNull TaskItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull Task task) {
            Context context = binding.getRoot().getContext();
            binding.taskId.setText(context.getString(R.string.task_id, task.getId()));
            binding.taskContent.setText(task.getContent());
            String creationDate = DateUtils.extractDateFromDateField(task.getCreationDate());
            String creationTime = DateUtils.extractTimeFromDateField(task.getCreationDate());
            binding.taskDate.setText(context.getString(R.string.task_date, creationDate, creationTime));
        }

    }

    private static class TasksDiffUtil extends DiffUtil.Callback {

        @NonNull
        private final List<Task> oldTasks;
        @NonNull
        private final List<Task> newTasks;

        public TasksDiffUtil(@NonNull List<Task> oldTasks, @NonNull List<Task> newTasks) {
            this.oldTasks = oldTasks;
            this.newTasks = newTasks;
        }

        @Override
        public int getOldListSize() {
            return oldTasks.size();
        }

        @Override
        public int getNewListSize() {
            return newTasks.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldTasks.get(oldItemPosition).getId() == newTasks.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldTasks.get(oldItemPosition).equals(newTasks.get(newItemPosition));
        }
    }

    public interface OnTaskClickedListener {
        void onTaskClicked(@NonNull Task task);
    }
}