package fr.human.it.ig2i.todoappjava.presentation.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import fr.human.it.ig2i.todoappjava.R;
import fr.human.it.ig2i.todoappjava.data.model.Task;
import fr.human.it.ig2i.todoappjava.databinding.FragmentTaskListBinding;
import fr.human.it.ig2i.todoappjava.presentation.create.CreateTaskFragment;
import fr.human.it.ig2i.todoappjava.presentation.details.TaskDetailsFragment;


public class TaskListFragment extends Fragment {

    @Nullable
    private FragmentTaskListBinding binding = null;
    private TaskListViewModel viewModel;
    private TaskAdapter adapter;

    private FragmentManager fragmentManager;

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskListBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);

        getBinding().taskList.setLayoutManager(new LinearLayoutManager(getBinding().taskList.getContext()));
        adapter = new TaskAdapter(this::navigateToDetails);
        getBinding().taskList.setAdapter(adapter);

        fragmentManager = getParentFragmentManager();

        requireActivity().setTitle(R.string.task_list_fragment_title);

        return getBinding().getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshTasks();
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> adapter.updateContent(tasks));
        getBinding().createTaskFab.setOnClickListener(v -> navigateToTaskCreation());
    }

    @NonNull
    public FragmentTaskListBinding getBinding() {
        return Objects.requireNonNull(binding);
    }

    private void navigateToTaskCreation() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, CreateTaskFragment.class, null, getString(R.string.task_creation_fragment_title)).setReorderingAllowed(true).addToBackStack(getString(R.string.task_creation_fragment_title)).commit();
    }

    private void navigateToDetails(Task task) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.task_id_arg_name_for_details), task.getId());
        transaction.replace(R.id.fragment_container, TaskDetailsFragment.class, bundle, getString(R.string.task_details_fragment_title)).setReorderingAllowed(true).addToBackStack(getString(R.string.task_details_fragment_title)).commit();
    }

}