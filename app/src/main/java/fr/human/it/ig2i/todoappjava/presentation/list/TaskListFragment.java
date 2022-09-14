package fr.human.it.ig2i.todoappjava.presentation.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

import fr.human.it.ig2i.todoappjava.databinding.FragmentTaskListBinding;


public class TaskListFragment extends Fragment {

    @Nullable
    private FragmentTaskListBinding binding = null;
    private TaskListViewModel viewModel;
    private TaskAdapter adapter;

    private FragmentManager fragmentManager;

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTaskListBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);

        getBinding().taskList.setLayoutManager(new LinearLayoutManager(getBinding().taskList.getContext()));
        adapter = new TaskAdapter();
        getBinding().taskList.setAdapter(adapter);

        fragmentManager = getParentFragmentManager();

        return getBinding().getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshTasks();
        viewModel.getTasks().observe(getViewLifecycleOwner(), tasks -> adapter.updateContent(tasks));
    }

    @NonNull
    public FragmentTaskListBinding getBinding() {
        return Objects.requireNonNull(binding);
    }

}