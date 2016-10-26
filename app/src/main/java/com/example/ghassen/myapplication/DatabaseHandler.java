package com.example.ghassen.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guesmi on 22/06/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "quizsManager";

    private static final String TABLE_QUIZS = "quizs";

    //  Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORIE = "categorie";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_REPONSE = "reponse";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUIZS_TABLE = "CREATE TABLE " + TABLE_QUIZS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_CATEGORIE + " TEXT," +
                KEY_QUESTION + " TEXT," +
                KEY_REPONSE + " TEXT " +
                ")";
        db.execSQL(CREATE_QUIZS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZS);

        // Create tables again
        onCreate(db);
    }
    //CRUD
    public void addQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORIE, quiz.getCategorie());
        values.put(KEY_QUESTION, quiz.getQuestion());
        values.put(KEY_REPONSE, quiz.getReponse());

        db.insert(TABLE_QUIZS, null, values);
        db.close();
    }

    public Quiz getQuiz(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUIZS, new String[] { KEY_ID,
                        KEY_CATEGORIE,KEY_QUESTION, KEY_REPONSE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Quiz quiz = new Quiz(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return quiz;
    }

    public List<Quiz> getAllQuizs() {
        List<Quiz> quizlist = new ArrayList<Quiz>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUIZS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Quiz quiz = new Quiz();
                quiz.setId(Integer.parseInt(cursor.getString(0)));
                quiz.setCategorie(cursor.getString(1));
                quiz.setQuestion(cursor.getString(2));
                quiz.setReponse(cursor.getString(3));

                quizlist.add(quiz);
            } while (cursor.moveToNext());
        }

        return quizlist;
    }
}