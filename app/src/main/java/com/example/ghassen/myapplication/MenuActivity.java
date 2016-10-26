package com.example.ghassen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.ghassen.myapplication.slider.WelcomeActivity;


/**
 * Created by Guesmi on 28/07/2016.
 */

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton commencerB,parametresB,infoB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MenuActivity.this.overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide() ;
        setContentView(R.layout.menu_activity);
        commencerB=(ImageButton) findViewById(R.id.commencerB);
        parametresB=(ImageButton) findViewById(R.id.parametreB);
        infoB=(ImageButton) findViewById(R.id.infoB);
        commencerB.setOnClickListener(this);
        parametresB.setOnClickListener(this);
        infoB.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commencerB:
                startActivity(new Intent(MenuActivity.this,CatalogueActivity.class));
                break;
            case R.id.parametreB:
                startActivity(new Intent(MenuActivity.this,ParamActivity.class));
                break;
            case R.id.infoB:
                startActivity(new Intent(MenuActivity.this,WelcomeActivity.class).putExtra("MninJit","menu"));
                break;

        }
    }
}
