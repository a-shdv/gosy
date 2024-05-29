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
import com.example.mob.entity.Auto;
import com.example.mob.repo.AutoRepo;

import java.util.Arrays;
import java.util.List;

public class AutoActivity extends AppCompatActivity {
    private AutoRepo autoRepo;
    private Button buttonAddAuto;
    private Button buttonReportAuto;
    private Button buttonDeleteAuto;
    private TableLayout tableLayoutFragment;
    private TableRow selectedRow;
    Integer deletedAuto = 0;

    private static final String purple = "#FF6200EE";

    @Override
    protected void onResume() {
        super.onResume();
        fillTable(Arrays.asList("ID", "Model", "Client"), autoRepo.findAllAutos());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);

        autoRepo = new AutoRepo(this);

        buttonAddAuto = findViewById(R.id.button_add_auto);
        buttonAddAuto.setOnClickListener(event -> addAutoClickListener());

        buttonReportAuto = findViewById(R.id.button_report_auto);
        buttonReportAuto.setOnClickListener(event -> reportAutoClickListener());

        buttonDeleteAuto = findViewById(R.id.button_delete_auto);
        buttonDeleteAuto.setOnClickListener(event -> deleteAutoClickListener());
    }

    private void addAutoClickListener() {
        Intent createAutoActivity = new Intent(AutoActivity.this, CreateAutoActivity.class);
        startActivity(createAutoActivity);
    }

    // Метод для обработки нажатий на кнопку "Edit"
    public void editAutoClickListener(int autoId) {
        // Перенос логики редактирования auto в этот метод
        Intent editAutoActivityIntent = new Intent(AutoActivity.this, EditAutoActivity.class);
        editAutoActivityIntent.putExtra("AUTO_ID", autoId);
        startActivity(editAutoActivityIntent);
    }
    private void reportAutoClickListener(){
        Intent reportAutoActivity = new Intent(AutoActivity.this, ReportActivity.class);
        reportAutoActivity.putExtra("DELETED_AUTO", deletedAuto);
        startActivity(reportAutoActivity);
    }

    private void deleteAutoClickListener() {
        if (selectedRow != null) {
            TextView idTextView = (TextView) selectedRow.getChildAt(0);
            int autoId = Integer.parseInt(idTextView.getText().toString());
            autoRepo.deleteAuto(autoId);
            // After deleting, you may want to refresh the table
            fillTable(Arrays.asList("ID", "Model", "Client"), autoRepo.findAllAutos());
            deletedAuto++;
        }
    }

    private void fillTable(List<String> columns, List<Auto> rows) {
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
        tableColumns.setBackgroundColor(Color.parseColor(purple));
        tableLayout.addView(tableColumns);
    }

    private void initRows(TableLayout tableLayout, List<Auto> rows) {
        for (Auto row : rows) {
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
            textViewReceivingDate.setText(String.valueOf(row.getModel()));
            textViewReceivingDate.setTextColor(Color.WHITE);
            textViewReceivingDate.setGravity(Gravity.CENTER);

            // 3 col
            TextView textViewSupplier = new TextView(this);
            textViewSupplier.setHeight(100);
            textViewSupplier.setTextSize(16);
            textViewSupplier.setText(String.valueOf(row.getClientId()));
            textViewSupplier.setTextColor(Color.WHITE);
            textViewSupplier.setGravity(Gravity.CENTER);

            // 4 col - кнопка "Edit"
            Button buttonEdit = new Button(this);
            buttonEdit.setText("Edit");
            buttonEdit.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            buttonEdit.setOnClickListener(v -> editAutoClickListener(row.getId()));

            // Visuals (row background color, selection)
            tableRow.setBackgroundColor(Color.parseColor(purple));
            tableRow.setOnClickListener(event -> selectRow(tableRow));

            // Add elements to row
            tableRow.addView(textViewDischargeDate);
            tableRow.addView(textViewReceivingDate);
            tableRow.addView(textViewSupplier);
            tableRow.addView(buttonEdit);
            tableLayout.addView(tableRow);
        }
    }

    // Inside AutoActivity
    private void selectRow(TableRow tableRow) {
        selectedRow = tableRow;
        for(int i = 0; i < tableLayoutFragment.getChildCount(); i++){
            View view = tableLayoutFragment.getChildAt(i);
            if (view instanceof TableRow){
                view.setBackgroundColor(Color.parseColor(purple));
            }
        }
        tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));

//        // Pass auto ID to EditAutoActivity
//        Intent editAutoActivityIntent = new Intent(AutoActivity.this, EditAutoActivity.class);
//        editAutoActivityIntent.putExtra("AUTO_ID", autoId);
//        startActivity(editAutoActivityIntent);
    }
}