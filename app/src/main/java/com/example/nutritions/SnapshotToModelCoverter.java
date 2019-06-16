package com.example.nutritions;

import com.google.firebase.database.DataSnapshot;

public class SnapshotToModelCoverter {
    public TodayNutritionsModel convertDataSnapshot(DataSnapshot data){
        TodayNutritionsModel todayNutritionsModel = new TodayNutritionsModel();
        todayNutritionsModel.protein.set((double)data.child("protein").getValue());
        todayNutritionsModel.carbohydrate.set((double)data.child("carbohydrate").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        todayNutritionsModel.fat.set((double)data.child("fat").getValue());
        return todayNutritionsModel;
    }
}
