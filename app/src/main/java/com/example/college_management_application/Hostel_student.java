package com.example.college_management_application;

public class Hostel_student {
    private String srn;
    private String firstName;
    private String lastName;
    private String branch;
    private String mobileNumber;
    private String emergencyContact;
    private String hostelBlock;
    private String roomNumber;
    private String gender;

    Hostel_student(){}
    public Hostel_student(String srn, String firstName, String lastName, String branch, String mobileNumber, String emergencyContact, String hostelBlock, String roomNumber,String gender) {
        this.srn = srn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.branch = branch;
        this.mobileNumber = mobileNumber;
        this.emergencyContact = emergencyContact;
        this.hostelBlock = hostelBlock;
        this.roomNumber = roomNumber;
        this.gender = gender;
    }


    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getHostelBlock() {
        return hostelBlock;
    }

    public void setHostelBlock(String hostelBlock) {
        this.hostelBlock = hostelBlock;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
