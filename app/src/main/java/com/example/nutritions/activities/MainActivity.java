package com.example.nutritions.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.nutritions.DailyNutritionLimitsModel;
import com.example.nutritions.ModelFirebaseSynchronizer;
import com.example.nutritions.NutrientStringifier;
import com.example.nutritions.R;
import com.example.nutritions.Utility;
import com.example.nutritions.models.Nutrients;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * Constants
     */
    final static int REQUEST_CODE_A = 1;

    /**
     * Private variables
     */
    private ActionBar actionBar;
    private FirebaseAuth mAuth;
    private PieChart pieChart;
    private DatabaseReference nutrients;
    private ModelFirebaseSynchronizer modelFirebaseSynchronizer;
    private DailyNutritionLimitsModel model;
    private Nutrients todayNutritionsModel;
    private FirebaseDatabase database;
    private FloatingActionButton addNutrients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MainActivity:onCreate");

        setContentView(R.layout.activity_main);
        todayNutritionsModel = new Nutrients();
        actionBar = getSupportActionBar();
        pieChart = findViewById(R.id.pieChart);
        addNutrients = findViewById(R.id.addNutrients);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        modelFirebaseSynchronizer = new ModelFirebaseSynchronizer();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                System.out.println("State changed: " + firebaseAuth.getCurrentUser());
            }
        });

        loadDefaultPieChart();
        initalizeBars();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        addNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("user is: " + FirebaseAuth.getInstance().getCurrentUser());
                startActivity(new Intent(MainActivity.this, AddProduct.class));
            }
        });

        // User is not logged in, redirect to login screen
        if (mAuth.getCurrentUser() == null) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivityForResult(loginIntent, REQUEST_CODE_A);
        } else {
            setOnChangeListener();
        }
    }

    //reference = users/uid/
    public void saveEmptyModel(DatabaseReference reference) {
        todayNutritionsModel = new Nutrients();
        modelFirebaseSynchronizer.saveDailyModel(todayNutritionsModel, reference);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_A:
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    setOnChangeListener();
                } else {
                    System.out.println("User is null");
                }
                break;
        }
    }

    private void setOnChangeListener() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            return;
        }

        nutrients = database.getReference("users").child(currentUser.getUid()).child(Utility.getCurrentDate());
        nutrients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Nutrients nutrients = dataSnapshot.getValue(Nutrients.class);

                    if (nutrients != null) {
                        todayNutritionsModel.setKcal(nutrients.getKcal());
                        todayNutritionsModel.setFat(nutrients.getFat());
                        todayNutritionsModel.setProtein(nutrients.getProtein());
                        todayNutritionsModel.setCarbohydrate(nutrients.getCarbohydrate());
                        todayNutritionsModel.setVitaminA(nutrients.getVitaminA());
                        todayNutritionsModel.setVitaminB1(nutrients.getVitaminB1());
                        todayNutritionsModel.setVitaminB2(nutrients.getVitaminB2());
                        todayNutritionsModel.setVitaminB3(nutrients.getVitaminB3());
                        todayNutritionsModel.setVitaminB6(nutrients.getVitaminB6());
                        todayNutritionsModel.setVitaminB9(nutrients.getVitaminB9());
                        todayNutritionsModel.setVitaminB12(nutrients.getVitaminB12());
                        todayNutritionsModel.setVitaminC(nutrients.getVitaminC());
                        todayNutritionsModel.setVitaminD(nutrients.getVitaminD());
                        todayNutritionsModel.setCalcium(nutrients.getCalcium());
                        todayNutritionsModel.setIodine(nutrients.getIodine());
                        todayNutritionsModel.setIron(nutrients.getIron());
                        todayNutritionsModel.setMagnesium(nutrients.getMagnesium());
                        todayNutritionsModel.setKalium(nutrients.getKalium());
                        todayNutritionsModel.setNatrium(nutrients.getNatrium());
                        todayNutritionsModel.setZinc(nutrients.getZinc());

                        updateData();
                        return;
                    }
                }

                saveEmptyModel(database.getReference().child("users").child(mAuth.getCurrentUser().getUid()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateData() {
        ProgressBar kcalBar = findViewById(R.id.kcalBar);
        ProgressBar proteinBar = findViewById(R.id.proteinBar);
        ProgressBar fatBar = findViewById(R.id.fatBar);
        ProgressBar carbohydrateBar = findViewById(R.id.carbohydrateBar);
        ProgressBar calciumBar = findViewById(R.id.calciumBar);
        ProgressBar iodineBar = findViewById(R.id.iodineBar);
        ProgressBar ironBar = findViewById(R.id.ironBar);
        ProgressBar magnesiumBar = findViewById(R.id.magnesiumBar);
        ProgressBar kaliumBar = findViewById(R.id.kaliumBar);
        ProgressBar natriumBar = findViewById(R.id.natriumBar);
        ProgressBar zincBar = findViewById(R.id.zincBar);
        ProgressBar vitaminABar = findViewById(R.id.vitaminABar);
        ProgressBar vitaminB1Bar = findViewById(R.id.vitaminB1Bar);
        ProgressBar vitaminB2Bar = findViewById(R.id.vitaminB2Bar);
        ProgressBar vitaminB3Bar = findViewById(R.id.vitaminB3Bar);
        ProgressBar vitaminB6Bar = findViewById(R.id.vitaminB6Bar);
        ProgressBar vitaminB9Bar = findViewById(R.id.vitaminB9Bar);
        ProgressBar vitaminB12Bar = findViewById(R.id.vitaminB12Bar);
        ProgressBar vitaminCBar = findViewById(R.id.vitaminCBar);
        ProgressBar vitaminDBar = findViewById(R.id.vitaminDBar);
        TextView kcalText = findViewById(R.id.kcalValue);
        TextView proteinText = findViewById(R.id.proteinValue);
        TextView carbohydrateText = findViewById(R.id.carbohydrateValue);
        TextView fatText = findViewById(R.id.fatValue);
        TextView calciumText = findViewById(R.id.calciumValue);
        TextView iodineText = findViewById(R.id.iodineValue);
        TextView ironText = findViewById(R.id.ironValue);
        TextView magnesiumText = findViewById(R.id.magnesiumValue);
        TextView kaliumText = findViewById(R.id.kaliumValue);
        TextView natriumText = findViewById(R.id.natriumValue);
        TextView zincText = findViewById(R.id.zincValue);
        TextView vitaminAText = findViewById(R.id.vitaminAValue);
        TextView vitaminB1Text = findViewById(R.id.vitaminB1Value);
        TextView vitaminB2Text = findViewById(R.id.vitaminB2Value);
        TextView vitaminB3Text = findViewById(R.id.vitaminB3Value);
        TextView vitaminB6Text = findViewById(R.id.vitaminB6Value);
        TextView vitaminB9Text = findViewById(R.id.vitaminB9Value);
        TextView vitaminB12Text = findViewById(R.id.vitaminB12Value);
        TextView vitaminCText = findViewById(R.id.vitaminCValue);
        TextView vitaminDText = findViewById(R.id.vitaminDValue);
        System.out.println("Data should be updated");
        kcalText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getKcal(), "kcal"));
        proteinText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getProtein(), "protein"));
        carbohydrateText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getCarbohydrate(), "carbohydrate"));
        fatText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getFat(), "fat"));
        calciumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getCalcium(), "calcium"));
        iodineText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getIodine(), "iodine"));
        ironText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getIron(), "iron"));
        magnesiumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getMagnesium(), "magnesium"));
        kaliumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getKalium(), "kalium"));
        natriumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getNatrium(), "natrium"));
        zincText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getZinc(), "zinc"));
        vitaminAText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminA(), "vitaminA"));
        vitaminB1Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB1(), "vitaminB1"));
        vitaminB2Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB2(), "vitaminB2"));
        vitaminB3Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB3(), "vitaminB3"));
        vitaminB6Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB6(), "vitaminB6"));
        vitaminB9Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB9(), "vitaminB9"));
        vitaminB12Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB12(), "vitaminB12"));
        vitaminCText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminC(), "vitaminC"));
        vitaminDText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminD(), "vitaminD"));
        kcalBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getKcal(), "kcal"));
        proteinBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getProtein(), "protein"));
        fatBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getFat(), "fat"));
        carbohydrateBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getCarbohydrate(), "carbohydrate"));
        calciumBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getCalcium(), "calcium"));
        iodineBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getIodine(), "iodine"));
        ironBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getIron(), "iron"));
        magnesiumBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getMagnesium(), "magnesium"));
        kaliumBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getKalium(), "kalium"));
        natriumBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getNatrium(), "natrium"));
        zincBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getZinc(), "zinc"));
        vitaminABar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminA(), "vitaminA"));
        vitaminB1Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB1(), "vitaminB1"));
        vitaminB2Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB2(), "vitaminB2"));
        vitaminB3Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB3(), "vitaminB3"));
        vitaminB6Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB6(), "vitaminB6"));
        vitaminB9Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB9(), "vitaminB9"));
        vitaminB12Bar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB12(), "vitaminB12"));
        vitaminCBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminC(), "vitaminC"));
        vitaminDBar.setProgress((int) NutrientStringifier.getPercent(todayNutritionsModel.getVitaminD(), "vitaminD"));
        //loads and refreshes data of pieChart
        addDataSet(getNutrtionBalance(), getNutritionBalanceProperties());
    }

    private ArrayList<String> getNutritionBalanceProperties() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Nutrients");
        list.add("Carbohydrate");
        list.add("Fat");
        return list;
    }

    //returns the calories balance of protein, carbs and fat. Note that fat gives 2.25 more calories per gram than carbs or protein
    private ArrayList<Integer> getNutrtionBalance() {
        ArrayList<Integer> balance = new ArrayList<>();
        double protein = todayNutritionsModel.getProtein();
        double carbohydrate = todayNutritionsModel.getCarbohydrate();
        double fat = todayNutritionsModel.getFat() * 2.25;
        System.out.println(protein + "protein in getNutritionBalance");
        System.out.println(carbohydrate + "carbohydrate in getNutritionBalance");
        System.out.println(fat + "fat in getNutritionBalance");

        double sum = protein + carbohydrate + fat;
        if (sum == 0) {
            balance.add(1);
            balance.add(1);
            balance.add(1);
        } else {
            balance.add((int) ((protein / sum) * 100));
            balance.add((int) ((carbohydrate / sum) * 100));
            balance.add((int) ((fat / sum) * 100));
        }
        return balance;
    }

    public void loadDefaultPieChart() {
        Description d = new Description();
        d.setText("Balance");
        pieChart.setCenterText("Balance");
        pieChart.setCenterTextSize(10);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleRadius(5);
        pieChart.setDescription(d);
        addDataSet();
    }

    //values : Nutrients, Carbohydrate, Fat
    public void addDataSet(ArrayList<Integer> values, ArrayList<String> properties) {
        String defaultErrorString = "Error";
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        Description d = new Description();
        d.setText("");
        pieChart.setCenterText("Balance");
        pieChart.setCenterTextSize(10);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleRadius(5);
        pieChart.setDescription(d);
        for (int i = 0; i < values.size(); i++) {
            String property = properties.size() == values.size() ? properties.get(i) : defaultErrorString;
            yEntries.add(new PieEntry(values.get(i), property));
            System.out.println(property + ": " + values.get(i));
        }
        PieDataSet pieDataSet = new PieDataSet(yEntries, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(ColorTemplate.rgb("000000"));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.rgb("ffff00"));
        colors.add(ColorTemplate.rgb("ff3300"));
        colors.add(ColorTemplate.rgb("4da6ff"));
        pieDataSet.setColors(colors);
        PieData data = new PieData(pieDataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    public void addDataSet() {
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        yEntries.add(new PieEntry(1, "Nutrients"));
        yEntries.add(new PieEntry(1, "Carbohydrate"));
        yEntries.add(new PieEntry(1, "Fat"));

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Nutritions");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(ColorTemplate.rgb("000000"));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.rgb("ffff00"));
        colors.add(ColorTemplate.rgb("ff3300"));
        colors.add(ColorTemplate.rgb("4da6ff"));
        pieDataSet.setColors(colors);
        PieData data = new PieData(pieDataSet);
        pieChart.setData(data);
        pieChart.invalidate();

    }

    public void initalizeBars() {
        model = DailyNutritionLimitsModel.getInstance();
        TextView proteinText = findViewById(R.id.proteinValue);
        TextView carbohydrateText = findViewById(R.id.carbohydrateValue);
        TextView fatText = findViewById(R.id.fatValue);
        proteinText.setText(String.valueOf(0));
        carbohydrateText.setText(String.valueOf(0));
        fatText.setText(String.valueOf(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
