package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob.R;
import com.example.mob.entity.Auto;
import com.example.mob.repo.AutoRepo;

public class EditAutoActivity extends AppCompatActivity {
    private EditText editTextAutoModel;
    private EditText editTextAutoClient;
    private Button buttonEditAutoOk;
    private AutoRepo autoRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_auto);

        editTextAutoModel = findViewById(R.id.edit_text_auto_model);
        editTextAutoClient = findViewById(R.id.edit_text_auto_client);
        buttonEditAutoOk = findViewById(R.id.button_edit_auto_ok);

        autoRepo = new AutoRepo(this);

        // Retrieve auto ID passed from AutoActivity
        int autoId = getIntent().getIntExtra("AUTO_ID", -1);
        if (autoId != -1) {
            // Fetch Auto details from database using autoId
            Auto auto = autoRepo.findAutoById(autoId);

            // Populate EditText fields with Auto details
            editTextAutoModel.setText(auto.getModel());
            editTextAutoClient.setText(String.valueOf(auto.getClientId()));
        }

        buttonEditAutoOk.setOnClickListener(event -> editAutoClickListener());
    }


    private void editAutoClickListener() {
        // Retrieve user input from EditText fields
        String model = editTextAutoModel.getText().toString();
        int clientId = Integer.parseInt(editTextAutoClient.getText().toString());

        // Update the Auto in the database
        int autoId = getIntent().getIntExtra("AUTO_ID", -1);
        autoRepo.editAuto(new Auto(autoId, model, clientId));

        // Show a toast message indicating successful update
        Toast.makeText(this, "Auto updated successfully", Toast.LENGTH_SHORT).show();

        // Finish the activity
        finish();
    }
}