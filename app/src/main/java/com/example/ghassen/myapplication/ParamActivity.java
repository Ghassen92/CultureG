package com.example.ghassen.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ghassen.myapplication.slider.PrefManager;

import static com.example.ghassen.myapplication.R.id.backBid2;
import static com.example.ghassen.myapplication.R.id.resetB;
import static com.example.ghassen.myapplication.R.id.sonCheckBox;

/**
 * Created by Guesmi on 28/07/2016.
 */

public class ParamActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox sonCheck;
    private PrefManager prefManager;
    private ImageButton backB;
    private ImageButton resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ParamActivity.this.overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide() ;
        setContentView(R.layout.param_layout);
        sonCheck=(CheckBox) findViewById(sonCheckBox);
        backB=(ImageButton) findViewById(backBid2);
        resetButton=(ImageButton) findViewById(resetB);
        sonCheck.setOnClickListener(this);
        backB.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        prefManager = new PrefManager(this);
        if(prefManager.isSoundActive()) sonCheck.setChecked(true);
        else sonCheck.setChecked(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case sonCheckBox:
               // startActivity(new Intent(ParamActivity.this,MainActivity.class));
                if(sonCheck.isChecked()) prefManager.setActiveSound(true);
                else prefManager.setActiveSound(false);
               break;
            case backBid2:
                finish();
                break;
            case resetB:
                prefManager.setCurrentLevel(1);
                prefManager.setScore(0);
                prefManager.setCurrentLevelGeo(0);
                prefManager.setCurrentLevelNature(0);
                prefManager.setCurrentLevelHistoire(0);
                prefManager.setCurrentLevelCulture(0);

                Toast.makeText(ParamActivity.this,"réinitialisation effectuée",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
