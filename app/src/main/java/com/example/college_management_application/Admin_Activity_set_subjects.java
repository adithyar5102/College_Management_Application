package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityAdminSetSubjectsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class Admin_Activity_set_subjects extends AppCompatActivity {

    ActivityAdminSetSubjectsBinding binding;
    int i = 1;
    String subjectid;
    int sem;
    String branch;
    TextView subnum;
    EditText subjectname;
    String[] subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSetSubjectsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        subnum = findViewById(R.id.admin_set_subject_text);
        subjectname = findViewById(R.id.admin_set_subject_get_subject);

        subject = new String[10];
        for(int e=0;e<10;e++){
            subject[e] = "";
        }
        binding.adminSetSubjectNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>10){
                    Toast.makeText(Admin_Activity_set_subjects.this, "Max limit is 10", Toast.LENGTH_SHORT).show();
                    upload(subject,subjectid,branch,sem);
                    return;
                }else{
                    subject[i-1] = subjectname.getText().toString();
                    i=i+1;
                    subjectname.setText("");
                    subnum.setText("Subject - "+ i);
                }
            }
        });

        binding.adminSetSubjectDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectid = binding.adminSetSubjectSubjectid.getText().toString();
                sem = Integer.parseInt(binding.adminSetSubjectSem.getText().toString());
                branch = binding.adminSetSubjectBranch.getText().toString().toUpperCase();

                upload(subject,subjectid,branch,sem);
            }
        });
    }

    public void upload(String[] arr,String subjectid, String branch, int sem){
        Subjects subject = new Subjects(subjectid,branch,sem,arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9]);
        All_Data obj = new All_Data();
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Subjects").child(subject.getSubjectId()).setValue(subject).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Admin_Activity_set_subjects.this, "Subjects Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Activity_set_subjects.this, "Failed to add subjects", Toast.LENGTH_SHORT).show();
            }
        });
    }
}