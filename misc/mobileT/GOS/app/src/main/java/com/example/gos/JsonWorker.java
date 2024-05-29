package com.example.gos;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;

public class JsonWorker {

    private static final Gson gson = new Gson();

    public static void readJson(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            Task task = gson.fromJson(fileReader, Task.class);
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeJson(String filename, Task task) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            String json = gson.toJson(task);
            fileWriter.write(json);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toJson(Task task) {
        String json = gson.toJson(task);
    }

    public static void fromJson(String json) {
        Task task = gson.fromJson(json, Task.class);
    }
}