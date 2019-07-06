package com.example.nutritions;

import com.google.firebase.database.DatabaseReference;

public class ModelFirebaseSynchronizer {
    public void saveDailyModel(TodayNutritionsModel model, DatabaseReference reference){
        String currentDate = Utility.getCurrentDate();
        reference.child(currentDate).setValue(model.getHashMap());
    }
}
