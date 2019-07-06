package com.example.nutritions.models;

public class Nutrition {
    private Double protein;
    private Double kcal;
    private Double fat;
    private Double carbohydrate;
    private Double vitaminA;
    private Double vitaminB1;
    private Double vitaminB2;
    private Double vitaminB3;
    private Double vitaminB6;
    private Double vitaminB9;
    private Double vitaminB12;
    private Double vitaminC;
    private Double calcium;
    private Double iodine;
    private Double iron;
    private Double kalium;
    private Double magnesium;
    private Double natrium;
    private Double zinc;
    private Double vitaminD;

    public Nutrition() {}

    public Nutrition(Double protein, Double kcal, Double fat, Double carbohydrate, Double vitaminA, Double vitaminB1, Double vitaminB2, Double vitaminB3, Double vitaminB6, Double vitaminB9, Double vitaminB12, Double vitaminC, Double calcium, Double iodine, Double iron, Double kalium, Double magnesium, Double natrium, Double zinc, Double vitaminD) {
        this.protein = protein;
        this.kcal = kcal;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.vitaminA = vitaminA;
        this.vitaminB1 = vitaminB1;
        this.vitaminB2 = vitaminB2;
        this.vitaminB3 = vitaminB3;
        this.vitaminB6 = vitaminB6;
        this.vitaminB9 = vitaminB9;
        this.vitaminB12 = vitaminB12;
        this.vitaminC = vitaminC;
        this.calcium = calcium;
        this.iodine = iodine;
        this.iron = iron;
        this.kalium = kalium;
        this.magnesium = magnesium;
        this.natrium = natrium;
        this.zinc = zinc;
        this.vitaminD = vitaminD;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "protein=" + protein +
                ", kcal=" + kcal +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                ", vitaminA=" + vitaminA +
                ", vitaminB1=" + vitaminB1 +
                ", vitaminB2=" + vitaminB2 +
                ", vitaminB3=" + vitaminB3 +
                ", vitaminB6=" + vitaminB6 +
                ", vitaminB9=" + vitaminB9 +
                ", vitaminB12=" + vitaminB12 +
                ", vitaminC=" + vitaminC +
                ", calcium=" + calcium +
                ", iodine=" + iodine +
                ", iron=" + iron +
                ", kalium=" + kalium +
                ", magnesium=" + magnesium +
                ", natrium=" + natrium +
                ", zinc=" + zinc +
                ", vitaminD=" + vitaminD +
                '}';
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public Double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Double getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(Double vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public Double getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(Double vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public Double getVitaminB3() {
        return vitaminB3;
    }

    public void setVitaminB3(Double vitaminB3) {
        this.vitaminB3 = vitaminB3;
    }

    public Double getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(Double vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public Double getVitaminB9() {
        return vitaminB9;
    }

    public void setVitaminB9(Double vitaminB9) {
        this.vitaminB9 = vitaminB9;
    }

    public Double getVitaminB12() {
        return vitaminB12;
    }

    public void setVitaminB12(Double vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }

    public Double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getIodine() {
        return iodine;
    }

    public void setIodine(Double iodine) {
        this.iodine = iodine;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Double getKalium() {
        return kalium;
    }

    public void setKalium(Double kalium) {
        this.kalium = kalium;
    }

    public Double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    public Double getNatrium() {
        return natrium;
    }

    public void setNatrium(Double natrium) {
        this.natrium = natrium;
    }

    public Double getZinc() {
        return zinc;
    }

    public void setZinc(Double zinc) {
        this.zinc = zinc;
    }

    public Double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(Double vitaminD) {
        this.vitaminD = vitaminD;
    }
}
