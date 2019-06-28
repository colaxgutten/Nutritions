package com.example.nutritions;

import java.util.HashMap;

public class FoodNutrientConsentrationModel {
    private String foodName;
    private double kcal;
    private double grams;
    private double proteinsPer100;
    private double fatPer100;
    private double carbohydratesPer100;
    private double vitaminAPer100;
    private double vitaminB1Per100;
    private double vitaminB2Per100;
    private double vitaminB3Per100;
    private double vitaminB6Per100;
    private double vitaminB9Per100;
    private double vitaminB12Per100;
    private double vitaminCPer100;
    private double vitaminDPer100;
    private double calciumPer100;
    private double iodinePer100;
    private double ironPer100;
    private double magnesiumPer100;
    private double kaliumPer100;
    private double natriumPer100;
    private double zincPer100;

    public FoodNutrientConsentrationModel(String name){
        this.foodName = name;
        grams=0;
        kcal=0;
        proteinsPer100=0;
        fatPer100=0;
        carbohydratesPer100=0;
        vitaminAPer100=0;
        vitaminB1Per100=0;
        vitaminB2Per100=0;
        vitaminB3Per100=0;
        vitaminB6Per100=0;
        vitaminB9Per100=0;
        vitaminB12Per100=0;
        vitaminCPer100=0;
        vitaminDPer100=0;
        calciumPer100=0;
        iodinePer100=0;
        magnesiumPer100=0;
        kaliumPer100=0;
        ironPer100=0;
        zincPer100=0;
        natriumPer100=0;
    }

    public double getKcalPer100(){
        return kcal;
    }

    public void setKcalPer100(double kcalPer100){
        kcal = kcalPer100;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getProteinsPer100() {
        return proteinsPer100;
    }

    public void setProteinsPer100(double proteinsPer100) {
        this.proteinsPer100 = proteinsPer100;
    }

    public double getFatPer100() {
        return fatPer100;
    }

    public void setFatPer100(double fatPer100) {
        this.fatPer100 = fatPer100;
    }

    public double getCarbohydratesPer100() {
        return carbohydratesPer100;
    }

    public void setCarbohydratesPer100(double carbohydratesPer100) {
        this.carbohydratesPer100 = carbohydratesPer100;
    }

    public double getVitaminAPer100() {
        return vitaminAPer100;
    }

    public void setVitaminAPer100(double vitaminAPer100) {
        this.vitaminAPer100 = vitaminAPer100;
    }

    public double getVitaminB1Per100() {
        return vitaminB1Per100;
    }

    public void setVitaminB1Per100(double vitaminB1Per100) {
        this.vitaminB1Per100 = vitaminB1Per100;
    }

    public double getVitaminB2Per100() {
        return vitaminB2Per100;
    }

    public void setVitaminB2Per100(double vitaminB2Per100) {
        this.vitaminB2Per100 = vitaminB2Per100;
    }

    public double getVitaminB3Per100() {
        return vitaminB3Per100;
    }

    public void setVitaminB3Per100(double vitaminB3Per100) {
        this.vitaminB3Per100 = vitaminB3Per100;
    }

    public double getVitaminB6Per100() {
        return vitaminB6Per100;
    }

    public void setVitaminB6Per100(double vitaminB6Per100) {
        this.vitaminB6Per100 = vitaminB6Per100;
    }

    public double getVitaminB9Per100() {
        return vitaminB9Per100;
    }

    public void setVitaminB9Per100(double vitaminB9Per100) {
        this.vitaminB9Per100 = vitaminB9Per100;
    }

    public double getVitaminB12Per100() {
        return vitaminB12Per100;
    }

    public void setVitaminB12Per100(double vitaminB12Per100) {
        this.vitaminB12Per100 = vitaminB12Per100;
    }

    public double getVitaminCPer100() {
        return vitaminCPer100;
    }

    public void setVitaminCPer100(double vitaminCPer100) {
        this.vitaminCPer100 = vitaminCPer100;
    }

    public double getVitaminDPer100() {
        return vitaminDPer100;
    }

    public void setVitaminDPer100(double vitaminDPer100) {
        this.vitaminDPer100 = vitaminDPer100;
    }

    public double getCalciumPer100() {
        return calciumPer100;
    }

    public void setCalciumPer100(double calciumPer100) {
        this.calciumPer100 = calciumPer100;
    }

    public double getIodinePer100() {
        return iodinePer100;
    }

    public void setIodinePer100(double iodinePer100) {
        this.iodinePer100 = iodinePer100;
    }

    public double getIronPer100() {
        return ironPer100;
    }

    public void setIronPer100(double ironPer100) {
        this.ironPer100 = ironPer100;
    }

    public double getMagnesiumPer100() {
        return magnesiumPer100;
    }

    public void setMagnesiumPer100(double magnesiumPer100) {
        this.magnesiumPer100 = magnesiumPer100;
    }

    public double getKaliumPer100() {
        return kaliumPer100;
    }

    public void setKaliumPer100(double kaliumPer100) {
        this.kaliumPer100 = kaliumPer100;
    }

    public double getNatriumPer100() {
        return natriumPer100;
    }

    public void setNatriumPer100(double natriumPer100) {
        this.natriumPer100 = natriumPer100;
    }

    public double getZincPer100() {
        return zincPer100;
    }

    public void setZincPer100(double zincPer100) {
        this.zincPer100 = zincPer100;
    }

    public HashMap<String,Double> getHashMap(){
        HashMap<String,Double> map = new HashMap<>();
        map.put("protein",this.proteinsPer100);
        map.put("carbohydrate",this.carbohydratesPer100);
        map.put("fat",this.fatPer100);
        map.put("vitaminA",this.vitaminAPer100);
        map.put("vitaminB1",this.vitaminB1Per100);
        map.put("vitaminB2",this.vitaminB2Per100);
        map.put("vitaminB3",this.vitaminB3Per100);
        map.put("vitaminB6",this.vitaminB6Per100);
        map.put("vitaminB9",this.vitaminB9Per100);
        map.put("vitaminB12",this.vitaminB12Per100);
        map.put("vitaminC",this.vitaminCPer100);
        map.put("vitaminD",this.vitaminDPer100);
        map.put("calcium",this.calciumPer100);
        map.put("iodine",this.iodinePer100);
        map.put("magnesium",this.magnesiumPer100);
        map.put("kalium",this.kaliumPer100);
        map.put("natrium",this.natriumPer100);
        map.put("zinc",this.zincPer100);
        map.put("iron",this.ironPer100);
        return map;
    }
}
