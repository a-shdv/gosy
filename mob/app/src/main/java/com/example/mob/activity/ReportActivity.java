package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mob.R;
import com.example.mob.entity.Auto;
import com.example.mob.repo.AutoRepo;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    TextView textViewSold;
    TextView textViewDeleted;
    TextView textViewAdded;

    private AutoRepo autoRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        textViewSold = findViewById(R.id.text_view_sold);
        textViewDeleted = findViewById(R.id.text_view_deleted);
        textViewAdded = findViewById(R.id.text_view_added);

        autoRepo =new AutoRepo(this);

        // Get and display the counts
        List<Auto> soldCount = autoRepo.findAllAutosWithClientId();
        List<Auto> addedCount = autoRepo.findAllAutosWithClientIdZero();
        int deletedAuto = getIntent().getIntExtra("DELETED_AUTO", -1);

        // Set the text for textViewSold
        textViewSold.setText("Автомобилей на руках: " + String.valueOf(soldCount.size()));

        // Set the text for textViewDeleted
        if (deletedAuto != -1) {
            textViewDeleted.setText("Автомобилей списано: " + String.valueOf(deletedAuto));
        }

        // Set the text for textViewAdded
        textViewAdded.setText("Автомобилей в салоне: " + String.valueOf(addedCount.size()));
    }
}