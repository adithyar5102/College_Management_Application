package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityStudentFeesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Activity_Student_fees extends AppCompatActivity {
    All_Data obj = new All_Data();
    ActivityStudentFeesBinding binding;
    Student_Fees userfees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentFeesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int year = SharedPrefManager.getInstance(Activity_Student_fees.this).getYear();
        String username = SharedPrefManager.getInstance(Activity_Student_fees.this).getUsername();
        get_fee_details(year,username);

        binding.studentFees1PayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Student_fees.this, Payment_gateway.class);
                i.putExtra("year","year1_paid");
                i.putExtra("paid",userfees.getYear1_paid());
                i.putExtra("To pay", userfees.getYear1_total()-userfees.getYear1_paid());
                startActivity(i);
            }
        });

        binding.studentFees2PayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Student_fees.this, Payment_gateway.class);
                i.putExtra("year","year2_paid");
                i.putExtra("paid",userfees.getYear2_paid());
                i.putExtra("To pay", userfees.getYear2_total()-userfees.getYear2_paid());
                startActivity(i);
            }
        });
        binding.studentFees3PayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Student_fees.this, Payment_gateway.class);
                i.putExtra("year","year3_paid");
                i.putExtra("To pay", userfees.getYear3_total()-userfees.getYear3_paid());
                i.putExtra("paid",userfees.getYear3_paid());
                startActivity(i);
            }
        });
        binding.studentFees4PayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Student_fees.this, Payment_gateway.class);
                i.putExtra("year","year4_paid");
                i.putExtra("To pay", userfees.getYear4_total()-userfees.getYear4_paid());
                i.putExtra("paid",userfees.getYear4_paid());
                startActivity(i);
            }
        });
    }

    private void get_fee_details(int YOP,String user) {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Student_fees_details_"+YOP).child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userfees = snapshot.getValue(Student_Fees.class);
                set_fees_details(userfees);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Student_fees.this, "Couldn't load fee details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void set_fees_details(Student_Fees userfees) {
        binding.studentFees1Year.setText("First Year");
        binding.studentFees1TotalFees.setText(Double.toString(userfees.getYear1_total()));
        binding.studentFees1Paid.setText(Double.toString(userfees.getYear1_paid()));

        binding.studentFees2Year.setText("Second Year");
        binding.studentFees2TotalFees.setText(Double.toString(userfees.getYear2_total()));
        binding.studentFees2Paid.setText(Double.toString(userfees.getYear2_paid()));

        binding.studentFees3Year.setText("Third Year");
        binding.studentFees3TotalFees.setText(Double.toString(userfees.getYear3_total()));
        binding.studentFees3Paid.setText(Double.toString(userfees.getYear3_paid()));

        binding.studentFees4Year.setText("Fourth Year");
        binding.studentFees4TotalFees.setText(Double.toString(userfees.getYear4_total()));
        binding.studentFees4Paid.setText(Double.toString(userfees.getYear4_paid()));
    }
}