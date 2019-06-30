package com.example.nutritions;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.example.nutritions.databinding.ActivityMainBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.databinding.DataBindingUtil;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DailyNutritionLimitsModel model;
    CurrentDate currentDate;
    String username;
    User user;
    PieChart pieChart;
    ModelFirebaseSynchronizer modelFirebaseSynchronizer;
    DatabaseReference reference;
    TodayNutritionController todayNutritionController;
    TodayNutritionsModel todayNutritionsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        modelFirebaseSynchronizer = new ModelFirebaseSynchronizer();
        username = "daniel";
        currentDate = new CurrentDate();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        loadDaily();
        pieChart = findViewById(R.id.pieChart);
        loadDefaultPieChart();
        binding.setTodayNutritionsModel(todayNutritionsModel);
        user = new User();
        user.setProtein(0);
        binding.setUser(user);


        initalizeBars();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        final DatabaseReference nutrients = database.getReference("users").child(username).child(currentDate.getCurrentDate());
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
                    todayNutritionsModel.kcal.set(kcal);
                    todayNutritionsModel.fat.set(fat);
                    todayNutritionsModel.protein.set(protein);
                    todayNutritionsModel.carbohydrate.set(carbohydrate);
                    todayNutritionsModel.vitaminA.set(vitaminA);
                    todayNutritionsModel.vitaminB1.set(vitaminB1);
                    todayNutritionsModel.vitaminB2.set(vitaminB2);
                    todayNutritionsModel.vitaminB3.set(vitaminB3);
                    todayNutritionsModel.vitaminB6.set(vitaminB6);
                    todayNutritionsModel.vitaminB9.set(vitaminB9);
                    todayNutritionsModel.vitaminB12.set(vitaminB12);
                    todayNutritionsModel.vitaminC.set(vitaminC);
                    todayNutritionsModel.vitaminD.set(vitaminD);
                    todayNutritionsModel.calcium.set(calcium);
                    todayNutritionsModel.iodine.set(iodine);
                    todayNutritionsModel.iron.set(iron);
                    todayNutritionsModel.magnesium.set(magnesium);
                    todayNutritionsModel.kalium.set(kalium);
                    todayNutritionsModel.natrium.set(natrium);
                    todayNutritionsModel.zinc.set(zinc);
                    updateData();
                } else {
                    saveEmptyModel(reference);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton addNutrients = findViewById(R.id.addNutrients);
        addNutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity(view);
            }
        });

    }

    public void saveEmptyModel( DatabaseReference reference){
        loadDaily();
        modelFirebaseSynchronizer.saveDailyModel(todayNutritionsModel,reference);
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
        kcalText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.kcal.get(),"kcal"));
        proteinText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.protein.get(),"protein"));
        carbohydrateText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.carbohydrate.get(),"carbohydrate"));
        fatText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.fat.get(),"fat"));
        calciumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.calcium.get(),"calcium"));
        iodineText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.iodine.get(),"iodine"));
        ironText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.iron.get(),"iron"));
        magnesiumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.magnesium.get(),"magnesium"));
        kaliumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.kalium.get(),"kalium"));
        natriumText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.natrium.get(),"natrium"));
        zincText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.zinc.get(),"zinc"));
        vitaminAText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminA.get(),"vitaminA"));
        vitaminB1Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB1.get(),"vitaminB1"));
        vitaminB2Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB2.get(),"vitaminB2"));
        vitaminB3Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB3.get(),"vitaminB3"));
        vitaminB6Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB6.get(),"vitaminB6"));
        vitaminB9Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB9.get(),"vitaminB9"));
        vitaminB12Text.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminB12.get(),"vitaminB12"));
        vitaminCText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminC.get(),"vitaminC"));
        vitaminDText.setText(NutrientStringifier.ProgressBarStringify(todayNutritionsModel.vitaminD.get(),"vitaminD"));
        kcalBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.kcal.get(),"kcal"));
        proteinBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.protein.get(),"protein"));
        fatBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.fat.get(),"fat"));
        carbohydrateBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.carbohydrate.get(),"carbohydrate"));
        calciumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.calcium.get(),"calcium"));
        iodineBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.iodine.get(),"iodine"));
        ironBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.iron.get(),"iron"));
        magnesiumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.magnesium.get(),"magnesium"));
        kaliumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.kalium.get(),"kalium"));
        natriumBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.natrium.get(),"natrium"));
        zincBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.zinc.get(),"zinc"));
        vitaminABar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminA.get(),"vitaminA"));
        vitaminB1Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB1.get(),"vitaminB1"));
        vitaminB2Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB2.get(),"vitaminB2"));
        vitaminB3Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB3.get(),"vitaminB3"));
        vitaminB6Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB6.get(),"vitaminB6"));
        vitaminB9Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB9.get(),"vitaminB9"));
        vitaminB12Bar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminB12.get(),"vitaminB12"));
        vitaminCBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminC.get(),"vitaminC"));
        vitaminDBar.setProgress((int)NutrientStringifier.getPercent(todayNutritionsModel.vitaminD.get(),"vitaminD"));
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
        double protein = todayNutritionsModel.protein.get();
        double carbohydrate = todayNutritionsModel.carbohydrate.get();
        double fat = todayNutritionsModel.fat.get()*2.25;
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
        if (todayNutritionsModel.carbohydrate.get()==null) {
            todayNutritionsModel.kcal.set(0.0);
            todayNutritionsModel.protein.set(0.0);
            todayNutritionsModel.fat.set(0.0);
            todayNutritionsModel.carbohydrate.set(0.0);
            todayNutritionsModel.vitaminA.set(0.0);
            todayNutritionsModel.vitaminB1.set(0.0);
            todayNutritionsModel.vitaminB2.set(0.0);
            todayNutritionsModel.vitaminB3.set(0.0);
            todayNutritionsModel.vitaminB6.set(0.0);
            todayNutritionsModel.vitaminB9.set(0.0);
            todayNutritionsModel.vitaminB12.set(0.0);
            todayNutritionsModel.vitaminC.set(0.0);
            todayNutritionsModel.vitaminD.set(0.0);
            todayNutritionsModel.calcium.set(0.0);
            todayNutritionsModel.iodine.set(0.0);
            todayNutritionsModel.iron.set(0.0);
            todayNutritionsModel.magnesium.set(0.0);
            todayNutritionsModel.kalium.set(0.0);
            todayNutritionsModel.natrium.set(0.0);
            todayNutritionsModel.zinc.set(0.0);
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
