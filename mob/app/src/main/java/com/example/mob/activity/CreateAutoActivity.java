package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob.R;
import com.example.mob.entity.Auto;
import com.example.mob.repo.AutoRepo;
import com.example.mob.repo.ClientRepo;

public class CreateAutoActivity extends AppCompatActivity {
    private EditText editTextAutoModel;
    private EditText editTextAutoClient;
    private Button createAutoOk;
    private AutoRepo autoRepo;
    private ClientRepo clientRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_auto);

        autoRepo = new AutoRepo(this);
        clientRepo = new ClientRepo(this);

        editTextAutoModel = findViewById(R.id.edit_text_auto_model);
        editTextAutoClient = findViewById(R.id.edit_text_auto_client);

        createAutoOk = findViewById(R.id.button_create_auto_ok);
        createAutoOk.setOnClickListener(event -> createAutoOkClickListener());
    }

    private void createAutoOkClickListener() {
        String model = editTextAutoModel.getText().toString();
        int clientId;
        try {
            clientId = Integer.parseInt(editTextAutoClient.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid client ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!clientRepo.isClientExists(clientId)) {
            Toast.makeText(this, "Client ID does not exist", Toast.LENGTH_SHORT).show();
        } else {
            autoRepo.createAuto(new Auto(model, clientId));
            Toast.makeText(this, "Auto created successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}