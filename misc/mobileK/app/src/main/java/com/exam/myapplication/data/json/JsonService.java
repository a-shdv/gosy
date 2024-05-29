package com.exam.myapplication.data.json;

import com.exam.myapplication.data.model.SickUserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonService {

    public static List<SickUserModel> getListFromJson(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<SickUserModel>>() {
        }.getType();
        return gson.fromJson(jsonString, listType);
    }

   public static String getJsonFromList(List<SickUserModel> list) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<SickUserModel>>() {
        }.getType();
        return gson.toJson(list, listType);
    }
}
