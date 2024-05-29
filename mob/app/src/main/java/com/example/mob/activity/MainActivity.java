package com.example.mob.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mob.R;
import com.example.mob.fragment.SecondFragment;
import com.example.mob.fragment.OneFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment lastFragment = new SecondFragment(); // initial value
    private Button buttonMove;
    private Button buttonShow;
    private Button buttonTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMove = findViewById(R.id.button_move);
        buttonMove.setOnClickListener(event -> moveClickListener());

        buttonShow = findViewById(R.id.button_show);
        buttonShow.setOnClickListener(event -> showClickListener());

        buttonTask = findViewById(R.id.button_task);
        buttonTask.setOnClickListener(event -> taskClickListener());
    }

    // Переход между Activity
    public void moveClickListener() {
        Intent testActivity = new Intent(MainActivity.this, TestActivity.class);
        startActivity(testActivity);
    }

    // Показать Fragment
    public void showClickListener() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (lastFragment instanceof OneFragment) {
            Fragment second = new SecondFragment();
            ft.replace(R.id.fragment_test, second);
            lastFragment = second;
        } else if (lastFragment instanceof SecondFragment) {
            Fragment one = new OneFragment();
            ft.replace(R.id.fragment_test, one);
            lastFragment = one;
        }

        ft.commit();
    }

    public void taskClickListener() {
        Intent taskActivity = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(taskActivity);
    }
}