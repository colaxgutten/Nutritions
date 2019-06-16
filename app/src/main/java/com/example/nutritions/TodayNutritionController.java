package com.example.nutritions;

public class TodayNutritionController {
    private TodayNutritionsModel todayNutritionsModel;

    public TodayNutritionController(){
        todayNutritionsModel = new TodayNutritionsModel();
    }

    public TodayNutritionsModel getTodayNutritionsModel() {
        return todayNutritionsModel;
    }

    public void addProtein (double protein){
        double current = todayNutritionsModel.protein.get();
        todayNutritionsModel.protein.set(current+protein);
    }
    public void addCarbohydrate (double carbohydrate){
        double current = todayNutritionsModel.carbohydrate.get();
        todayNutritionsModel.carbohydrate.set(current+carbohydrate);
    }

    public void addFat(double fat){
        double current = todayNutritionsModel.fat.get();
        todayNutritionsModel.fat.set(current+fat);
    }
    public void addVitaminA (double vitaminA){
        double current = todayNutritionsModel.vitaminA.get();
        todayNutritionsModel.vitaminA.set(current+vitaminA);
    }
    public void addVitaminB1 (double vitaminB1){
        double current = todayNutritionsModel.vitaminB1.get();
        todayNutritionsModel.vitaminB1.set(current+vitaminB1);
    }
    public void addVitaminB2 (double vitaminB2){
        double current = todayNutritionsModel.vitaminB2.get();
        todayNutritionsModel.vitaminB2.set(current+vitaminB2);
    }
    public void addVitaminB3 (double vitaminB3){
        double current = todayNutritionsModel.vitaminB3.get();
        todayNutritionsModel.vitaminB3.set(current+vitaminB3);
    }
    public void addVitaminB6 (double vitaminB6){
        double current = todayNutritionsModel.vitaminB6.get();
        todayNutritionsModel.vitaminB6.set(current+vitaminB6);
    }
    public void addVitaminB9 (double vitaminB9){
        double current = todayNutritionsModel.vitaminB9.get();
        todayNutritionsModel.vitaminB9.set(current+vitaminB9);
    }
    public void addVitaminB12 (double vitaminB12){
        double current = todayNutritionsModel.vitaminB12.get();
        todayNutritionsModel.vitaminB12.set(current+vitaminB12);
    }
    public void addVitaminC (double vitaminC){
        double current = todayNutritionsModel.vitaminC.get();
        todayNutritionsModel.vitaminC.set(current+vitaminC);
    }
    public void addVitaminD (double vitaminD){
        double current = todayNutritionsModel.vitaminD.get();
        todayNutritionsModel.vitaminD.set(current+vitaminD);
    }
    public void addCalcium (double calcium){
        double current = todayNutritionsModel.calcium.get();
        todayNutritionsModel.calcium.set(current+calcium);
    }
    public void addIodine (double iodine){
        double current = todayNutritionsModel.iodine.get();
        todayNutritionsModel.iodine.set(current+iodine);
    }
    public void addIron (double iron){
        double current = todayNutritionsModel.iron.get();
        todayNutritionsModel.iron.set(current+iron);
    }
    public void addMagnesium (double magnesium){
        double current = todayNutritionsModel.magnesium.get();
        todayNutritionsModel.magnesium.set(current+magnesium);
    }
    public void addKalium (double kalium){
        double current = todayNutritionsModel.kalium.get();
        todayNutritionsModel.kalium.set(current+kalium);
    }
    public void addNatrium (double natrium){
        double current = todayNutritionsModel.natrium.get();
        todayNutritionsModel.natrium.set(current+natrium);
    }
    public void addZinc (double zinc){
        double current = todayNutritionsModel.zinc.get();
        todayNutritionsModel.zinc.set(current+zinc);
    }

    public void addFood(FoodNutrientConsentrationModel model){
        if (model!=null) {
            double grams = model.getGrams();
            double multiplier = grams/100;
            this.addProtein(model.getProteinsPer100() *multiplier);
            this.addCalcium(model.getCarbohydratesPer100()*multiplier);
            this.addFat(model.getFatPer100()*multiplier);
            this.addVitaminA(model.getVitaminAPer100()*multiplier);
            this.addVitaminB1(model.getVitaminB1Per100()*multiplier);
            this.addVitaminB2(model.getVitaminB2Per100()*multiplier);
            this.addVitaminB3(model.getVitaminB3Per100()*multiplier);
            this.addVitaminB6(model.getVitaminB6Per100()*multiplier);
            this.addVitaminB9(model.getVitaminB9Per100()*multiplier);
            this.addVitaminB12(model.getVitaminB12Per100()*multiplier);
            this.addVitaminC(model.getVitaminCPer100()*multiplier);
            this.addVitaminD(model.getVitaminDPer100()*multiplier);
            this.addCalcium(model.getCalciumPer100()*multiplier);
            this.addIodine(model.getIodinePer100()*multiplier);
            this.addIron(model.getIronPer100()*multiplier);
            this.addMagnesium(model.getMagnesiumPer100()*multiplier);
            this.addKalium(model.getKaliumPer100()*multiplier);
            this.addNatrium(model.getNatriumPer100()*multiplier);
            this.addZinc(model.getZincPer100()*multiplier);
        }
    }
}
