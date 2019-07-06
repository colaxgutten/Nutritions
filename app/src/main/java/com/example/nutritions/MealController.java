package com.example.nutritions;

import com.example.nutritions.models.MealModel;

import java.util.HashMap;

public class MealController {
    private MealModel meal;
    private ProductFirebaseHandler firebaseHandler;

    public void NewMeal(){
        meal = new MealModel();
    }

    public MealController(){
        meal = new MealModel();
        firebaseHandler = new ProductFirebaseHandler();
    }

    public void addProductToMeal(String productName, double grams){
        meal.addProduct(productName,grams);
    }

    public HashMap<String, Double> getMeal(){
        return meal.getMeal();
    }

}
