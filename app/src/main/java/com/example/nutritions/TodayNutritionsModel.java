package com.example.nutritions;

import java.util.HashMap;

public class TodayNutritionsModel {
    private double kcal;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double vitaminA;
    private double vitaminB1;
    private double vitaminB2;
    private double vitaminB3;
    private double vitaminB6;
    private double vitaminB9;
    private double vitaminB12;
    private double vitaminC;
    private double vitaminD;
    private double calcium;
    private double iodine;
    private double iron;
    private double magnesium;
    private double kalium;
    private double natrium;
    private double zinc;

    public TodayNutritionsModel(){
        kcal = 0;
        fat=0;
        protein=0;
        carbohydrate=0;
        vitaminA=0;
        vitaminB1=0;
        vitaminB2=0;
        vitaminB3=0;
        vitaminB6=0;
        vitaminB9=0;
        vitaminB12=0;
        vitaminC=0;
        vitaminD=0;
        calcium=0;
        iodine=0;
        iron=0;
        magnesium=0;
        kalium=0;
        natrium=0;
        zinc=0;
    }

    public HashMap<String,Double> getHashMap(){
        HashMap<String,Double> map = new HashMap<>();
        map.put("kcal",this.kcal);
        map.put("protein",this.protein);
        map.put("carbohydrate",this.carbohydrate);
        map.put("fat",this.fat);
        map.put("vitaminA",this.vitaminA);
        map.put("vitaminB1",this.vitaminB1);
        map.put("vitaminB2",this.vitaminB2);
        map.put("vitaminB3",this.vitaminB3);
        map.put("vitaminB6",this.vitaminB6);
        map.put("vitaminB9",this.vitaminB9);
        map.put("vitaminB12",this.vitaminB12);
        map.put("vitaminC",this.vitaminC);
        map.put("vitaminD",this.vitaminD);
        map.put("calcium",this.calcium);
        map.put("iodine",this.iodine);
        map.put("magnesium",this.magnesium);
        map.put("kalium",this.kalium);
        map.put("natrium",this.natrium);
        map.put("zinc",this.zinc);
        map.put("iron",this.iron);
        return map;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public double getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(double vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public double getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(double vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public double getVitaminB3() {
        return vitaminB3;
    }

    public void setVitaminB3(double vitaminB3) {
        this.vitaminB3 = vitaminB3;
    }

    public double getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(double vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public double getVitaminB9() {
        return vitaminB9;
    }

    public void setVitaminB9(double vitaminB9) {
        this.vitaminB9 = vitaminB9;
    }

    public double getVitaminB12() {
        return vitaminB12;
    }

    public void setVitaminB12(double vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIodine() {
        return iodine;
    }

    public void setIodine(double iodine) {
        this.iodine = iodine;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getKalium() {
        return kalium;
    }

    public void setKalium(double kalium) {
        this.kalium = kalium;
    }

    public double getNatrium() {
        return natrium;
    }

    public void setNatrium(double natrium) {
        this.natrium = natrium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }
}
