package com.example.nutritions;

 public class TodayNutritionModelAdder {

     public static TodayNutritionsModel addModels(TodayNutritionsModel model1, TodayNutritionsModel model2){
         TodayNutritionsModel model = new TodayNutritionsModel();
         model.setKcal(model1.getKcal()+model2.getKcal());
         model.setFat(model1.getFat()+model2.getFat());
         model.setCarbohydrate(model1.getCarbohydrate()+model2.getCarbohydrate());
         model.setProtein(model1.getProtein()+model2.getProtein());
         model.setVitaminA(model1.getVitaminA()+model2.getVitaminA());
         model.setVitaminB1(model1.getVitaminB1()+model2.getVitaminB1());
         model.setVitaminB2(model1.getVitaminB2()+model2.getVitaminB2());
         model.setVitaminB3(model1.getVitaminB3()+model2.getVitaminB3());
         model.setVitaminB6(model1.getVitaminB6()+model2.getVitaminB6());
         model.setVitaminB9(model1.getVitaminB9()+model2.getVitaminB9());
         model.setVitaminB12(model1.getVitaminB12()+model2.getVitaminB12());
         model.setVitaminC(model1.getVitaminC()+model2.getVitaminC());
         model.setVitaminD(model1.getVitaminD()+model2.getVitaminD());
         model.setCalcium(model1.getCalcium()+model2.getCalcium());
         model.setKalium(model1.getKalium()+model2.getKalium());
         model.setIodine(model1.getIodine()+model2.getIodine());
         model.setIron(model1.getIron()+model2.getIron());
         model.setNatrium(model1.getNatrium()+model2.getNatrium());
         model.setZinc(model1.getZinc()+model2.getZinc());
         model.setMagnesium(model1.getMagnesium()+model2.getMagnesium());
         return model;
     }
}
