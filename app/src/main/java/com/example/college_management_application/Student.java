package com.example.college_management_application;

public class Student {
    private String usn;
    private String firstName;
    private String midName;
    private String lastName;
    private int YOP;
    private String branch;
    private String Phone_number;
    private String Email;
    private int sem;

    private String sec;
    // Empty constructor
    public Student() {
    }

    // Constructor with usn, firstName, midName, lastName, YOP, and branch
    public Student(String usn, String firstName, String midName, String lastName, int YOP, String branch, String Phone_number,String Email ,int sem,String sec) {
        this.usn = usn;
        this.firstName = firstName;
        this.midName = midName;
        this.lastName = lastName;
        this.YOP = YOP;
        this.branch = branch;
        this.sem = sem;
        this.Phone_number = Phone_number;
        this.Email = Email;
        this.sec = sec;
    }

    // Getter and setter methods for usn, firstName, midName, lastName, YOP, and branch
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYOP() {
        return YOP;
    }

    public void setYOP(int YOP) {
        this.YOP = YOP;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }
}

