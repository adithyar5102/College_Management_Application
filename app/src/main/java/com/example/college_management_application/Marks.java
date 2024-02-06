package com.example.college_management_application;

public class Marks {
    private String usn;
    private int sem1;
    private int sem2;
    private int sem3;
    private int sem4;
    private int sem5;
    private int sem6;
    private int sem7;
    private int sem8;

    // Empty constructor
    public Marks() {
    }

    // Constructor with usn and marks for eight semesters
    public Marks(String usn, int sem1, int sem2, int sem3, int sem4, int sem5, int sem6, int sem7, int sem8) {
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

    public int getSem1() {
        return sem1;
    }

    public void setSem1(int sem1) {
        this.sem1 = sem1;
    }

    public int getSem2() {
        return sem2;
    }

    public void setSem2(int sem2) {
        this.sem2 = sem2;
    }

    public int getSem3() {
        return sem3;
    }

    public void setSem3(int sem3) {
        this.sem3 = sem3;
    }

    public int getSem4() {
        return sem4;
    }

    public void setSem4(int sem4) {
        this.sem4 = sem4;
    }

    public int getSem5() {
        return sem5;
    }

    public void setSem5(int sem5) {
        this.sem5 = sem5;
    }

    public int getSem6() {
        return sem6;
    }

    public void setSem6(int sem6) {
        this.sem6 = sem6;
    }

    public int getSem7() {
        return sem7;
    }

    public void setSem7(int sem7) {
        this.sem7 = sem7;
    }

    public int getSem8() {
        return sem8;
    }

    public void setSem8(int sem8) {
        this.sem8 = sem8;
    }
}

