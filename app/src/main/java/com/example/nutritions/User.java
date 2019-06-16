package com.example.nutritions;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class User extends BaseObservable {
    private double protein;

    @Bindable
    public Double getProtein(){
        return protein;
    }

    public void setProtein(double protein){
        this.protein=protein;

        notifyPropertyChanged(BR.user);
    }
}
