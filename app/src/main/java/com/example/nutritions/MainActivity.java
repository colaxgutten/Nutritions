package com.example.nutritions;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    FirebaseAuth mAuth;
    DailyNutritionLimitsModel model;
    CurrentDate currentDate;
    String username;
    PieChart pieChart;
    DatabaseReference nutrients;
    ModelFirebaseSynchronizer modelFirebaseSynchronizer;
    TodayNutritionController todayNutritionController;
    TodayNutritionsModel todayNutritionsModel;
    FirebaseDatabase database;
    final static int REQUEST_CODE_A = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE_A);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        modelFirebaseSynchronizer = new ModelFirebaseSynchronizer();
        currentDate = new CurrentDate();
        loadDaily();
        pieChart = findViewById(R.id.pieChart);
        loadDefaultPieChart();
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                System.out.println("State changed: "+firebaseAuth.getCurrentUser());
            }
        });

        initalizeBars();
        database = FirebaseDatabase.getInstance();
        FloatingActionButton addNutrients = findViewById(R.id.addNutrients);
        addNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("user is: " + FirebaseAuth.getInstance().getCurrentUser());
                startActivity(new Intent(MainActivity.this, AddProduct.class));
            }
        });

    }
    //reference = users/uid/
    public void saveEmptyModel( DatabaseReference reference){
        loadDaily();
        modelFirebaseSynchronizer.saveDailyModel(todayNutritionsModel,reference);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String context = "onActivityResult: ";
        switch (requestCode){
            case REQUEST_CODE_A:
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                if (user!=null) {
                    setOnChangeListener();
                } else {
                    System.out.println("User is null");
                }
                break;
        }
    }

    private void setOnChangeListener(){
        nutrients = database.getReference("users").child(mAuth.getCurrentUser().getUid()).child(currentDate.getCurrentDate());
        nutrients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("protein").exists()){
                    double kcal = dataSnapshot.child("kcal").getValue(Double.class);
                    double protein = dataSnapshot.child("protein").getValue(Double.class);double fat = dataSnapshot.child("fat").getValue(Double.class);
                    double carbohydrate = dataSnapshot.child("carbohydrate").getValue(Double.class);
                    double vitaminA = dataSnapshot.child("vitaminA").getValue(Double.class);
                    double vitaminB1 = dataSnapshot.child("vitaminB1").getValue(Double.class);
                    double vitaminB2 = dataSnapshot.child("vitaminB2").getValue(Double.class);
                    double vitaminB3 = dataSnapshot.child("vitaminB3").getValue(Double.class);
                    double vitaminB6 = dataSnapshot.child("vitaminB6").getValue(Double.class);
                    double vitaminB9 = dataSnapshot.child("vitaminB9").getValue(Double.class);
                    double vitaminB12 = dataSnapshot.child("vitaminB12").getValue(Double.class);
                    double vitaminC = dataSnapshot.child("vitaminC").getValue(Double.class);
                    double calcium = dataSnapshot.child("calcium").getValue(Double.class);
                    double iodine = dataSnapshot.child("iodine").getValue(Double.class);
                    double iron = dataSnapshot.child("iron").getValue(Double.class);
                    double kalium = dataSnapshot.child("kalium").getValue(Double.class);
                    double magnesium = dataSnapshot.child("magnesium").getValue(Double.class);
                    double natrium = dataSnapshot.child("natrium").getValue(Double.class);
                    double zinc = dataSnapshot.child("zinc").getValue(Double.class);
                    double vitaminD = dataSnapshot.child("vitaminD").getValue(Double.class);
                    todayNutritionsModel.setKcal(kcal);
                    todayNutritionsModel.setFat(fat);
                    todayNutritionsModel.setProtein(protein);
                    todayNutritionsModel.setCarbohydrate(carbohydrate);
                    todayNutritionsModel.setVitaminA(vitaminA);
                    todayNutritionsModel.setVitaminB1(vitaminB1);
                    todayNutritionsModel.setVitaminB2(vitaminB2);
                    todayNutritionsModel.setVitaminB3(vitaminB3);
                    todayNutritionsModel.setVitaminB6(vitaminB6);
                    todayNutritionsModel.setVitaminB9(vitaminB9);
                    todayNutritionsModel.setVitaminB12(vitaminB12);
                    todayNutritionsModel.setVitaminC(vitaminC);
                    todayNutritionsModel.setVitaminD(vitaminD);
                    todayNutritionsModel.setCalcium(calcium);
                    todayNutritionsModel.setIodine(iodine);
                    todayNutritionsModel.setIron(iron);
                    todayNutritionsModel.setMagnesium(magnesium);
                    todayNutritionsModel.setKalium(kalium);
                    todayNutritionsModel.setNatrium(natrium);
                    todayNutritionsModel.setZinc(zinc);
                    updateData();
                } else {
                    saveEmptyModel(database.getReference().child("users").child(mAuth.getCurrentUser().getUid()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateData(){
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
        kcalText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getKcal(),"kcal"));
        proteinText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getProtein(),"protein"));
        carbohydrateText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getCarbohydrate(),"carbohydrate"));
        fatText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getFat(),"fat"));
        calciumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getCalcium(),"calcium"));
        iodineText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getIodine(),"iodine"));
        ironText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getIron(),"iron"));
        magnesiumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getMagnesium(),"magnesium"));
        kaliumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getKalium(),"kalium"));
        natriumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getNatrium(),"natrium"));
        zincText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getZinc(),"zinc"));
        vitaminAText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminA(),"vitaminA"));
        vitaminB1Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB1(),"vitaminB1"));
        vitaminB2Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB2(),"vitaminB2"));
        vitaminB3Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB3(),"vitaminB3"));
        vitaminB6Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB6(),"vitaminB6"));
        vitaminB9Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB9(),"vitaminB9"));
        vitaminB12Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminB12(),"vitaminB12"));
        vitaminCText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminC(),"vitaminC"));
        vitaminDText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.getVitaminD(),"vitaminD"));
        kcalBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getKcal(),"kcal"));
        proteinBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getProtein(),"protein"));
        fatBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getFat(),"fat"));
        carbohydrateBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getCarbohydrate(),"carbohydrate"));
        calciumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getCalcium(),"calcium"));
        iodineBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getIodine(),"iodine"));
        ironBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getIron(),"iron"));
        magnesiumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getMagnesium(),"magnesium"));
        kaliumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getKalium(),"kalium"));
        natriumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getNatrium(),"natrium"));
        zincBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getZinc(),"zinc"));
        vitaminABar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminA(),"vitaminA"));
        vitaminB1Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB1(),"vitaminB1"));
        vitaminB2Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB2(),"vitaminB2"));
        vitaminB3Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB3(),"vitaminB3"));
        vitaminB6Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB6(),"vitaminB6"));
        vitaminB9Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB9(),"vitaminB9"));
        vitaminB12Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminB12(),"vitaminB12"));
        vitaminCBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminC(),"vitaminC"));
        vitaminDBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.getVitaminD(),"vitaminD"));
        //loads and refreshes data of pieChart
        addDataSet(getNutrtionBalance(),getNutritionBalanceProperties());
    }

    private ArrayList<String> getNutritionBalanceProperties() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Protein");
        list.add("Carbohydrate");
        list.add("Fat");
        return list;
    }

    //returns the calories balance of protein, carbs and fat. Note that fat gives 2.25 more calories per gram than carbs or protein
    private ArrayList<Integer> getNutrtionBalance() {
        ArrayList<Integer> balance = new ArrayList<>();
        double protein = todayNutritionsModel.getProtein();
        double carbohydrate = todayNutritionsModel.getCarbohydrate();
        double fat = todayNutritionsModel.getFat()*2.25;
        System.out.println(protein + "protein in getNutritionBalance");
        System.out.println(carbohydrate + "carbohydrate in getNutritionBalance");
        System.out.println(fat + "fat in getNutritionBalance");

        double sum = protein+carbohydrate+fat;
        if (sum==0){
            balance.add(1);
            balance.add(1);
            balance.add(1);
        } else {
            balance.add((int)((protein/sum)*100));
            balance.add((int)((carbohydrate/sum)*100));
            balance.add((int)((fat/sum)*100));
        }
        return balance;
    }

    public void addActivity(View v){
        startActivity(new Intent(MainActivity.this, AddNutrients.class));
    }

    public void loadDaily(){
        todayNutritionController = new TodayNutritionController();
        todayNutritionsModel = todayNutritionController.getTodayNutritionsModel();
    }

    public void loadDefaultPieChart(){
        Description d = new Description();
        d.setText("Balance");
        pieChart.setCenterText("Balance");
        pieChart.setCenterTextSize(10);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);
        pieChart.setHoleRadius(5);
        pieChart.setDescription(d);
        addDataSet(pieChart);
    }

    //values : Protein, Carbohydrate, Fat
    public void addDataSet(ArrayList<Integer> values, ArrayList<String> properties){
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
        for (int i = 0; i<values.size();i++){
            String property = properties.size()==values.size() ? properties.get(i) : defaultErrorString;
            yEntries.add(new PieEntry(values.get(i),property));
            System.out.println(property+": "+values.get(i));
        }
        PieDataSet pieDataSet = new PieDataSet(yEntries,"");
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

    public void addDataSet(PieChart pie){
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> properties = new ArrayList<>();
        properties.add("Protein");
        properties.add("Carbohydrate");
        properties.add("Fat");
        yEntries.add(new PieEntry(1,"Protein"));
        yEntries.add(new PieEntry(1,"Carbohydrate"));
        yEntries.add(new PieEntry(1,"Fat"));

        PieDataSet pieDataSet = new PieDataSet(yEntries,"Nutritions");
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

    public void initalizeBars(){
        model = DailyNutritionLimitsModel.getInstance();
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
   /*     proteinBar.setMax((int)model.getProteinMax());
        fatBar.setMax((int)model.getFatMax());
        carbohydrateBar.setMax((int)model.getCarbohydrateMax());
        calciumBar.setMax((int)model.getCalciumMax());
        iodineBar.setMax((int)model.getIodineMax());
        ironBar.setMax((int)model.getIronMax());
        magnesiumBar.setMax((int)model.getMagnesiumMax());
        kaliumBar.setMax((int)model.getKaliumMax());
        natriumBar.setMax((int)model.getNatriumMax());
        zincBar.setMax((int)model.getZincMax());
        vitaminABar.setMax((int)model.getVitaminAMax());
        vitaminB1Bar.setMax((int)model.getVitaminB1Max());
        vitaminB2Bar.setMax((int)model.getVitaminB2Max());
        vitaminB3Bar.setMax((int)model.getVitaminB3Max());
        vitaminB6Bar.setMax((int)model.getVitaminB6Max());
        vitaminB9Bar.setMax((int)model.getVitaminB9Max());
        vitaminB12Bar.setMax((int)model.getVitaminB12Max());
        vitaminCBar.setMax((int)model.getVitaminCMax());
        carbohydrateBar.setMax((int)model.getCarbohydrateMax());
        fatBar.setMax((int)model.getFatMax());
        vitaminDBar.setMax((int)model.getVitaminDMax());*/
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
