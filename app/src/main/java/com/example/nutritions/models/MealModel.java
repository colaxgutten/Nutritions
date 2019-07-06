package com.example.nutritions.models;

import java.util.HashMap;

public class MealModel {

    private HashMap<String, Double> meal;

    public MealModel(){
        meal = new HashMap<>();
    }

    public void addProduct(String food, Double grams){
        meal.put(food,grams);
    }

    public HashMap<String, Double> getMeal() {
        return meal;
    }
}
