package com.example.nutritions;

 public class TodayNutritionModelAdder {

     public static TodayNutritionsModel addModels(TodayNutritionsModel model1, TodayNutritionsModel model2){
         TodayNutritionsModel model = new TodayNutritionsModel();
         model.kcal.set(model1.kcal.get()+model2.kcal.get());
         model.fat.set(model1.fat.get()+model2.fat.get());
         model.carbohydrate.set(model1.carbohydrate.get()+model2.carbohydrate.get());
         model.protein.set(model1.protein.get()+model2.protein.get());
         model.vitaminA.set(model1.vitaminA.get()+model2.vitaminA.get());
         model.vitaminB1.set(model1.vitaminB1.get()+model2.vitaminB1.get());
         model.vitaminB2.set(model1.vitaminB2.get()+model2.vitaminB2.get());
         model.vitaminB3.set(model1.vitaminB3.get()+model2.vitaminB3.get());
         model.vitaminB6.set(model1.vitaminB6.get()+model2.vitaminB6.get());
         model.vitaminB9.set(model1.vitaminB9.get()+model2.vitaminB9.get());
         model.vitaminB12.set(model1.vitaminB12.get()+model2.vitaminB12.get());
         model.vitaminC.set(model1.vitaminC.get()+model2.vitaminC.get());
         model.vitaminD.set(model1.vitaminD.get()+model2.vitaminD.get());
         model.calcium.set(model1.calcium.get()+model2.calcium.get());
         model.kalium.set(model1.kalium.get()+model2.kalium.get());
         model.iodine.set(model1.iodine.get()+model2.iodine.get());
         model.iron.set(model1.iron.get()+model2.iron.get());
         model.natrium.set(model1.natrium.get()+model2.natrium.get());
         model.zinc.set(model1.zinc.get()+model2.zinc.get());
         model.magnesium.set(model1.magnesium.get()+model2.magnesium.get());
         return model;
     }
}
