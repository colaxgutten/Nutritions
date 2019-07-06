package com.example.nutritions.settings;

import static com.example.nutritions.settings.StaticDefaults.DEFAULT_GENDER;
import static com.example.nutritions.settings.StaticDefaults.DEFAULT_HEIGHT;
import static com.example.nutritions.settings.StaticDefaults.DEFAULT_WEEKLYEXERCISES;
import static com.example.nutritions.settings.StaticDefaults.DEFAULT_WEIGHT;

public class UserSettings {
    private double weight;
    private double height;
    private boolean male;
    private int weeklyExercises;

    public UserSettings() {
        this.weeklyExercises = 0;
        this.weight = 0;
        this.height = 0;
        this.male = true;
    }

    public void setDefault() {
        this.weight = DEFAULT_WEIGHT;
        this.height = DEFAULT_HEIGHT;
        this.weeklyExercises = DEFAULT_WEEKLYEXERCISES;
        this.male = DEFAULT_GENDER;
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
