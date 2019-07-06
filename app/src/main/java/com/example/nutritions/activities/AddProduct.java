package com.example.nutritions.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutritions.MealController;
import com.example.nutritions.ModelFirebaseSynchronizer;
import com.example.nutritions.R;
import com.example.nutritions.SnapshotToModelCoverter;
import com.example.nutritions.Utility;
import com.example.nutritions.models.Nutrients;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {
    String selectedFood = "";
    String selectedMeal = "";
    DatabaseReference usersReference;
    DatabaseReference foodReference;
    DatabaseReference mealReference;
    DataSnapshot foodSnapShot;
    DataSnapshot userSnapShot;
    DataSnapshot mealSnapShot;
    SnapshotToModelCoverter converter;
    String uid;
    MealController mealController;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivityForResult(new Intent(getApplicationContext(), MainActivity.class), 0);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Add Product");
        setContentView(R.layout.activity_add_product);
        mealController = new MealController();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        converter = new SnapshotToModelCoverter();
        usersReference = database.getReference("users").child(uid);
        mealReference = database.getReference("users").child("meal");
        mealReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mealSnapShot = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userSnapShot = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        foodReference = database.getReference("users").child("food");
        foodReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foodSnapShot = dataSnapshot;
                loadFood();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button backbutton = findViewById(R.id.backButtonProduct);
        backbutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              finish();
                                          }
                                      }
        );

        FloatingActionButton addNutrients = findViewById(R.id.addMeal);
        addNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProduct.this, AddNutrients.class));
            }
        });

        Button addMealButton = findViewById(R.id.addMealButton);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMeal.length()>=1){
                    boolean added = false;
                    Nutrients todayNutritionsModel = converter.convertDataSnapshot(userSnapShot.child(Utility.getCurrentDate()), 100);
                    if (mealSnapShot.child(selectedMeal).exists()) {
                        added=true;
                        for (DataSnapshot d : mealSnapShot.child(selectedMeal).getChildren()) {
                            String foodString = d.getKey();
                            System.out.println("Context: AddProduct.class, addButton.onClick: " + foodString);
                            double grams = d.getValue(double.class);
                            if (foodSnapShot.hasChild(foodString)) {
                                DataSnapshot food = foodSnapShot.child(foodString);
                                Nutrients foodModel = converter.convertDataSnapshot(food, grams);
                                todayNutritionsModel.addNutrients(foodModel);
                            }
                        }
                    }
                    if (added)
                        Toast.makeText(AddProduct.this, "Meal added", Toast.LENGTH_SHORT).show();
                    ModelFirebaseSynchronizer synchronizer = new ModelFirebaseSynchronizer();
                    synchronizer.saveDailyModel(todayNutritionsModel,usersReference);
                }
            }
        });
        Button addButton = findViewById(R.id.addProductButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFood.length() >= 1) {
                    System.out.println(foodSnapShot.getChildrenCount());
                    for (DataSnapshot d : foodSnapShot.getChildren()) {
                        System.out.println(d.getKey());
                    }
                    if (foodSnapShot.child(selectedFood).exists()) {
                        DataSnapshot food = foodSnapShot.child(selectedFood);
                        EditText weight = findViewById(R.id.foodWeight);
                        String gramsString = weight.getText().toString();
                        weight.setText("");
                        double grams;
                        if (gramsString.length() >= 1) {
                            grams = Double.parseDouble(gramsString);
                        } else
                            grams = 100;
                        Nutrients foodModel = converter.convertDataSnapshot(food, grams);
                        Nutrients todayNutritionsModel = converter.convertDataSnapshot(userSnapShot.child(Utility.getCurrentDate()), 100);
                        todayNutritionsModel.addNutrients(foodModel);
                        ModelFirebaseSynchronizer synchronizer = new ModelFirebaseSynchronizer();
                        synchronizer.saveDailyModel(todayNutritionsModel, usersReference);
                        Toast.makeText(AddProduct.this, "Product added", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("Food doesn't exist.");
                    }
                } else {
                    System.out.println("You have no selected food. . .");
                }
            }
        });
    }

    public void loadFood() {
        AutoCompleteTextView foodView = findViewById(R.id.foodList);
        AutoCompleteTextView mealView = findViewById(R.id.mealList);
        ArrayList<String> mealList = new ArrayList<>();
        for (DataSnapshot d : mealSnapShot.getChildren()){
            mealList.add(d.getKey());
        }
        System.out.println("MealList Size: " + mealList.size());
        ArrayList<String> foodList = new ArrayList<>();
        for (DataSnapshot d : foodSnapShot.getChildren()) {
            foodList.add(d.getKey());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, foodList);
        ArrayAdapter<String> mealAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1,mealList);
        mealView.setAdapter(mealAdapter);
        foodView.setAdapter(adapter);
        foodView.setDropDownWidth(-1);
        foodView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if (selected != null) {
                    selectedFood = selected;
                }
            }
        });
        mealView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMeal = (String) parent.getItemAtPosition(position);
            }
        });
    }
}
