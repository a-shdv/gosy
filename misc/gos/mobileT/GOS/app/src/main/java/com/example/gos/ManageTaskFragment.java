package com.example.gos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gos.databinding.FragmentManageTaskBinding;

public class ManageTaskFragment extends Fragment {

    private FragmentManageTaskBinding binding;

    private MainViewModel viewModel;

    private int task_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        task_id = requireArguments().getInt(KEY_TASK_ID, DEFAULT_VALUE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
    }

    private void initialize() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(MainViewModel.class);

        viewModel.requestCurrentTask(task_id);
        viewModel.currentTask.observe(getViewLifecycleOwner(), task -> {
            if (task != null) {
                binding.editTextTask.setText(task.getValue());
            }
        });

        binding.saveButton.setOnClickListener(v -> {
            if (task_id != DEFAULT_VALUE) {
                viewModel.update(task_id, binding.editTextTask.getEditableText().toString());
                closeFragment();
            } else {
                viewModel.insert(binding.editTextTask.getEditableText().toString());
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        getParentFragmentManager().popBackStack();
    }

    private final int DEFAULT_VALUE = 0;
    public static String KEY_TASK_ID = "KEY_TASK_ID";

    public static ManageTaskFragment newInstance(Integer taskId) {
        ManageTaskFragment fragment = new ManageTaskFragment();

        Bundle bundle = new Bundle();
        if (taskId != null) {
            bundle.putInt(KEY_TASK_ID, taskId);
        }

        fragment.setArguments(bundle);
        return fragment;
    }
}