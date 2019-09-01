package com.example.nutritions;

import com.example.nutritions.models.UserModel;

public class User {
    private UserModel userModel;
    public User(){
        userModel = new UserModel();
        userModel.DefaultModel();
    }
    // The Mifflin - ST Jeoir BMR equation
    public double calculateBMR(){
        //Male calculation
        if (userModel.getGender().equals("male")){
            return (10*userModel.getWeight())+(6.25*userModel.getHeight())-(5*userModel.getAge())+5;
        }
        //Female calculation
            else {
            return (10*userModel.getWeight())+(6.25*userModel.getHeight())-(5*userModel.getAge())-161;
        }
    }

    public double calculateActivityKCAL(){
        if (userModel.getActivityLevel().equals("low")){
            return 200;
        } else if (userModel.getActivityLevel().equals("medium")){
            return 350;
        } else if (userModel.getActivityLevel().equals("high")){
            return 500;
        }
        return 0;
    }

    public double calculateDailyKCAL(){
        return calculateBMR()+calculateActivityKCAL();
    }

    /*
    Calculates and returns the daily limits of nutrients in grams corresponding to the users age, gender, weight, height, activity level and nutrition macro
    @return list of values: daily max protein in grams, daily max carbohydrate in grams and daily max fat in grams respectively
     */
    public double[] getNutritionBalance(){
        double[] limits = new double[3];
        double caloriesMax = calculateDailyKCAL();
        double proteinPercentage = getProteinPercentage();
        double carbohydratePercentage = getCarbohydratePercentage();
        double fatPercentage = getFatPercentage();
        double proteinGrams = (caloriesMax*(proteinPercentage/100))/4;
        double carbohydrateGrams = (caloriesMax*(carbohydratePercentage/100))/4;
        double fatGrams = (caloriesMax*(fatPercentage/100))/9;
        limits[0]=proteinGrams;limits[1]=carbohydrateGrams;limits[2]=fatGrams;
        return limits;
    }

    public double getProteinPercentage(){
        if (userModel.getMacro().equals("active"))
            return 15;
        else if (userModel.getMacro().equals("intense"))
            return 20;
        else return 30;
    }

    public double getCarbohydratePercentage(){
        if (userModel.getMacro().equals("active"))
            return 55;
        else if (userModel.getMacro().equals("intense"))
            return 55;
        else return 50;
    }

    public double getFatPercentage(){
        if (userModel.getMacro().equals("active"))
            return 30;
        else if (userModel.getMacro().equals("intense"))
            return 25;
        else return 20;
    }

}
