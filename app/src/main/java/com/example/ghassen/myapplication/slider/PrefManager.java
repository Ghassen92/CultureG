package com.example.ghassen.myapplication.slider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Guesmi on 14/08/2016.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "guesmi-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_SOUND_ACTIVE = "IsSoundActive";
    private static final String CURRENT_LEVEL = "currentLevel";
    private static final String SCORE = "score";
    private static final String CURRENT_LEVEL_CULTURE = "currentLevelCulture";
    private static final String CURRENT_LEVEL_HISTOIRE = "currentLevelHistoire";
    private static final String CURRENT_LEVEL_NATURE = "currentLevelNATURE";
    private static final String CURRENT_LEVEL_GEO = "currentLevelGEO";



    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setActiveSound(boolean isSoundActive) {
        editor.putBoolean(IS_SOUND_ACTIVE, isSoundActive);
        editor.commit();
    }

    public boolean isSoundActive() {
        return pref.getBoolean(IS_SOUND_ACTIVE, true);
    }

    public void setCurrentLevel(int currentLevel) {
        editor.putInt(CURRENT_LEVEL, currentLevel);
        editor.commit();
    }

    public int getCurrentLevel() {
        return pref.getInt(CURRENT_LEVEL,0);
    }

    public void setScore(int score) {
        editor.putInt(SCORE, score);
        editor.commit();
    }

    public int getScore() {
        return pref.getInt(SCORE,0);
    }

    public void setCurrentLevelCulture(int i) {
        editor.putInt(CURRENT_LEVEL_CULTURE, i);
        editor.commit();
    }

    public int getCurrentLevelCulture() {
        return pref.getInt(CURRENT_LEVEL_CULTURE,0);
    }

    public void setCurrentLevelNature(int i) {
        editor.putInt(CURRENT_LEVEL_NATURE, i);
        editor.commit();
    }

    public int getCurrentLevelNature() {
        return pref.getInt(CURRENT_LEVEL_NATURE,0);
    }

    public void setCurrentLevelGeo(int i) {
        editor.putInt(CURRENT_LEVEL_GEO, i);
        editor.commit();
    }

    public int getCurrentLevelGeo() {
        return pref.getInt(CURRENT_LEVEL_GEO,0);
    }

    public void setCurrentLevelHistoire(int i) {
        editor.putInt(CURRENT_LEVEL_HISTOIRE, i);
        editor.commit();
    }

    public int getCurrentLevelHistoire() {
        Log.d("prefffff",pref.getInt(CURRENT_LEVEL_HISTOIRE,0)+" ");
        return pref.getInt(CURRENT_LEVEL_HISTOIRE,0);
    }



}