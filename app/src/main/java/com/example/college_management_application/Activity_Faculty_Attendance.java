package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Faculty_Attendance extends AppCompatActivity {
    String branch,sec;
    int subnum;
    int yop;
    Button submit;
    All_Data obj = new All_Data();
    ArrayList<Section_Attendance> students = new ArrayList<>();
    RecyclerView recyclerView;
    protected ArrayList<Attendance> Section_Attendance_List = new ArrayList<Attendance>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_attendance);
        submit = findViewById(R.id.faculty_set_attendance_button);
        Intent intent = getIntent();
        branch = intent.getStringExtra("branch");
        sec = intent.getStringExtra("sec");
        yop = intent.getIntExtra("yop",0);
        subnum = intent.getIntExtra("subnum",-1);
        subnum+=1;
        recyclerView = findViewById(R.id.faculty_rv_attendance_view);

        get_addtendance(branch,sec,yop);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_attendance();
                return;
            }
        });
    }

    private void update_attendance() {
        DatabaseReference mdatabase = obj.mdatabase;
        for(Section_Attendance curstd : students){
            int value;
            if(curstd.isPresent()){
                value = curstd.get_classcount()+1;
            }else{
                value = curstd.get_classcount();
            }
            mdatabase.child(Integer.toString(yop)+"_"+branch+"_"+sec).child(curstd.getName()).child("sub"+subnum).setValue(value);
        }
        Intent intent = new Intent(Activity_Faculty_Attendance.this,Activity_Faculty_home.class);
        startActivity(intent);
        Toast.makeText(Activity_Faculty_Attendance.this, "Attendance Submitted", Toast.LENGTH_SHORT).show();
    }

    private void get_addtendance(String branch, String sec, int yop) {

        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child(Integer.toString(yop)+"_"+branch+"_"+sec).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()){
                    Attendance cur = snap.getValue(Attendance.class);
                    Section_Attendance_List.add(cur);
                }
                get_subjects(Section_Attendance_List);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Faculty_Attendance.this, "Couldn't retrieve the attendance list try again later", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
    private void get_subjects(ArrayList<Attendance> Section_Attendance_List){
        for(Attendance cur:Section_Attendance_List){
            int subatt = cur.get_sub_attendance(subnum);
            String usn = cur.getUsn();
            Log.d("check1", String.valueOf(subatt));
            Log.d("check1",usn);
            students.add(new Section_Attendance(usn,subatt));
        }
        try{
            start_rv();
        }catch (Exception e){
            Log.d("Check1",e.toString());
        }
    }
    private void start_rv(){
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Faculty_Attendance.this));
        recyclerView.setHasFixedSize(true);
        StudentAttendanceAdapter adapter = new StudentAttendanceAdapter(students);
        recyclerView.setAdapter(adapter);

    }
}