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

public class AddNutrients extends AppCompatActivity {
    String selectedFood= "";
    DatabaseReference usersReference;
    DatabaseReference foodReference;
    DataSnapshot foodSnapShot;
    SnapshotToModelCoverter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrients);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        converter = new SnapshotToModelCoverter();
        usersReference = database.getReference("users");
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        loadFood();

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
                        double grams = Double.parseDouble(gramsString);
                        if (grams <=0)
                            grams=100;
                        converter.convertDataSnapshot(food);
                    } else {
                        System.out.println("Food doesn't exist.");
                    }
                }
            }
        });

    }

    public void loadFood(){
        AutoCompleteTextView foodView = findViewById(R.id.foodList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,StaticFoodList.food);
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
