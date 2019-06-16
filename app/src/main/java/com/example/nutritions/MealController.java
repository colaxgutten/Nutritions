package com.example.nutritions;

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
        FoodNutrientConsentrationModel product = firebaseHandler.getProductFromFirebase(productName);
        if (product== null)
            return;
        product.setGrams(grams);
        meal.addProduct(product);
    }

    public void addProductToMeal(String productName){
        addProductToMeal(productName, 100);
    }

    public MealModel getMeal(){
        return this.meal;
    }

}
