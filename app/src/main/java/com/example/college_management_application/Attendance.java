package com.example.college_management_application;

public class Attendance {
    private String usn;
    private int sub1;
    private int sub2;
    private int sub3;
    private int sub4;
    private int sub5;
    private int sub6;
    private int sub7;
    private int sub8;
    private int sub9;
    private int sub10;

    // Empty constructor
    public Attendance() {
    }

    // Constructor with usn and attendance for subjects from sub1 to sub10
    public Attendance(String usn, int sub1, int sub2, int sub3, int sub4, int sub5, int sub6, int sub7, int sub8, int sub9, int sub10) {
        this.usn = usn;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
        this.sub5 = sub5;
        this.sub6 = sub6;
        this.sub7 = sub7;
        this.sub8 = sub8;
        this.sub9 = sub9;
        this.sub10 = sub10;
    }

    // Getter and setter methods for usn and attendance for subjects from sub1 to sub10
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public int getSub1() {
        return sub1;
    }

    public void setSub1(int sub1) {
        this.sub1 = sub1;
    }

    public int getSub2() {
        return sub2;
    }

    public void setSub2(int sub2) {
        this.sub2 = sub2;
    }

    public int getSub3() {
        return sub3;
    }

    public void setSub3(int sub3) {
        this.sub3 = sub3;
    }

    public int getSub4() {
        return sub4;
    }

    public void setSub4(int sub4) {
        this.sub4 = sub4;
    }

    public int getSub5() {
        return sub5;
    }

    public void setSub5(int sub5) {
        this.sub5 = sub5;
    }

    public int getSub6() {
        return sub6;
    }

    public void setSub6(int sub6) {
        this.sub6 = sub6;
    }

    public int getSub7() {
        return sub7;
    }

    public void setSub7(int sub7) {
        this.sub7 = sub7;
    }

    public int getSub8() {
        return sub8;
    }

    public void setSub8(int sub8) {
        this.sub8 = sub8;
    }

    public int getSub9() {
        return sub9;
    }

    public void setSub9(int sub9) {
        this.sub9 = sub9;
    }

    public int getSub10() {
        return sub10;
    }

    public void setSub10(int sub10) {
        this.sub10 = sub10;
    }
}
