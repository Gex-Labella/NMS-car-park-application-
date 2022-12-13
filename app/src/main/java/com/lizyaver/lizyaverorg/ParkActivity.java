package com.lizyaver.lizyaverorg;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ParkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        ViewPager mViewPager = findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);

        // Spinner element
        final Spinner spinner = findViewById(R.id.spinner);
        Button button = findViewById(R.id.button);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Spot 1");
        categories.add("Spot 2");
        categories.add("Spot 3");
        categories.add("Spot 4");
        categories.add("Spot 5");
        categories.add("Spot 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}