package com.example.nutritions;

import java.util.ArrayList;

public class MealModel {

    public ArrayList<FoodNutrientConsentrationModel> products;

    public MealModel(){
        this.products = new ArrayList<FoodNutrientConsentrationModel>();
    }

    public void addProduct(FoodNutrientConsentrationModel product){
        products.add(product);
    }
}
