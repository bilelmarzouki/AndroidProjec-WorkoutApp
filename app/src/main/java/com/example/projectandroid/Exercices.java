package com.example.projectandroid;

public class Exercices {

    private String exerciename;
    private String reps;
    private String rest1;
    private String rest2;
    private int exerciceimg;

    public Exercices(String exerciename, String reps, String rest1, String rest2, int exerciceimg) {

        this.exerciename = exerciename;
        this.reps = reps;
        this.rest1 = rest1;
        this.rest2 = rest2;
        this.exerciceimg = exerciceimg;

    }


    public String getExerciename() {
        return exerciename;
    }

    public void setExerciename(String exerciename) {
        this.exerciename = exerciename;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getRest1() {
        return rest1;
    }

    public void setRest1(String rest1) {
        this.rest1 = rest1;
    }

    public String getRest2() {
        return rest2;
    }

    public void setRest2(String rest2) {
        this.rest2 = rest2;
    }

    public int getExerciceimg() {
        return exerciceimg;
    }

    public void setExerciceimg(int exerciceimg) {
        this.exerciceimg = exerciceimg;
    }


}
