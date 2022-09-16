package fr.human.it.ig2i.todoappjava.presentation.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import fr.human.it.ig2i.todoappjava.R;
import fr.human.it.ig2i.todoappjava.data.model.Task;
import fr.human.it.ig2i.todoappjava.databinding.FragmentTaskDetailsBinding;
import fr.human.it.ig2i.todoappjava.utils.DateUtils;
import fr.human.it.ig2i.todoappjava.utils.ToastUtils;

public class TaskDetailsFragment extends Fragment {

    @Nullable
    private FragmentTaskDetailsBinding binding = null;

    private TaskDetailsViewModel viewModel;

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTaskDetailsBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(this).get(TaskDetailsViewModel.class);

        loadSelectedTask();

        return getBinding().getRoot();
    }

    private void loadSelectedTask() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            int taskId = bundle.getInt(getString(R.string.task_id_arg_name_for_details));
            viewModel.setCurrentTaskId(taskId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getCurrentTask().observe(getViewLifecycleOwner(), this::displayTask);
        viewModel.isTaskFinished().observe(getViewLifecycleOwner(), this::onTaskFinished);
        getBinding().backButton.setOnClickListener(v -> requireActivity().onBackPressed());
        getBinding().finishButton.setOnClickListener(v -> viewModel.markTaskAsDone());
    }

    private void onTaskFinished(Boolean isTaskFinished) {
        if(isTaskFinished == null) return;
        if(isTaskFinished) {
            ToastUtils.displayToast(getContext(), R.string.task_successfully_finish);
        } else {
            ToastUtils.displayToast(getContext(), R.string.task_failed_finish);
        }
        requireActivity().onBackPressed();
    }

    private void displayTask(Task task) {
        if(task != null) {
            String creationDate = DateUtils.extractDateFromDateField(task.getCreationDate());
            String creationTime = DateUtils.extractTimeFromDateField(task.getCreationDate());
            getBinding().taskId.setText(getString(R.string.task_id, task.getId()));
            getBinding().taskContent.setText(task.getContent());
            getBinding().taskCreationDate.setText(getString(R.string.task_date, creationDate, creationTime));
        }
    }

    @NonNull
    public FragmentTaskDetailsBinding getBinding() {
        return Objects.requireNonNull(binding);
    }
}
