package com.example.nutritions;

import com.google.firebase.database.DatabaseReference;

public class FoodRegister {

    public void saveFoodRegister(ModelFirebaseSynchronizer synchronizer, DatabaseReference reference){
        FoodNutrientConsentrationModel model = new FoodNutrientConsentrationModel("styrk kakao");
        model.setGrams(100);
        model.setFatPer100(0.2);
        model.setCarbohydratesPer100(5.6);
        model.setProteinsPer100(5.4);
        model.setNatriumPer100(100);
        model.setVitaminDPer100(0.0008);
        model.setVitaminB2Per100(0.15);
        model.setVitaminB12Per100(0.0008);
        model.setKaliumPer100(210);
        model.setCalciumPer100(170);
        model.setIodinePer100(0.02);
        synchronizer.saveFoodRegister(model,reference);
        model = new FoodNutrientConsentrationModel("imaginaryfood1");
        model.setGrams(100);
        model.setProteinsPer100(34);
        model.setFatPer100(4);
        model.setCarbohydratesPer100(12);
        synchronizer.saveFoodRegister(model,reference);

    }
}
