package com.example.college_management_application;

import android.graphics.drawable.Drawable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class All_Data {
    protected DatabaseReference mdatabase;
    protected StorageReference storageReference;
    public All_Data(){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }
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

    String sec,branch;
    protected Attendance totalclass;
}
