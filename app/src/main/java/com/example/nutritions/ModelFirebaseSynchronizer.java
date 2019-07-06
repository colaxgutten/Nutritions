package com.example.nutritions;

import com.example.nutritions.models.Nutrition;
import com.google.firebase.database.DatabaseReference;

public class ModelFirebaseSynchronizer {
    public void saveDailyModel(Nutrition model, DatabaseReference reference){
        String currentDate = Utility.getCurrentDate();
        reference.child(currentDate).setValue(model);
    }
}
