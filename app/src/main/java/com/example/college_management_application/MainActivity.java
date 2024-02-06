 package com.example.college_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Firebase_Instance_set obj_set = new Firebase_Instance_set();
        ArrayList<Student> std = new ArrayList<>();
        std.add(new Student("R20EK001","Ganesh","","K",2024,"CIT"));
        obj_set.set_Student(std,2024);
        obj_set.set_Admin_Auth(new UserAuth("Admin@001","QWE"));
        obj_set.set_Faculty_Auth(new UserAuth("Faculty@001","QWE"));
        String res = obj_set.set_Student_Auth(new UserAuth("R20EK001","QWE"),2024);
        ArrayList<Marks> mark = new ArrayList<>();
        mark.add(new Marks("R20EK001",80,82,88,93,42,0,0,0));
        obj_set.set_marks(mark,2024);
        Toast.makeText(this, "End", Toast.LENGTH_SHORT).show();

         */
    }

}
