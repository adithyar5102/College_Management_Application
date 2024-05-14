package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityValidateCertificateBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Activity_validate_certificate extends AppCompatActivity {
    All_Data obj = new All_Data();
    String data;
    TextView textView;
    EditText editText;
    Button button;
    ActivityValidateCertificateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityValidateCertificateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.validateCertificateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    data = binding.validateCertificateUsn.getText().toString();
                }catch (Exception e){
                    Toast.makeText(Activity_validate_certificate.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                }
                get_marks(data.split("/"));
            }
        });
        button = binding.validateCertificateBtn;
        textView = binding.validateCertificateText;
        editText = binding.validateCertificateUsn;

    }
    String usn,yop,sem;
    Marks stdmarks;
    private void get_marks(String[] split) {

        try{
            usn = split[0];
            yop = split[1];
            sem = split[2];
        }catch(IndexOutOfBoundsException e){
            Toast.makeText(this, "Enter all the details", Toast.LENGTH_SHORT).show();
        }
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child(yop+"_Marks_list").child(usn).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stdmarks = snapshot.getValue(Marks.class);
                showresult(stdmarks,sem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    float marks;
    private void showresult(Marks stdmarks, String sem) {
        switch (sem){
            case "1":
                marks = stdmarks.getSem1();
                break;
            case "2":
                marks = stdmarks.getSem2();
                break;
            case "3":
                marks = stdmarks.getSem3();
                break;
            case "4":
                marks = stdmarks.getSem4();
                break;
            case "5":
                marks = stdmarks.getSem5();
                break;
            case "6":
                marks = stdmarks.getSem6();
                break;

            case "7":
                marks = stdmarks.getSem7();
                break;

            case "8":
                marks = stdmarks.getSem8();
                break;
            default:
                Toast.makeText(Activity_validate_certificate.this, "Enter valid semester", Toast.LENGTH_SHORT).show();
                return;
        }
        button.setActivated(false);
        editText.setActivated(false);
        if(marks != 0.0){
            textView.setText(usn+" Scored a percentage of "+marks);
        }else{
            textView.setText("Results not declared or updated");
        }
    }
}