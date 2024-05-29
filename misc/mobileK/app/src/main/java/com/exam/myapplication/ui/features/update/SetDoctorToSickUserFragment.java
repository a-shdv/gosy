package com.exam.myapplication.ui.features.update;

import static androidx.navigation.ViewKt.findNavController;
import static com.exam.myapplication.ui.features.add.AddFragment.NO_ID_VALUE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exam.myapplication.databinding.SetSickDoctorToUserFragmentBinding;

public class SetDoctorToSickUserFragment extends Fragment {
    private SetSickDoctorToUserFragmentBinding binding;

    private SetDoctorToSickUserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = SetSickDoctorToUserFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetDoctorToSickUserFragmentArgs args = SetDoctorToSickUserFragmentArgs.fromBundle(getArguments());
        viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(SetDoctorToSickUserViewModel.initializer)
        ).get(SetDoctorToSickUserViewModel.class);

        if (args.getId() != NO_ID_VALUE) {
            viewModel.getSickUserModel(args.getId()).observe(getViewLifecycleOwner(), sickUser -> {
                if (sickUser != null) {
                    viewModel.sickUserModel = sickUser;
                    binding.etDoctorFio.setText(sickUser.getDoctorFio() == null ? "" : sickUser.getDoctorFio());
                    if (!sickUser.getInHospital()) {
                        binding.btnSetNotInHospital.setVisibility(View.GONE);
                    }
                }
            });
        }
        binding.btnSetDoctorFio.setOnClickListener(v -> {
            viewModel.setUserDoctorFio(binding.etDoctorFio.getText().toString());
            findNavController(binding.getRoot()).navigateUp();
        });

        binding.btnSetNotInHospital.setOnClickListener(v -> {
            viewModel.makeUserNotInHospital();
            findNavController(binding.getRoot()).navigateUp();
        });
    }
}
