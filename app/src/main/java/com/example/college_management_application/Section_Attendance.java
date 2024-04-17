package com.example.college_management_application;

public class Section_Attendance {
    private String name;
    private boolean isPresent;
    int classcount;

    public Section_Attendance(String name,int classcount) {
        this.name = name;
        this.isPresent = false;
        this.classcount = classcount;
    }

    public String getName() {
        return name;
    }
    public int get_classcount(){
        return classcount;
    }
    public void set_classcount(int count){
        this.classcount = count;
    }
    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
