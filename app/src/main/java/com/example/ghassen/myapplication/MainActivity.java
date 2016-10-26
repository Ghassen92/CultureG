package com.example.ghassen.myapplication;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghassen.myapplication.slider.PrefManager;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView[] alpahabet,reponsesTv;
    private TextView quizzt ,scoreT,categorieT,levelT,deleteB,showB;
    private ImageButton backB;
    private int [] reponseIndice, indiceOccupé,indiceVisibleAlphabet=new int [14]; // pour travailler à l'envers
    private int reponseLength,quizzID;
    private String reponse,question,categorie;
    private LinearLayout reponseLayout;
    private int count=0,score=0,currentLevel=0,help=2;
    private List<Quiz> quizs ;
    private boolean stop=false;
    /*private Quiz[] quizs ={
            new Quiz(1,"physics","where does sound travel faster ?","WATER"),
            new Quiz(2,"physics","an object in space that has an icy core with a tail of gas and dust that extends millions of miles. What is this ?","COMET"),
            new Quiz(3,"history","Following World War II, an airlift took place in which European city? ","BERLIN"),
            new Quiz(4,"culture","What animal group do bees belong to? ","APOIDEA")
    };*/
    PrefManager prefManager;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getSupportActionBar().hide() ;
        setContentView(R.layout.activity_main);
        attachActivitytoUI();
        quizs=filtrerQuizz(new DatabaseHandler(this).getAllQuizs(),getIntent().getExtras().getString("categorie"));
        prefManager = new PrefManager(this);
        switch (getIntent().getExtras().getString("categorie")){
            case "nature": currentLevel=prefManager.getCurrentLevelNature();break;
            case "histoire": currentLevel=prefManager.getCurrentLevelHistoire();break;
            case "culture": currentLevel=prefManager.getCurrentLevelCulture();break;
            case "géographie": currentLevel=prefManager.getCurrentLevelGeo();break;

        }
        //currentLevel=prefManager.getCurrentLevel();
        score=prefManager.getScore();
        if(!stop) preparerUI(quizs.get(currentLevel));


    }

    private List<Quiz> filtrerQuizz(List<Quiz> allQuizs, String categorie) {
        List<Quiz> temp=new ArrayList<Quiz>();
        for (int i=0;i<allQuizs.size();i++)
            if(allQuizs.get(i).getCategorie().equals(categorie)) temp.add(allQuizs.get(i));
        return temp;
    }

    private void attachActivitytoUI() {
        reponseLayout=(LinearLayout) findViewById(R.id.reponseL);
        quizzt=(TextView) findViewById(R.id.quizzT);
        scoreT=(TextView) findViewById(R.id.scoreT);
        categorieT=(TextView) findViewById(R.id.categorieT);
        levelT=(TextView) findViewById(R.id.levelT);
        backB=(ImageButton) findViewById(R.id.backBid);
        deleteB=(TextView)findViewById(R.id.deleteB);
        showB=(TextView) findViewById(R.id.showB);
        alpahabet=new TextView[14];
        alpahabet[0]=(TextView) findViewById(R.id.c1);
        alpahabet[1]=(TextView) findViewById(R.id.c2);
        alpahabet[2]=(TextView) findViewById(R.id.c3);
        alpahabet[3]=(TextView) findViewById(R.id.c4);
        alpahabet[4]=(TextView) findViewById(R.id.c5);
        alpahabet[5]=(TextView) findViewById(R.id.c6);
        alpahabet[6]=(TextView) findViewById(R.id.c7);
        alpahabet[7]=(TextView) findViewById(R.id.c8);
        alpahabet[8]=(TextView) findViewById(R.id.c9);
        alpahabet[9]=(TextView) findViewById(R.id.c10);
        alpahabet[10]=(TextView) findViewById(R.id.c11);
        alpahabet[11]=(TextView) findViewById(R.id.c12);
        alpahabet[12]=(TextView) findViewById(R.id.c13);
        alpahabet[13]=(TextView) findViewById(R.id.c14);
        for (int i=0;i<14;i++) alpahabet[i].setOnClickListener(this);
        backB.setOnClickListener(this);
        deleteB.setOnClickListener(this);
        showB.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.backBid){
          finish();
        }


        if(premiereCaseVide()!=-1){
            if(v.getId()==R.id.deleteB){
                if(help==00) {
                    Toast.makeText(MainActivity.this,"no more help",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isStillChance(indiceOccupé)) return;
                playMedia(R.raw.bubble);
                int i=(int)(Math.random()*(14-reponseLength))+reponseLength;

                while(indiceVisibleAlphabet[indiceOccupé[i]]==-1) i=(int)(Math.random()*(14-reponseLength))+reponseLength;

                score-=10;
                //help--;
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    alpahabet[indiceOccupé[i]].setBackgroundDrawable( getResources().getDrawable(R.drawable.red_button) );
                } else {
                    alpahabet[indiceOccupé[i]].setBackground( ContextCompat.getDrawable(MainActivity.this, R.drawable.red_button));
                }
                //alpahabet[indiceOccupé[i]].setVisibility(View.INVISIBLE);
                indiceVisibleAlphabet[indiceOccupé[i]]=-1;
                alpahabet[indiceOccupé[i]].setClickable(false);
                scoreT.setText(score+"");
                Log.d("ccccDELETE",i+" "+indiceOccupé[i]+" ---- "+indiceOccupéToString()+"  ----- sc="+score);
                return;
            }
            switch (v.getId()){
                case R.id.c1 : alpahabet[0].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=0;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[0].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[0]=-1;break;
                case R.id.c2 : alpahabet[1].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=1;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[1].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[1]=-1;break;
                case R.id.c3 : alpahabet[2].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=2;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[2].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[2]=-1;break;
                case R.id.c4 : alpahabet[3].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=3;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[3].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[3]=-1;break;
                case R.id.c5 : alpahabet[4].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=4;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[4].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[4]=-1;break;
                case R.id.c6 : alpahabet[5].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=5;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[5].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[5]=-1;break;
                case R.id.c7 : alpahabet[6].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=6;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[6].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[6]=-1;break;
                case R.id.c8 : alpahabet[7].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=7;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[7].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[7]=-1;break;
                case R.id.c9 : alpahabet[8].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=8;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[8].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[8]=-1;break;
                case R.id.c10 : alpahabet[9].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=9;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[9].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[9]=-1;break;
                case R.id.c11 : alpahabet[10].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=10;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[10].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[10]=-1;break;
                case R.id.c12 : alpahabet[11].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=11;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[11].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[11]=-1;break;
                case R.id.c13 : alpahabet[12].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=12;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[12].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[12]=-1;break;
                case R.id.c14 : alpahabet[13].setVisibility(View.INVISIBLE);reponseIndice[premiereCaseVide()]=13;
                    reponsesTv[premiereCaseVide()].setText(alpahabet[13].getText());playMedia(R.raw.ding);
                    indiceVisibleAlphabet[13]=-1;break;



            }
            if((v.getId()==R.id.showB)){
                int k=premiereCaseVide();

                reponsesTv[k].setText(Character.toUpperCase(reponse.charAt(k))+"");
                if(indiceVisibleAlphabet[indiceOccupé[reponseLength-1-k]]==-1){
                       // Toast.makeText(MainActivity.this,"le caractère est déja utilisé",Toast.LENGTH_SHORT).show();
                    //return;
                }
                alpahabet[indiceOccupé[reponseLength-1-k]].setVisibility(View.INVISIBLE);
                indiceVisibleAlphabet[indiceOccupé[reponseLength-1-k]]=-1;
                reponseIndice[k]=-2;//-2 fixed
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    reponsesTv[k].setBackgroundDrawable( getResources().getDrawable(R.drawable.yellow_button) );
                } else {
                    reponsesTv[k].setBackground( ContextCompat.getDrawable(MainActivity.this, R.drawable.yellow_button));
                }

                score-=10;
                scoreT.setText(score+"");
                Log.d("ccccShowB", "ind vis "+alphaVisibleToString());
                playMedia(R.raw.bubble);

            }
        }

        if (isCorrect()) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(MainActivity.this, "true",Toast.LENGTH_LONG).show();
                    currentLevel++;
                    score+=20;
                    if(currentLevel==2) {
                        Toast.makeText(MainActivity.this, "niveau terminé",Toast.LENGTH_SHORT).show();
                       // stop=true; //si on veut arreter le loup des questions
                        currentLevel%=2;
                    };
                    playMedia(R.raw.success_sound);
                    /*preparerUI(quizs.get(currentLevel));
                    scoreT.setText(score+"");*/
                }
            }, 1000);
            return;
        }
        for(int i=0;i<reponseLength;i++){
            if (v.getId()==(i+1)&&(!reponsesTv[i].getText().equals(" "))&&(reponseIndice[i]!=-2)) {
                reponsesTv[i].setText(" ");
                alpahabet[reponseIndice[i]].setVisibility(View.VISIBLE);
                indiceVisibleAlphabet[reponseIndice[i]]=1;
                Log.d("ccccResp", "ind vis "+alphaVisibleToString());
                reponseIndice[i]=-1;
                return;
            }
        }


    }


    private boolean isStillChance(int[] indiceOccupé) {
        for(int i=reponseLength;i<indiceOccupé.length;i++){
            if (indiceVisibleAlphabet[indiceOccupé[i]]==1) return true;
        }
        Log.d("ccccchance","plein ----------->"+indiceOccupéToString());
        return false;
    }

    private boolean isCorrect() {
              for(int i=0;i<reponse.length();i++){
                  //Log.d("rrrrrr",reponsesTv[i].getText()+"  "+reponse.charAt(i));
                  if(!reponsesTv[i].getText().equals(Character.toUpperCase(reponse.charAt(i))+"")) return false;
              }

        for(int i=0;i<reponse.length();i++){
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    reponsesTv[i].setBackgroundDrawable( getResources().getDrawable(R.drawable.green_button) );
                } else {
                    reponsesTv[i].setBackground( ContextCompat.getDrawable(MainActivity.this, R.drawable.green_button));
                }
            }

        return  true;
    }

    private void preparerUI(Quiz quiz){
        question= quiz.getQuestion();
        reponseLength= quiz.getReponse().length();
        reponse = quiz.getReponse();
        categorie= quiz.getCategorie();
        quizzID= quiz.getId();
        reponsesTv =new TextView[reponseLength];
        reponseIndice=new int[reponseLength];
        help=2;
        count=0;
        reponseLayout.removeAllViewsInLayout();
        for(int i=0;i<14;i++) { alpahabet[i].setText(" ");
            alpahabet[i].setVisibility(View.VISIBLE);
            alpahabet[i].setClickable(true);
            if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                alpahabet[i].setBackgroundDrawable( getResources().getDrawable(R.drawable.round_button) );
            } else {
                alpahabet[i].setBackground( ContextCompat.getDrawable(MainActivity.this, R.drawable.round_button));
            }
            indiceVisibleAlphabet [i]=1;};

        for(int i=0;i<reponseLength;i++){
            addTextViewToLinearL();
            reponsesTv[i].setOnClickListener(MainActivity.this);
            reponseIndice[i]=-1;
        }
            quizzt.setText(question);
            categorieT.setText(categorie);
            int currentLevel_1=currentLevel+1;
            levelT.setText(currentLevel_1+"");
            scoreT.setText(score+"");
            prepareAlphabet();

    }

    private void prepareAlphabet() {
        char c;
        indiceOccupé=new int[14];
        for(int i=0;i<14;i++){
            if(i<reponseLength) {
                 c= reponse.charAt(reponseLength-i-1);
                int pos = (int) (Math.random()* 14);
                while(!alpahabet[pos].getText().equals(" "))  pos = (int) (Math.random()* 14);
                alpahabet[pos].setText(Character.toUpperCase(c)+"");
                indiceOccupé[i]=pos;
                Log.d("cccc", c+" "+ pos+"  i= "+i);

            }
            else{
                int asciiChar = (int) (Math.random()* 26+65);
                c=Character.toUpperCase((char)(asciiChar));
                int k=0;
                while(!alpahabet[k].getText().equals(" ")) k++;
                alpahabet[k].setText(c+"");
                indiceOccupé[i]=k;
                Log.d("cccc", c+" "+ k+"  i= "+i);
            }

        }
    }


    private void addTextViewToLinearL() {
        count++;
        TextView t=new TextView(MainActivity.this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(8,8,8,8);
        t.setLayoutParams(params);
        t.setGravity(Gravity.CENTER);
        t.setId(count);
        t.setText(" ");
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            t.setBackgroundDrawable( getResources().getDrawable(R.drawable.round_button) );
        } else {
            t.setBackground( ContextCompat.getDrawable(MainActivity.this, R.drawable.round_button));
        }
        t.setTextSize(25);
        t.setTextColor(Color.parseColor("#ffffff"));
        reponsesTv[count-1]=t;
        reponseLayout.addView(t);

    }

    private int premiereCaseVide(){
        for(int i=0;i<reponseLength;i++){
            if (reponsesTv[i].getText().equals(" ")) return i;
        }
        return -1;
    }
    String indiceOccupéToString(){
        String ch="";
        for(int i=0;i<indiceOccupé.length;i++) ch+=indiceOccupé[i]+" ";
        return  ch;
    }
    String alphaVisibleToString(){
        String ch="";
        for(int i=0;i<indiceVisibleAlphabet.length;i++) ch+=indiceVisibleAlphabet[i]+" ";
        return  ch;
    }

    void playMedia(int soundID){
        if(!prefManager.isSoundActive()&&soundID==R.raw.success_sound)
            {
                if( !stop) {
                    preparerUI(quizs.get(currentLevel));
                    scoreT.setText(score + "");
                }
            }
        else if(!prefManager.isSoundActive()) return;
        if(mp!=null){
            mp.release();
            mp=null;
        }
         mp = MediaPlayer.create(this, soundID);
        final int soundId2=soundID;
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.reset();
                mp.release();
                mp=null;
                if(soundId2==R.raw.success_sound) {
                    if(!stop) preparerUI(quizs.get(currentLevel));
                    scoreT.setText(score + "");
                }
            }

        });
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // prefManager.setCurrentLevel(currentLevel);
        prefManager.setScore(score);
        switch (getIntent().getExtras().getString("categorie")){
            case "nature":   prefManager.setCurrentLevelNature(currentLevel);break;
            case "histoire": prefManager.setCurrentLevelHistoire(currentLevel);break;
            case "culture":  prefManager.setCurrentLevelCulture(currentLevel);break;
            case "géographie":  prefManager.setCurrentLevelGeo(currentLevel);break;

        }
    }
}
