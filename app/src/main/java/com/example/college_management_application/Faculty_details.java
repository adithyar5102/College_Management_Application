package com.example.college_management_application;

public class Faculty_details {
    private String First_name;
    private String Last_name;
    private String Designation;
    private String mail;
    private String gender;
    private int age;
    private String department;

    public Faculty_details(){}
    // Constructor
    public Faculty_details(String First_name, String Last_name, String Designation, String mail, String gender, int age, String department) {
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.Designation = Designation;
        this.mail = mail;
        this.gender = gender;
        this.age = age;
        this.department = department;
    }

    // Getters
    public String getFirst_name() { return First_name; }
    public String getLast_name() { return Last_name; }
    public String getDesignation() { return Designation; }
    public String getMail() { return mail; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }

    // Setters
    public void setFirst_name(String First_name) { this.First_name = First_name; }
    public void setLast_name(String Last_name) { this.Last_name = Last_name; }
    public void setDesignation(String Designation) { this.Designation = Designation; }
    public void setMail(String mail) { this.mail = mail; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setDepartment(String department) { this.department = department; }
}
