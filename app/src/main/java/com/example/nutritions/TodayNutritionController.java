package com.example.nutritions;

public class TodayNutritionController {
    private TodayNutritionsModel todayNutritionsModel;

    public TodayNutritionController(){
        todayNutritionsModel = new TodayNutritionsModel();
    }

    public TodayNutritionsModel getTodayNutritionsModel() {
        return todayNutritionsModel;
    }
}
