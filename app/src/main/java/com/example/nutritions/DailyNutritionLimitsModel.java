package com.example.nutritions;

public class DailyNutritionLimitsModel {

    private static DailyNutritionLimitsModel model;


    public double proteinMax;
    private double carbohydrateMax;
    private double fatMax;
    private double vitaminAMax;
    private double vitaminB12Max;
    private double vitaminB6Max;
    private double vitaminCMax;
    private double vitaminDMax;
    private double vitaminB1Max;
    private double vitaminB2Max;
    private double vitaminB3Max;
    private double vitaminB9Max;
    private double calciumMax;
    private double iodineMax;
    private double ironMax;
    private double magnesiumMax;
    private double kaliumMax;
    private double natriumMax;
    private double zincMax;

    public static DailyNutritionLimitsModel getModel() {
        return model;
    }

    public static void setModel(DailyNutritionLimitsModel model) {
        DailyNutritionLimitsModel.model = model;
    }

    public double getVitaminAMax() {
        return vitaminAMax;
    }

    public void setVitaminAMax(double vitaminAMax) {
        this.vitaminAMax = vitaminAMax;
    }

    public double getVitaminB12Max() {
        return vitaminB12Max;
    }

    public void setVitaminB12Max(double vitaminB12Max) {
        this.vitaminB12Max = vitaminB12Max;
    }

    public double getVitaminB6Max() {
        return vitaminB6Max;
    }

    public void setVitaminB6Max(double vitaminB6Max) {
        this.vitaminB6Max = vitaminB6Max;
    }

    public double getVitaminCMax() {
        return vitaminCMax;
    }

    public void setVitaminCMax(double vitaminCMax) {
        this.vitaminCMax = vitaminCMax;
    }

    public double getVitaminDMax() {
        return vitaminDMax;
    }

    public void setVitaminDMax(double vitaminDMax) {
        this.vitaminDMax = vitaminDMax;
    }

    public double getVitaminB1Max() {
        return vitaminB1Max;
    }

    public void setVitaminB1Max(double vitaminB1Max) {
        this.vitaminB1Max = vitaminB1Max;
    }

    public double getVitaminB2Max() {
        return vitaminB2Max;
    }

    public void setVitaminB2Max(double vitaminB2Max) {
        this.vitaminB2Max = vitaminB2Max;
    }

    public double getVitaminB3Max() {
        return vitaminB3Max;
    }

    public void setVitaminB3Max(double vitaminB3Max) {
        this.vitaminB3Max = vitaminB3Max;
    }

    public double getVitaminB9Max() {
        return vitaminB9Max;
    }

    public void setVitaminB9Max(double vitaminB9Max) {
        this.vitaminB9Max = vitaminB9Max;
    }

    public double getCalciumMax() {
        return calciumMax;
    }

    public void setCalciumMax(double calciumMax) {
        this.calciumMax = calciumMax;
    }

    public double getIodineMax() {
        return iodineMax;
    }

    public void setIodineMax(double iodineMax) {
        this.iodineMax = iodineMax;
    }

    public double getIronMax() {
        return ironMax;
    }

    public void setIronMax(double ironMax) {
        this.ironMax = ironMax;
    }

    public double getMagnesiumMax() {
        return magnesiumMax;
    }

    public void setMagnesiumMax(double magnesiumMax) {
        this.magnesiumMax = magnesiumMax;
    }

    public double getKaliumMax() {
        return kaliumMax;
    }

    public void setKaliumMax(double kaliumMax) {
        this.kaliumMax = kaliumMax;
    }

    public double getNatriumMax() {
        return natriumMax;
    }

    public void setNatriumMax(double natriumMax) {
        this.natriumMax = natriumMax;
    }

    public double getZincMax() {
        return zincMax;
    }

    public void setZincMax(double zincMax) {
        this.zincMax = zincMax;
    }

    private DailyNutritionLimitsModel(double proteinMax, double carbohydrateMax, double fatMax){
        this.carbohydrateMax=carbohydrateMax;
        this.fatMax=fatMax;
        this.proteinMax=proteinMax;
    }

    private DailyNutritionLimitsModel(){
        //TODO - add all attriubtes to static list and set here
        this.carbohydrateMax=StaticDefaults.MAX_CARBOHYDRATES;
        this.proteinMax=StaticDefaults.MAX_PROTEINS;
        this.fatMax=StaticDefaults.MAX_FAT;
        this.vitaminAMax = StaticDefaults.MAX_VITAMINA;
        this.vitaminB1Max = StaticDefaults.MAX_VITAMINB1;
        this.vitaminB2Max = StaticDefaults.MAX_VITAMINB2;
        this.vitaminB3Max = StaticDefaults.MAX_VITAMINB3;
        this.vitaminB6Max = StaticDefaults.MAX_VITAMINB6;
        this.vitaminB9Max = StaticDefaults.MAX_VITAMINB9;
        this.vitaminB12Max = StaticDefaults.MAX_VITAMINB12;
        this.vitaminCMax = StaticDefaults.MAX_VITAMINC;
        this.vitaminDMax = StaticDefaults.MAX_VITAMIND;
        this.calciumMax = StaticDefaults.MAX_CALCIUM;
        this.iodineMax = StaticDefaults.MAX_IODINE;
        this.ironMax = StaticDefaults.MAX_IRON;
        this.magnesiumMax = StaticDefaults.MAX_MAGNESIUM;
        this.kaliumMax = StaticDefaults.MAX_KALIUM;
        this.natriumMax = StaticDefaults.MAX_NATRIUM;
        this.zincMax = StaticDefaults.MAX_ZINC;

    }

    public static synchronized DailyNutritionLimitsModel getInstance(){
        if (model == null){
            model = new DailyNutritionLimitsModel();
        }
            return model;
    }

    public void setNutrition(DailyNutritionLimitsModel model){
        this.model = model;
    }

    public double getProteinMax() {
        return proteinMax;
    }

    public void setProteinMax(double proteinMax) {
        this.proteinMax = proteinMax;
    }

    public double getCarbohydrateMax() {
        return carbohydrateMax;
    }

    public void setCarbohydrateMax(double carbohydrateMax) {
        this.carbohydrateMax = carbohydrateMax;
    }

    public double getFatMax() {
        return fatMax;
    }

    public void setFatMax(double fatMax) {
        this.fatMax = fatMax;
    }


}
