package com.example.nutritions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class ModelFirebaseSynchronizer {
    CurrentDate date;
    DatabaseReference databaseReference;
    public ModelFirebaseSynchronizer(){
        date = new CurrentDate();
    }

    public String ModelToJson(TodayNutritionsModel model){
        if (model==null)
            return null;
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(model);
        return json;
    }

    public TodayNutritionsModel JsonToModel(String json){
        Gson gson = new GsonBuilder().create();
        TodayNutritionsModel model = gson.fromJson(json,TodayNutritionsModel.class);
        return model;
    }


    public void saveFoodRegister(FoodNutrientConsentrationModel model, DatabaseReference reference){
        String dateString = date.getCurrentDate();
        reference.child("food").child(model.getFoodName()).setValue(model.getHashMap());
    }



    public void saveDailyModel(TodayNutritionsModel model, DatabaseReference reference){
        String dateString = date.getCurrentDate();
        reference.child(dateString).setValue(model.getHashMap());

    }

}
