package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mob.R;
import com.example.mob.entity.Client;
import com.example.mob.repo.ClientRepo;

import java.util.Arrays;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    private TableLayout tableLayoutFragment;
    private TableRow selectedRow;
    private Button buttonAddClient;
    private Button buttonDeleteClient;
    private ClientRepo clientRepo;
    private int selectedClientId; // Переменная для хранения ID выбранного клиента

    @Override
    protected void onResume() {
        super.onResume();
        fillTable(Arrays.asList("ID", "Name"), clientRepo.findAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        clientRepo = new ClientRepo(this);

        buttonAddClient = findViewById(R.id.button_add_client);
        buttonAddClient.setOnClickListener(event -> addClientClickListener());

        buttonDeleteClient = findViewById(R.id.button_delete_client);
        buttonDeleteClient.setOnClickListener(event -> deleteClientClickListener());
    }

    private void addClientClickListener() {
        Intent createClientActivity = new Intent(ClientActivity.this, CreateClientActivity.class);
        startActivity(createClientActivity);
    }

    private void deleteClientClickListener() {
        if (selectedClientId != 0) {
            clientRepo.deleteClient(selectedClientId);
            // После удаления обновляем таблицу
            fillTable(Arrays.asList("ID", "Name"), clientRepo.findAll());
        }
    }

    private void fillTable(List<String> columns, List<Client> rows) {
        tableLayoutFragment = findViewById(R.id.table_layout_fragment);
        tableLayoutFragment.removeAllViews();
        initColumns(tableLayoutFragment, columns);
        initRows(tableLayoutFragment, rows);
    }

    private void initColumns(TableLayout tableLayout, List<String> columns) {
        TableRow tableColumns = new TableRow(this);
        for (String col : columns) {
            TextView textView = new TextView(this);
            textView.setTextSize(16);
            textView.setText(col);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth((int) (getWindowManager().getDefaultDisplay().getWidth() / 3.2));
            tableColumns.addView(textView);
        }
        tableColumns.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayout.addView(tableColumns);
    }

    private void initRows(TableLayout tableLayout, List<Client> rows) {
        for (Client row : rows) {
            TableRow tableRow = new TableRow(this);

            // 1 col
            TextView textViewDischargeDate = new TextView(this);
            textViewDischargeDate.setHeight(100);
            textViewDischargeDate.setTextSize(16);
            textViewDischargeDate.setText(String.valueOf(row.getId()));
            textViewDischargeDate.setTextColor(Color.WHITE);
            textViewDischargeDate.setGravity(Gravity.CENTER);

            // 2 col
            TextView textViewReceivingDate = new TextView(this);
            textViewReceivingDate.setHeight(100);
            textViewReceivingDate.setTextSize(16);
            textViewReceivingDate.setText(String.valueOf(row.getName()));
            textViewReceivingDate.setTextColor(Color.WHITE);
            textViewReceivingDate.setGravity(Gravity.CENTER);

            // Visuals (row background color, selection)
            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));
            tableRow.setOnClickListener(event -> selectRow(tableRow));

            // Add elements to row
            tableRow.addView(textViewDischargeDate);
            tableRow.addView(textViewReceivingDate);
            tableLayout.addView(tableRow);
        }
    }

    private void selectRow(TableRow tableRow) {
        selectedRow = tableRow;

        // Получаем ID клиента из первой ячейки строки таблицы
        TextView idTextView = (TextView) tableRow.getChildAt(0);
        selectedClientId = Integer.parseInt(idTextView.getText().toString());

        for(int i = 0; i < tableLayoutFragment.getChildCount(); i++){
            View view = tableLayoutFragment.getChildAt(i);
            if (view instanceof TableRow){
                view.setBackgroundColor(Color.parseColor("#FF6200EE"));
            }
        }
        tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));
    }
}