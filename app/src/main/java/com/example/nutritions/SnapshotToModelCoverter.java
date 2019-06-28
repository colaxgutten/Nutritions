package com.example.nutritions;

import com.google.firebase.database.DataSnapshot;

public class SnapshotToModelCoverter {
    public TodayNutritionsModel convertDataSnapshot(DataSnapshot data, double grams){
        double gramsRatio = 1.0;
        if (grams!=0){
            gramsRatio=grams/100;
        }
        TodayNutritionsModel todayNutritionsModel = new TodayNutritionsModel();
        System.out.println(data.getKey()+": "+grams+" grams");
        todayNutritionsModel.kcal.set(data.child("kcal").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.protein.set(data.child("protein").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.carbohydrate.set(data.child("carbohydrate").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.fat.set(data.child("fat").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminA.set(data.child("vitaminA").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB1.set(data.child("vitaminB1").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB2.set(data.child("vitaminB2").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB3.set(data.child("vitaminB3").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB6.set(data.child("vitaminB6").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB9.set(data.child("vitaminB9").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminB12.set(data.child("vitaminB12").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminC.set(data.child("vitaminC").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.vitaminD.set(data.child("vitaminD").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.iodine.set(data.child("iodine").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.iron.set(data.child("iron").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.magnesium.set(data.child("magnesium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.calcium.set(data.child("calcium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.natrium.set(data.child("natrium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.zinc.set(data.child("zinc").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.kalium.set(data.child("kalium").getValue(Double.class)*gramsRatio);
        return todayNutritionsModel;
    }
}
