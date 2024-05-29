package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.mob.R;
import com.example.mob.entity.Client;
import com.example.mob.repo.ClientRepo;

public class CreateClientActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonCreateClientOk;
    private ClientRepo clientRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        clientRepo = new ClientRepo(this);

        editText = findViewById(R.id.edit_text_client_name);

        buttonCreateClientOk = findViewById(R.id.button_create_client_ok);
        buttonCreateClientOk.setOnClickListener(event -> createClientOkClickListener());
    }

    private void createClientOkClickListener() {
        String name = editText.getText().toString();
        clientRepo.addClient(new Client(name));
        finish();
    }
}