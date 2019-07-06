package com.example.nutritions.models;

public class Nutrients {
    private double protein;
    private double kcal;
    private double fat;
    private double carbohydrate;
    private double vitaminA;
    private double vitaminB1;
    private double vitaminB2;
    private double vitaminB3;
    private double vitaminB6;
    private double vitaminB9;
    private double vitaminB12;
    private double vitaminC;
    private double calcium;
    private double iodine;
    private double iron;
    private double kalium;
    private double magnesium;
    private double natrium;
    private double zinc;
    private double vitaminD;

    public Nutrients() {
    }

    public Nutrients(double protein, double kcal, double fat, double carbohydrate, double vitaminA, double vitaminB1, double vitaminB2, double vitaminB3, double vitaminB6, double vitaminB9, double vitaminB12, double vitaminC, double calcium, double iodine, double iron, double kalium, double magnesium, double natrium, double zinc, double vitaminD) {
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

    public void addNutrients(Nutrients nutrients) {
        if (nutrients == null)
            return;

        this.protein += nutrients.getProtein();
        this.kcal += nutrients.getKcal();
        this.fat += nutrients.getFat();
        this.carbohydrate += nutrients.getCarbohydrate();
        this.vitaminA += nutrients.getVitaminA();
        this.vitaminB1 += nutrients.getVitaminB1();
        this.vitaminB2 += nutrients.getVitaminB2();
        this.vitaminB3 += nutrients.getVitaminB3();
        this.vitaminB6 += nutrients.getVitaminB6();
        this.vitaminB9 += nutrients.getVitaminB9();
        this.vitaminB12 += nutrients.getVitaminB12();
        this.vitaminC += nutrients.getVitaminC();
        this.calcium += nutrients.getCalcium();
        this.iodine += nutrients.getIodine();
        this.iron += nutrients.getIron();
        this.kalium += nutrients.getKalium();
        this.magnesium += nutrients.getMagnesium();
        this.natrium += nutrients.getNatrium();
        this.zinc += nutrients.getZinc();
        this.vitaminD += nutrients.getVitaminD();
    }

    /**
     * Multiplies all nutrients with a given value
     *
     * @param multiplier - the value to multiply with
     */
    public void multiplyNutrients(double multiplier) {
        setProtein(getProtein() * multiplier);
        setKcal(getKcal() * multiplier);
        setFat(getFat() * multiplier);
        setCarbohydrate(getCarbohydrate() * multiplier);
        setVitaminA(getVitaminA() * multiplier);
        setVitaminB1(getVitaminB1() * multiplier);
        setVitaminB2(getVitaminB2() * multiplier);
        setVitaminB3(getVitaminB3() * multiplier);
        setVitaminB6(getVitaminB6() * multiplier);
        setVitaminB9(getVitaminB9() * multiplier);
        setVitaminB12(getVitaminB12() * multiplier);
        setVitaminC(getVitaminC() * multiplier);
        setCalcium(getCalcium() * multiplier);
        setIodine(getIodine() * multiplier);
        setIron(getIron() * multiplier);
        setKalium(getKalium() * multiplier);
        setMagnesium(getMagnesium() * multiplier);
        setNatrium(getNatrium() * multiplier);
        setZinc(getZinc() * multiplier);
        setVitaminD(getVitaminD() * multiplier);
    }

    @Override
    public String toString() {
        return "Nutrients{" +
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

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
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

    public double getKalium() {
        return kalium;
    }

    public void setKalium(double kalium) {
        this.kalium = kalium;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
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

    public double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
    }
}
