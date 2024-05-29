package com.exam.myapplication.ui.features.add;

import static androidx.navigation.ViewKt.findNavController;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.myapplication.databinding.AddFragmentBinding;

public class AddFragment extends Fragment {

    private AddViewModel viewModel;
    private AddFragmentBinding binding;
    private AddFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(AddViewModel.initializer)
        ).get(AddViewModel.class);

        args = AddFragmentArgs.fromBundle(getArguments());
        if (args.getId() != NO_ID_VALUE) {
            viewModel.getNode(args.getId()).observe(getViewLifecycleOwner(), sickUser -> {
                if (sickUser != null) {
                    binding.etFio.setText(sickUser.getFio());
                }
            });
        }

        binding.btnSubmit.setOnClickListener(v -> {
            viewModel.addSickUser(
                    binding.etFio.getText().toString()
            );
            findNavController(binding.getRoot()).navigateUp();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public static final long NO_ID_VALUE = -1L;
}