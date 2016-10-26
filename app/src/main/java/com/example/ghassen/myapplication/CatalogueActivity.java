package com.example.ghassen.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ghassen.myapplication.slider.PrefManager;


public class CatalogueActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout cultureL,histoireL,natureL,geoL;
    TextView cultureLevel,histoireLevel,natureLevel,geoLevel;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  getSupportActionBar().hide() ;
        setContentView(R.layout.catalogue);
        cultureL=(LinearLayout) findViewById(R.id.culture_ll);
        histoireL=(LinearLayout) findViewById(R.id.histoire_ll);
        natureL=(LinearLayout) findViewById(R.id.nature_ll);
        geoL=(LinearLayout) findViewById(R.id.geographie_ll);
        cultureLevel=(TextView) findViewById(R.id.cultureLevel);
        histoireLevel=(TextView) findViewById(R.id.histoireLevel);
        natureLevel=(TextView) findViewById(R.id.natureLevel);
        geoLevel=(TextView) findViewById(R.id.geoLevel);
        prefManager=new PrefManager(this);
        cultureL.setOnClickListener(this);
        natureL.setOnClickListener(this);
        geoL.setOnClickListener(this);
        histoireL.setOnClickListener(this);
        cultureLevel.setText( "Culture    "+prefManager.getCurrentLevelCulture()+"/2 ");
        natureLevel.setText(  "Nature     "+prefManager.getCurrentLevelNature()+"/2 ");
        histoireLevel.setText("Histoire   "+prefManager.getCurrentLevelHistoire()+"/2 ");
        geoLevel.setText(     "Géographie "+prefManager.getCurrentLevelGeo()+"/2 ");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.culture_ll: startActivity(new Intent(this,MainActivity.class).putExtra("categorie","culture"));finish();break;
            case R.id.nature_ll:  startActivity(new Intent(this,MainActivity.class).putExtra("categorie","nature"));finish();break;
            case R.id.geographie_ll: startActivity(new Intent(this,MainActivity.class).putExtra("categorie","géographie"));finish();break;
            case R.id.histoire_ll: startActivity(new Intent(this,MainActivity.class).putExtra("categorie","histoire"));finish();break;

        }
    }
}