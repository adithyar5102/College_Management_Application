package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityFacultyHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Activity_Faculty_home extends AppCompatActivity {
    String branch ;
    String sec ;
    int yop,sem;
    All_Data obj = new All_Data();
    Spinner spinner;
    int subnum;

    ActivityFacultyHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        spinner = findViewById(R.id.spinner);
        binding.facultyHomeAttNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    branch = binding.facultyHomeAttBranch.getText().toString().toUpperCase();
                    sec = binding.facultyHomeAttSection.getText().toString().toUpperCase();
                    yop = Integer.parseInt(binding.facultyHomeAttYearOfPassing.getText().toString());
                    sem = Integer.parseInt(binding.facultyHomeAttSem.getText().toString());
                }catch (Exception e){
                    Toast.makeText(Activity_Faculty_home.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                    return;
                }

                getSubjects(branch,sem);
            }
        });

        binding.facultyHomeAttHostelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Faculty_home.this, Activity_Faculty_Hostel_Entry.class);
                startActivity(intent);

            }
        });

        binding.facultyHomeAttMarkattButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity_Faculty_home.this,Activity_Faculty_Attendance.class);
                intent.putExtra("branch",branch);
                intent.putExtra("sec",sec);
                intent.putExtra("yop",yop);
                intent.putExtra("subnum",subnum);
                startActivity(intent);
            }
        });
    }

    private void getSubjects(String branch,int sem) {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Subjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren()){
                    Subjects cur = snap.getValue(Subjects.class);
                    if(cur.getSem() == sem && cur.getBranch().equals(branch)){
                        obj.subjects = cur;
                        try{
                            display_dropdown(cur);
                        }catch (Exception e){
                            Toast.makeText(Activity_Faculty_home.this, "Error occurred try again later", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Faculty_home.this, "Couldn't connect to database", Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity_Faculty_home.this, "Try after some time", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void display_dropdown(Subjects subject) {
        String[] items = new String[10];
        items[0] = subject.getSub1();
        items[1] = subject.getSub2();
        items[2] = subject.getSub3();
        items[3] = subject.getSub4();
        items[4] = subject.getSub5();
        items[5] = subject.getSub6();
        items[6] = subject.getSub7();
        items[7] = subject.getSub8();
        items[8] = subject.getSub9();
        items[9] = subject.getSub10();
        String[] subn = new String[]{"sub1", "sub2", "sub3", "sub4", "sub5", "sub6", "sub7", "sub8", "sub9", "sub10"};

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

// Set the click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position is the index of the selected item
                subnum = position;
                Toast.makeText(getApplicationContext(), "Selected item index: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }
}