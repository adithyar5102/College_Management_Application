package com.example.college_management_application;

public class Subjects {
    private String subjectId;
    private String branch;
    private int sem;
    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String sub6;
    private String sub7;
    private String sub8;
    private String sub9;
    private String sub10;

    // Empty constructor
    public Subjects() {
    }

    // Constructor with subjectId, branch, sem, and subjects from sub1 to sub10
    public Subjects(String subjectId, String branch, int sem, String sub1, String sub2, String sub3, String sub4, String sub5, String sub6, String sub7, String sub8, String sub9, String sub10) {
        this.subjectId = subjectId;
        this.branch = branch;
        this.sem = sem;
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

    // Getter and setter methods for subjectId, branch, sem, and subjects from sub1 to sub10
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }

    public String getSub4() {
        return sub4;
    }

    public void setSub4(String sub4) {
        this.sub4 = sub4;
    }

    public String getSub5() {
        return sub5;
    }

    public void setSub5(String sub5) {
        this.sub5 = sub5;
    }

    public String getSub6() {
        return sub6;
    }

    public void setSub6(String sub6) {
        this.sub6 = sub6;
    }

    public String getSub7() {
        return sub7;
    }

    public void setSub7(String sub7) {
        this.sub7 = sub7;
    }

    public String getSub8() {
        return sub8;
    }

    public void setSub8(String sub8) {
        this.sub8 = sub8;
    }

    public String getSub9() {
        return sub9;
    }

    public void setSub9(String sub9) {
        this.sub9 = sub9;
    }

    public String getSub10() {
        return sub10;
    }

    public void setSub10(String sub10) {
        this.sub10 = sub10;
    }
}
