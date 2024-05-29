package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mob.R;

import java.util.Arrays;
import java.util.List;

public class AutoActivity extends AppCompatActivity {
    private TableLayout tableLayoutFragment;
    private TableRow selectedRow;

    @Override
    protected void onResume() {
        super.onResume();
        fillTable(Arrays.asList("Дата назначения", "Дата получения", "Имя поставщика"), Arrays.asList("asf", "gfa", "sad"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
    }

    private void fillTable(List<String> columns, List<String> rows) {
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

    private void initRows(TableLayout tableLayout, List<String> rows) {
        for (String row : rows) {
            TableRow tableRow = new TableRow(this);

            // 1 col
            TextView textViewDischargeDate = new TextView(this);
            textViewDischargeDate.setHeight(100);
            textViewDischargeDate.setTextSize(16);
            textViewDischargeDate.setText(row);
            textViewDischargeDate.setTextColor(Color.WHITE);
            textViewDischargeDate.setGravity(Gravity.CENTER);

            // 2 col
            TextView textViewReceivingDate = new TextView(this);
            textViewReceivingDate.setHeight(100);
            textViewReceivingDate.setTextSize(16);
            textViewReceivingDate.setText(row);
            textViewReceivingDate.setTextColor(Color.WHITE);
            textViewReceivingDate.setGravity(Gravity.CENTER);

            // 3 col
            TextView textViewSupplier = new TextView(this);
            textViewSupplier.setHeight(100);
            textViewSupplier.setTextSize(16);
            textViewSupplier.setText(row);
            textViewSupplier.setTextColor(Color.WHITE);
            textViewSupplier.setGravity(Gravity.CENTER);

            // 4 col
//            TextView textViewId = new TextView(this);
//            textViewId.setVisibility(View.INVISIBLE);
//            textViewId.setText(String.valueOf(receipt.getId()));

            // Visuals (row background color, selection)
            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));
            tableRow.setOnClickListener(event -> selectRow(tableRow));

            // Add elements to row
            tableRow.addView(textViewDischargeDate);
            tableRow.addView(textViewReceivingDate);
            tableRow.addView(textViewSupplier);
//            tableRow.addView(textViewId);
            tableLayout.addView(tableRow);
        }
    }

    private void selectRow(TableRow tableRow) {
        selectedRow = tableRow;
        for(int i = 0; i < tableLayoutFragment.getChildCount(); i++){
            View view = tableLayoutFragment.getChildAt(i);
            if (view instanceof TableRow){
                view.setBackgroundColor(Color.parseColor("#FF6200EE"));
            }
        }
        tableRow.setBackgroundColor(Color.parseColor("#FFBB86FC"));

    }
}