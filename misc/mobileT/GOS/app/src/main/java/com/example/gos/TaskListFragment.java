package com.example.gos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gos.databinding.FragmentTaskListBinding;

import java.util.List;

public class TaskListFragment extends Fragment {

    private FragmentTaskListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTaskListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
    }

    private void initialize() {
        MainViewModel viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(MainViewModel.class);

        TaskAdapter taskAdapter = new TaskAdapter(listener);

        binding.taskRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.taskRecyclerView.setAdapter(taskAdapter);

        viewModel.taskList.observe(getViewLifecycleOwner(), taskAdapter::submitList);

        binding.addTaskButton.setOnClickListener(v -> {
            openManageTaskFragment(null);
        });

        binding.reportButton.setOnClickListener(v -> {
            viewModel.filterByTextLength();
            viewModel.filteredTaskList.observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(List<Task> tasks) {
                    Intent intent = new Intent(requireActivity().getApplicationContext(), ReportActivity.class);
                    intent.putExtra(ReportActivity.KEY, getString(R.string.quantity_report_template, tasks.size()));

                    viewModel.filteredTaskList.removeObserver(this);
                    requireActivity().startActivity(intent);
                }
            });
        });
    }

    public TaskAdapter.TaskViewHolder.TaskLayoutListener listener = task -> {
        openManageTaskFragment(task.getId());
    };

    private void openManageTaskFragment(Integer taskId) {
        getParentFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ManageTaskFragment.newInstance(taskId))
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commit();
    }

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }
}
