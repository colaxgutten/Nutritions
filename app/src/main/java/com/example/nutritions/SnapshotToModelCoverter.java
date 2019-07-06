package com.example.nutritions;

import com.example.nutritions.models.Nutrition;
import com.google.firebase.database.DataSnapshot;

public class SnapshotToModelCoverter {
    public Nutrition convertDataSnapshot(DataSnapshot data, double grams){
        double gramsRatio = 1.0;
        if (grams!=0){
            gramsRatio=grams/100;
        }
        Nutrition todayNutritionsModel = new Nutrition();
        System.out.println(data.getKey()+": "+grams+" grams");
        todayNutritionsModel.setKcal(data.child("kcal").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setProtein(data.child("protein").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setCarbohydrate(data.child("carbohydrate").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setFat(data.child("fat").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminA(data.child("vitaminA").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB1(data.child("vitaminB1").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB2(data.child("vitaminB2").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB3(data.child("vitaminB3").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB6(data.child("vitaminB6").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB9(data.child("vitaminB9").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminB12(data.child("vitaminB12").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminC(data.child("vitaminC").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setVitaminD(data.child("vitaminD").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setIodine(data.child("iodine").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setIron(data.child("iron").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setMagnesium(data.child("magnesium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setCalcium(data.child("calcium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setNatrium(data.child("natrium").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setZinc(data.child("zinc").getValue(Double.class)*gramsRatio);
        todayNutritionsModel.setKalium(data.child("kalium").getValue(Double.class)*gramsRatio);
        return todayNutritionsModel;
    }
}
