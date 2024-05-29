package com.exam.myapplication.ui.features.list;

import static androidx.navigation.ViewKt.findNavController;
import static com.exam.myapplication.ui.features.add.AddFragment.NO_ID_VALUE;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.myapplication.data.json.JsonService;
import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.databinding.ListFragmentBinding;
import com.exam.myapplication.ui.features.list.adapter.ListAdapter;
import com.exam.myapplication.ui.features.report.ReportActivity;

import java.util.List;

public class ListFragment extends Fragment {
    private ListFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = ListFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(ListViewModel.initializer)
        ).get(ListViewModel.class);

        ListAdapter listAdapter = new ListAdapter();
        listAdapter.setListener(sickUserModel ->
                findNavController(binding.getRoot()).navigate(ListFragmentDirections.actionListFragmentToSetDoctorToSickUserFragment(sickUserModel.getId()))
        );
        binding.rvList.setAdapter(listAdapter);

        viewModel.getNotesLiveData().observe(getViewLifecycleOwner(), listAdapter::submitItems);

        binding.fabAdd.setOnClickListener(v ->
                findNavController(binding.getRoot()).navigate(ListFragmentDirections.actionListFragmentToAddEditFragment(NO_ID_VALUE)));

        binding.btnReport.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReportActivity.class);
            Bundle args = new Bundle();

            long userInHospital = listAdapter.getList().stream().filter(SickUserModel::getInHospital).count();
            long userNotInHospital = listAdapter.getList().stream().filter(s -> !s.getInHospital()).count();

            args.putInt(ReportActivity.KEY_COUNT_IN_HOSPITAL, (int) userInHospital);
            args.putInt(ReportActivity.KEY_COUNT_NOT_IN_HOSPITAL, (int) userNotInHospital);
            intent.putExtras(args);
            startActivity(intent);
        });

        binding.btnFromJson.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReportActivity.class);
            Bundle args = new Bundle();
            List<SickUserModel> listFromJson = JsonService.getListFromJson(viewModel.jsonString);
            args.putString(ReportActivity.KEY_JSON_REPORT, listFromJson.toString());
            intent.putExtras(args);
            startActivity(intent);
        });

        binding.btnToJson.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReportActivity.class);
            Bundle args = new Bundle();
            viewModel.jsonString = JsonService.getJsonFromList(listAdapter.getList());
            args.putString(ReportActivity.KEY_JSON_REPORT, viewModel.jsonString);
            intent.putExtras(args);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}