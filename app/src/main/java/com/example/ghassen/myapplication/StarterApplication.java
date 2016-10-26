package com.example.ghassen.myapplication;

import android.app.Application;
import android.util.Log;

import java.util.List;

/**
 * Created by Guesmi on 22/06/2016.
 */
public class StarterApplication extends Application {

    public void onCreate() {
        super.onCreate();
        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ..");
        db.addQuiz(new Quiz("nature", "Le printemps commence dans le mois de?", "MARS"));
        db.addQuiz(new Quiz("nature", "Je suis un fruit d'été de couleur rouge?", "fraise"));
        db.addQuiz(new Quiz("histoire","Comment s'appelle la tour qui devait atteindre le ciel et que construisaient les hommes qui parlaient encore tous la même langue?","Eiffel"));
        db.addQuiz(new Quiz("histoire","Quel pays a gagné la Coupe du monde de football 2010?","Espagne"));
        db.addQuiz(new Quiz("culture","Quelle est la langue maternelle la plus parlée sur la planète? ","chinois"));
        db.addQuiz(new Quiz("culture","Les Beatles était un groupe de ? ","Rock"));
        db.addQuiz(new Quiz("géographie","Quel pays a Vienne pour capitale?","Autriche"));
        db.addQuiz(new Quiz("géographie","Quel pays a Zagrebne pour capitale?","Croatia"));
		




        Log.d("Reading: ", "Reading all quizs.."); //just pour tester
        List<Quiz> quizs = db.getAllQuizs();

        for (Quiz q : quizs) {
            String log = "Id: " + q.getId() + " ,Cat: " + q.getCategorie() + " ,Quest: " + q.getQuestion()
                    + " ,reponse: " + q.getReponse();
            Log.d("Name: ", log);

        }
    }
}
