package fr.human.it.ig2i.todoappjava.presentation.create;

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
import fr.human.it.ig2i.todoappjava.databinding.FragmentCreateTaskBinding;
import fr.human.it.ig2i.todoappjava.utils.ToastUtils;

public class CreateTaskFragment extends Fragment {

    @Nullable
    private FragmentCreateTaskBinding binding = null;
    private CreateTaskViewModel viewModel;

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateTaskBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(CreateTaskViewModel.class);
        requireActivity().setTitle(getString(R.string.task_creation_fragment_title));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getBinding().cancelButton.setOnClickListener(v -> requireActivity().onBackPressed());

        getBinding().createButton.setOnClickListener(v -> createTask());
    }

    private void createTask() {
        String taskContent = Objects.requireNonNull(getBinding().taskContentEditText.getText()).toString();
        if (taskContent.trim().isEmpty()) {
            ToastUtils.displayToast(getContext(), R.string.task_content_empty_error_message);
            return;
        }
        if (viewModel.addTask(taskContent)) {
            requireActivity().onBackPressed();
        } else {
            ToastUtils.displayToast(getContext(), R.string.task_creation_error_message);
        }
    }

    @NonNull
    public FragmentCreateTaskBinding getBinding() {
        return Objects.requireNonNull(binding);
    }


}