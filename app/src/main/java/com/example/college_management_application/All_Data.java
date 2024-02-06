package com.example.college_management_application;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class All_Data {
    protected ArrayList<Posts> Posts_list = new ArrayList<Posts>();
    protected  Student User_details;
    protected String User_name;
    protected int YOP;
    protected ArrayList<PlacementMail> Mail_list = new ArrayList<PlacementMail>();
    protected Marks marks;
    protected  Subjects subjects;
    protected  Attendance student_attendance;
    protected ArrayList<Attendance> Section_Attendance_List = new ArrayList<Attendance>();
    protected ArrayList<Drawable> post_images=new ArrayList<>();
}
