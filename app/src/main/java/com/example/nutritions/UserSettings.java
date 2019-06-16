package com.example.nutritions;

public class UserSettings {
    private final double DEFAULT_WEIGHT = 80;
    private final double DEFAULT_HEIGHT = 180;
    private final int DEFAULT_WEEKLYEXERCISES = 3;
    private final boolean DEFAULT_GENDER = true;

    private double weight;
    private double height;
    private boolean male;
    private int weeklyExercises;

    public UserSettings(){
        this.weeklyExercises=0;
        this.weight=0;
        this.height=0;
        this.male=true;
    }

    public void setDefault(){
        this.weight=DEFAULT_WEIGHT;
        this.height=DEFAULT_HEIGHT;
        this.weeklyExercises=DEFAULT_WEEKLYEXERCISES;
        this.male=DEFAULT_GENDER;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getWeeklyExercises() {
        return weeklyExercises;
    }

    public void setWeeklyExercises(int weeklyExercises) {
        this.weeklyExercises = weeklyExercises;
    }
}
