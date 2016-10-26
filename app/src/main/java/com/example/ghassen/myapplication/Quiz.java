package com.example.ghassen.myapplication;

/**
 * Created by Guesmi on 10/06/2016.
 */
public class Quiz {

    int id;
    String categorie,question,reponse;

    public Quiz() {
    }

    public Quiz(int id, String categorie, String question, String reponse) {
        this.id=id;
        this.categorie = categorie;
        this.question = question;
        this.reponse = reponse;
    }

    public Quiz(String categorie, String question, String reponse) {
        this.categorie = categorie;
        this.question = question;
        this.reponse = reponse;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public  int getId() {
        return id;
    }

    public  void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
