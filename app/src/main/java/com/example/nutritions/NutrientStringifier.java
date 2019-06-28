package com.example.nutritions;

public class NutrientStringifier {
    public static String ProgressBarStringify(double value,String nutrientType){
        String percentValue = "";
        String unitType="mg";
        double percent = 0;
        double roundedValue = (double)((int)(value*100))/100;
        percent = getPercent(value,nutrientType);
        percent = Math.round(percent);
        percentValue = percent+" %";
        if (nutrientType.equals("protein") || nutrientType.equals("fat") || nutrientType.equals("carbohydrate"))
            unitType="g";
        if (nutrientType.equals("kcal"))
            unitType="kcal";
        return roundedValue+unitType+" ("+percentValue+")";
    }
    public static double getPercent(double value,String nutrientType){
        double percent = 0;
        switch (nutrientType){
            case "kcal" :
                percent = (value/StaticDefaults.MAX_KCAL)*100;
                break;
            case "protein" :
                percent = (value/StaticDefaults.MAX_PROTEINS)*100;
                break;
            case "fat":{
                percent = (value/StaticDefaults.MAX_FAT)*100;
                break;
            }
            case "carbohydrate":{
                percent = (value/StaticDefaults.MAX_CARBOHYDRATES)*100;
                break;
            }
            case "vitaminA":{
                percent = (value/StaticDefaults.MAX_VITAMINA)*100;
                break;
            }
            case "vitaminB1":{
                percent = (value/StaticDefaults.MAX_VITAMINB1)*100;
                break;
            }
            case "vitaminB2":{
                percent = (value/StaticDefaults.MAX_VITAMINB2)*100;
                break;
            }
            case "vitaminB3":{
                percent = (value/StaticDefaults.MAX_VITAMINB3)*100;
                break;
            }
            case "vitaminB6":{
                percent = (value/StaticDefaults.MAX_VITAMINB6)*100;
                break;
            }
            case "vitaminB9":{
                percent = (value/StaticDefaults.MAX_VITAMINB9)*100;
                break;
            }
            case "vitaminB12":{
                percent = (value/StaticDefaults.MAX_VITAMINB12)*100;
                break;
            }
            case "vitaminC":{
                percent = (value/StaticDefaults.MAX_VITAMINC)*100;
                break;
            }
            case "vitaminD":{
                percent = (value/StaticDefaults.MAX_VITAMIND)*100;
                break;
            }
            case "calcium":{
                percent = (value/StaticDefaults.MAX_CALCIUM)*100;
                break;
            }
            case "iodine":{
                percent = (value/StaticDefaults.MAX_IODINE)*100;
                break;
            }
            case "iron":{
                percent = (value/StaticDefaults.MAX_IRON)*100;
                break;
            }
            case "magnesium":{
                percent = (value/StaticDefaults.MAX_MAGNESIUM)*100;
                break;
            }
            case "kalium":{
                percent = (value/StaticDefaults.MAX_KALIUM)*100;
                break;
            }
            case "natrium":{
                percent = (value/StaticDefaults.MAX_NATRIUM)*100;
                break;
            }
            case "zinc":{
                percent = (value/StaticDefaults.MAX_ZINC)*100;
                break;
            }
        }
        return percent;
    }
}
