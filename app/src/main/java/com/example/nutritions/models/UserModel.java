package com.example.nutritions.models;

public class UserModel {
    public String gender;
    public String macro;
    public String activityLevel;
    private int age;
    private int height;
    private double weight;


    public void DefaultModel(){
        age = 25;
        height = 180;
        weight = 75;
        gender = "male";
        macro = "active";
        activityLevel = "medium";
    }

    public UserModel(){}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /*
    return String macro - can be 'active','intense' or 'weightLoss'
     */
    public String getMacro() {
        return macro;
    }

    public void setMacro(String macro) {
        this.macro = macro;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
