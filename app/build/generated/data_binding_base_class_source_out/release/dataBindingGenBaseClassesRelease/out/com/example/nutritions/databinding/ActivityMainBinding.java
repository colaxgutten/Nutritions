package com.example.nutritions.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nutritions.TodayNutritionsModel;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final FloatingActionButton addNutrients;

  @NonNull
  public final Toolbar toolbar;

  @Bindable
  protected TodayNutritionsModel mTodayNutritionsModel;

  @Bindable
  protected User mUser;

  protected ActivityMainBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, FloatingActionButton addNutrients, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.addNutrients = addNutrients;
    this.toolbar = toolbar;
  }

  public abstract void setTodayNutritionsModel(@Nullable TodayNutritionsModel todayNutritionsModel);

  @Nullable
  public TodayNutritionsModel getTodayNutritionsModel() {
    return mTodayNutritionsModel;
  }

  public abstract void setUser(@Nullable User user);

  @Nullable
  public User getUser() {
    return mUser;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.example.nutritions.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.example.nutritions.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityMainBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityMainBinding)bind(component, view, com.example.nutritions.R.layout.activity_main);
  }
}
