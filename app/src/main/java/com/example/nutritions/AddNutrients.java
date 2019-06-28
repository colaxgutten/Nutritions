package com.example.nutritions;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddNutrients extends AppCompatActivity {
    String selectedFood= "";
    CurrentDate currentDate;
    DatabaseReference usersReference;
    DatabaseReference usersReference2;
    DatabaseReference foodReference;
    DataSnapshot foodSnapShot;
    DataSnapshot userSnapShot;
    SnapshotToModelCoverter converter;
    String username;
    boolean foodLoaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrients);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        currentDate = new CurrentDate();
        username = "daniel";
        converter = new SnapshotToModelCoverter();
        usersReference2 = database.getReference("users");
        usersReference = database.getReference("users").child(username);
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

        Button backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              finish();
                                          }
                                      }
        );
        Button addButton = findViewById(R.id.addProductButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFood.length()>=1){
                    System.out.println(foodSnapShot.getChildrenCount());
                    for(DataSnapshot d :foodSnapShot.getChildren()){
                        System.out.println(d.getKey());
                    }
                    if (foodSnapShot.child(selectedFood).exists()){
                        DataSnapshot food = foodSnapShot.child(selectedFood);
                        EditText weight = findViewById(R.id.foodWeight);
                        String gramsString = weight.getText().toString();
                        double grams;
                        if (gramsString.length()>=1){
                            grams = Double.parseDouble(gramsString);
                        } else
                            grams=100;
                        TodayNutritionsModel foodModel = converter.convertDataSnapshot(food,grams);
                        TodayNutritionsModel todayNutritionsModel = converter.convertDataSnapshot(userSnapShot.child(currentDate.getCurrentDate()),100);
                        TodayNutritionsModel sum = TodayNutritionModelAdder.addModels(foodModel,todayNutritionsModel);
                        ModelFirebaseSynchronizer synchronizer = new ModelFirebaseSynchronizer();
                        synchronizer.saveDailyModel(sum,usersReference2);
                    } else {
                        System.out.println("Food doesn't exist.");
                    }
                } else {
                    System.out.println("You have no selected food. . .");
                }
            }
        });

    }

    public void loadFood(){
        AutoCompleteTextView foodView = findViewById(R.id.foodList);
        ArrayList<String> foodList = new ArrayList<>();
            for (DataSnapshot d : foodSnapShot.getChildren()) {
                foodList.add(d.getKey());
                System.out.println(d.getKey());
            }
        System.out.println("Food Loaded");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,foodList);
        foodView.setAdapter(adapter);
        foodView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String)parent.getItemAtPosition(position);
                if (selected!=null){
                    selectedFood = selected;
                }
            }
        });
    }
}
