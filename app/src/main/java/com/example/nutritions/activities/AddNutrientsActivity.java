package com.example.nutritions.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.nutritions.MealController;
import com.example.nutritions.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddNutrientsActivity extends AppCompatActivity {
    String selectedFoodForMeal = "";
    DatabaseReference usersReference;
    DatabaseReference usersReference2;
    DatabaseReference foodReference;
    DatabaseReference mealReference;
    DataSnapshot foodSnapShot;
    DataSnapshot userSnapShot;
    MealController mealController;
    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<String> mealList;
    String username;

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivityForResult(new Intent(getApplicationContext(), MainActivity.class), 0);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Add Nutrients");
        setContentView(R.layout.activity_add_nutrients);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mealController = new MealController();
        username = FirebaseAuth.getInstance().getUid().toString();
        listView = findViewById(R.id.mealListView);
        mealList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mealList);
        listView.setAdapter(adapter);
        usersReference2 = database.getReference("users");
        usersReference = database.getReference("users").child(username);
        mealReference = database.getReference("users").child("meal");
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

        Button backbutton = findViewById(R.id.backbuttonMeal);
        backbutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              finish();
                                          }
                                      }
        );
        Button addProductToMealButton = findViewById(R.id.addProductMealButton);
        Button addMealButton = findViewById(R.id.addMealButton);
        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mealNameText = findViewById(R.id.addMealText);
                String mealNameFromView = mealNameText.getText().toString();
                mealReference.child(mealNameFromView).setValue(mealController.getMeal());
                resetMeal();
            }
        });
        addProductToMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFoodForMeal.length()>=1){

                    if (foodSnapShot.child(selectedFoodForMeal).exists()){
                        EditText weight = findViewById(R.id.foodWeightMeal);
                        String gramsString = weight.getText().toString();
                        double grams;
                        if (gramsString.length()>=1){
                            grams = Double.parseDouble(gramsString);
                        } else
                            grams=100;
                        mealController.addProductToMeal(selectedFoodForMeal,grams);
                        mealList.add(selectedFoodForMeal);
                        adapter.notifyDataSetChanged();
                    } else {
                        System.out.println("Food doesn't exist.");
                    }
                } else {
                    System.out.println("You have no selected food. . .");
                }
            }
        });
    }

    public void resetMeal(){
        mealController.NewMeal();
        mealList.clear();
        adapter.notifyDataSetChanged();
    }


    public void loadFood(){
        AutoCompleteTextView foodViewMeal = findViewById(R.id.foodListMeal);
        ArrayList<String> foodList = new ArrayList<>();
            for (DataSnapshot d : foodSnapShot.getChildren()) {
                foodList.add(d.getKey());
            }
        System.out.println("Food Loaded");
        ArrayAdapter<String> adapterMeal = new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1,foodList);
        foodViewMeal.setAdapter(adapterMeal);
        foodViewMeal.setDropDownWidth(-1);
        foodViewMeal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                if (selected!=null)
                    selectedFoodForMeal=selected;
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}
