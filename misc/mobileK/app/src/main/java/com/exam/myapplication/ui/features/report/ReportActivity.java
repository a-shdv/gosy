package com.exam.myapplication.ui.features.report;

import android.os.Bundle;
import android.view.View;

import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.ui.features.list.adapter.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.exam.myapplication.databinding.ReportActivityBinding;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReportActivityBinding binding = ReportActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int inHospitalCount = extras.getInt(KEY_COUNT_IN_HOSPITAL, -1);
            int notInHospitalCount = extras.getInt(KEY_COUNT_NOT_IN_HOSPITAL, -1);
            String jsonResultString = extras.getString(KEY_JSON_REPORT);

            if (inHospitalCount != -1 && notInHospitalCount != -1) {
                binding.etUsersInHospital.setText(String.valueOf(inHospitalCount));
                binding.etUsersNotInHospital.setText(String.valueOf(notInHospitalCount));

                binding.etUsersInHospital.setVisibility(View.VISIBLE);
                binding.etUsersNotInHospital.setVisibility(View.VISIBLE);
                binding.tvJsonResult.setVisibility(View.GONE);
            }

            if (jsonResultString != null && !jsonResultString.equals("")) {
                binding.label1.setVisibility(View.GONE);
                binding.label2.setVisibility(View.GONE);
                binding.etUsersInHospital.setVisibility(View.GONE);
                binding.etUsersNotInHospital.setVisibility(View.GONE);

                binding.tvJsonResult.setText(jsonResultString);
                binding.tvJsonResult.setVisibility(View.VISIBLE);
            }
        }
    }

    public static final String KEY_COUNT_IN_HOSPITAL = "KEY_COUNT_IN_HOSPITAL";
    public static final String KEY_COUNT_NOT_IN_HOSPITAL = "KEY_COUNT_NOT_IN_HOSPITAL";
    public static final String KEY_JSON_REPORT = "KEY_JSON_REPORT";

}