package com.example.college_management_application;

public class Student_Fees {
    private String USN;
    private String First_name;
    private String Last_name;
    private String Branch;
    private double Year1_total, Year2_total, Year3_total, Year4_total;
    private double Year1_paid, Year2_paid, Year3_paid, Year4_paid;
    public Student_Fees(){}

    // Constructor
    public Student_Fees(String USN, String First_name, String Last_name, String Branch, double Year1_total, double Year2_total, double Year3_total, double Year4_total, double Year1_paid, double Year2_paid, double Year3_paid, double Year4_paid) {
        this.USN = USN;
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.Branch = Branch;
        this.Year1_total = Year1_total;
        this.Year2_total = Year2_total;
        this.Year3_total = Year3_total;
        this.Year4_total = Year4_total;
        this.Year1_paid = Year1_paid;
        this.Year2_paid = Year2_paid;
        this.Year3_paid = Year3_paid;
        this.Year4_paid = Year4_paid;
    }

    // Getters
    public String getUSN() { return USN; }
    public String getFirst_name() { return First_name; }
    public String getLast_name() { return Last_name; }
    public String getBranch() { return Branch; }
    public double getYear1_total() { return Year1_total; }
    public double getYear2_total() { return Year2_total; }
    public double getYear3_total() { return Year3_total; }
    public double getYear4_total() { return Year4_total; }
    public double getYear1_paid() { return Year1_paid; }
    public double getYear2_paid() { return Year2_paid; }
    public double getYear3_paid() { return Year3_paid; }
    public double getYear4_paid() { return Year4_paid; }

    // Setters
    public void setUSN(String USN) { this.USN = USN; }
    public void setFirst_name(String First_name) { this.First_name = First_name; }
    public void setLast_name(String Last_name) { this.Last_name = Last_name; }
    public void setBranch(String Branch) { this.Branch = Branch; }
    public void setYear1_total(double Year1_total) { this.Year1_total = Year1_total; }
    public void setYear2_total(double Year2_total) { this.Year2_total = Year2_total; }
    public void setYear3_total(double Year3_total) { this.Year3_total = Year3_total; }
    public void setYear4_total(double Year4_total) { this.Year4_total = Year4_total; }
    public void setYear1_paid(double Year1_paid) { this.Year1_paid = Year1_paid; }
    public void setYear2_paid(double Year2_paid) { this.Year2_paid = Year2_paid; }
    public void setYear3_paid(double Year3_paid) { this.Year3_paid = Year3_paid; }
    public void setYear4_paid(double Year4_paid) { this.Year4_paid = Year4_paid; }
    public double getPaidAmount(int year) {
        switch (year) {
            case 1:
                return getYear1_paid();
            case 2:
                return getYear2_paid();
            case 3:
                return getYear3_paid();
            case 4:
                return getYear4_paid();
            default:
                throw new IllegalArgumentException("Invalid year: " + year);
        }
    }
    public void setPaidAmount(int year, double amount) {
        switch (year) {
            case 1: setYear1_paid(amount); break;
            case 2: setYear2_paid(amount); break;
            case 3: setYear3_paid(amount); break;
            case 4: setYear4_paid(amount); break;
            default: throw new IllegalArgumentException("Invalid year: " + year);
        }
    }
}
