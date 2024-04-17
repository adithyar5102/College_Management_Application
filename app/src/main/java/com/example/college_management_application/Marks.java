package com.example.college_management_application;

public class Marks {
    private String usn;
    private float sem1;
    private float sem2;
    private float sem3;
    private float sem4;
    private float sem5;
    private float sem6;
    private float sem7;
    private float sem8;

    // Empty constructor
    public Marks() {
    }

    // Constructor with usn and marks for eight semesters
    public Marks(String usn, float sem1, float sem2, float sem3, float sem4, float sem5, float sem6, float sem7, float sem8) {
        this.usn = usn;
        this.sem1 = sem1;
        this.sem2 = sem2;
        this.sem3 = sem3;
        this.sem4 = sem4;
        this.sem5 = sem5;
        this.sem6 = sem6;
        this.sem7 = sem7;
        this.sem8 = sem8;
    }

    // Getter and setter methods for usn and marks for eight semesters
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public float getSem1() {
        return sem1;
    }

    public void setSem1(float sem1) {
        this.sem1 = sem1;
    }

    public float getSem2() {
        return sem2;
    }

    public void setSem2(float sem2) {
        this.sem2 = sem2;
    }

    public float getSem3() {
        return sem3;
    }

    public void setSem3(float sem3) {
        this.sem3 = sem3;
    }

    public float getSem4() {
        return sem4;
    }

    public void setSem4(float sem4) {
        this.sem4 = sem4;
    }

    public float getSem5() {
        return sem5;
    }

    public void setSem5(float sem5) {
        this.sem5 = sem5;
    }

    public float getSem6() {
        return sem6;
    }

    public void setSem6(float sem6) {
        this.sem6 = sem6;
    }

    public float getSem7() {
        return sem7;
    }

    public void setSem7(float sem7) {
        this.sem7 = sem7;
    }

    public float getSem8() {
        return sem8;
    }

    public void setSem8(float sem8) {
        this.sem8 = sem8;
    }
}

