package com.example.nutritions.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutritions.R;
import com.example.nutritions.Utility;
import com.example.nutritions.models.Nutrients;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    int counter = 0;
    DataSnapshot dataSnapshot;
    ArrayList<Double> kcalListPrevWeek;

    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        kcalListPrevWeek=new ArrayList<>();
        for (int i = 0;i<7;i++){
            kcalListPrevWeek.add(0.0);
        }
        Button backbutton = findViewById(R.id.backButtonDetails);
        backbutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              finish();
                                          }
                                      });
        loadBarChart();
    }
    
    public void loadBarChart(){
        Description d = new Description();
        barChart = findViewById(R.id.barChart);
        d.setText("Daily calories");
        barChart.setDescription(d);
        updateDataset();
    }

    public void updateChart(){
        ArrayList<BarEntry> data = new ArrayList<>();
        data.add(new BarEntry(0,kcalListPrevWeek.get(0).intValue()));
        data.add(new BarEntry(1,kcalListPrevWeek.get(1).intValue()));
        data.add(new BarEntry(2,kcalListPrevWeek.get(2).intValue()));
        data.add(new BarEntry(3,kcalListPrevWeek.get(3).intValue()));
        data.add(new BarEntry(4,kcalListPrevWeek.get(4).intValue()));
        data.add(new BarEntry(5,kcalListPrevWeek.get(5).intValue()));
        data.add(new BarEntry(6,kcalListPrevWeek.get(6).intValue()));
        BarDataSet barDataSet = new BarDataSet(data,"Calories");
        BarData barData = new BarData(barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
    }

    public void updateDataset(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<String> pastWeek = Utility.getPast7Days();
        for (String s: pastWeek){
            System.out.println(s);
        }
        DatabaseReference todayRef = database.getReference("users").child(uid);
        todayRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                counter++;
                if (dataSnapshot.exists()) {
                    for (int i =0;i<7;i++){
                        if (dataSnapshot.child(pastWeek.get(i)).exists()){
                            Nutrients nutrients = dataSnapshot.child(pastWeek.get(i)).getValue(Nutrients.class);
                            kcalListPrevWeek.set(i,nutrients.getKcal());
                        } else {
                            kcalListPrevWeek.set(i,0.0);
                        }
                    }
                    updateChart();
                } else {
                    System.out.println("Context: DetailsActivity onDataChange todayRef: dataSnapshot doesn't exist...");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
