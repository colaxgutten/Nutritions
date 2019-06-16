package com.example.nutritions;

import android.databinding.ObservableField;

import java.util.HashMap;

public class TodayNutritionsModel {
    public final ObservableField<Double> protein = new ObservableField<>();
    public final ObservableField<Double> carbohydrate = new ObservableField<>();
    public final ObservableField<Double> fat = new ObservableField<>();
    public final ObservableField<Double> vitaminA = new ObservableField<>();
    public final ObservableField<Double> vitaminB1 = new ObservableField<>();
    public final ObservableField<Double> vitaminB2 = new ObservableField<>();
    public final ObservableField<Double> vitaminB3 = new ObservableField<>();
    public final ObservableField<Double> vitaminB6 = new ObservableField<>();
    public final ObservableField<Double> vitaminB9 = new ObservableField<>();
    public final ObservableField<Double> vitaminB12 = new ObservableField<>();
    public final ObservableField<Double> vitaminC = new ObservableField<>();
    public final ObservableField<Double> vitaminD = new ObservableField<>();
    public final ObservableField<Double> calcium = new ObservableField<>();
    public final ObservableField<Double> iodine = new ObservableField<>();
    public final ObservableField<Double> iron = new ObservableField<>();
    public final ObservableField<Double> magnesium = new ObservableField<>();
    public final ObservableField<Double> kalium = new ObservableField<>();
    public final ObservableField<Double> natrium = new ObservableField<>();
    public final ObservableField<Double> zinc = new ObservableField<>();

    public HashMap<String,Double> getHashMap(){
        HashMap<String,Double> map = new HashMap<>();
        map.put("protein",this.protein.get());
        map.put("carbohydrate",this.carbohydrate.get());
        map.put("fat",this.fat.get());
        map.put("vitaminA",this.vitaminA.get());
        map.put("vitaminB1",this.vitaminB1.get());
        map.put("vitaminB2",this.vitaminB2.get());
        map.put("vitaminB3",this.vitaminB3.get());
        map.put("vitaminB6",this.vitaminB6.get());
        map.put("vitaminB9",this.vitaminB9.get());
        map.put("vitaminB12",this.vitaminB12.get());
        map.put("vitaminC",this.vitaminC.get());
        map.put("vitaminD",this.vitaminD.get());
        map.put("calcium",this.calcium.get());
        map.put("iodine",this.iodine.get());
        map.put("magnesium",this.magnesium.get());
        map.put("kalium",this.kalium.get());
        map.put("natrium",this.natrium.get());
        map.put("zinc",this.zinc.get());
        map.put("iron",this.iron.get());
        return map;
    }

    public double getProtein(){
        return this.protein.get();
    }
}
