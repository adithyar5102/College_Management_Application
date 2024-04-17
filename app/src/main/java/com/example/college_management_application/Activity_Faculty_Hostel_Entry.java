package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityFacultyHostelEntryBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class Activity_Faculty_Hostel_Entry extends AppCompatActivity {
    EditText srnEditText, firstNameEditText, lastNameEditText, branchEditText, mobileNumberEditText, emergencyContactEditText, hostelBlockEditText, roomNumberEditText, genderEditText;

    // Declare Strings to hold the input values
    String srn, firstName, lastName, branch, mobileNumber, emergencyContact, hostelBlock, roomNumber,gender;
    ActivityFacultyHostelEntryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFacultyHostelEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        srnEditText = findViewById(R.id.faculty_hostel_add_student_srn);
        firstNameEditText = findViewById(R.id.faculty_hostel_add_student_first_name);
        lastNameEditText = findViewById(R.id.faculty_hostel_add_student_last_name);
        branchEditText = findViewById(R.id.faculty_hostel_add_student_branch);
        mobileNumberEditText = findViewById(R.id.faculty_hostel_add_student_mobile_number);
        emergencyContactEditText = findViewById(R.id.faculty_hostel_add_student_emergency_contact);
        hostelBlockEditText = findViewById(R.id.faculty_hostel_add_student_hostel_block);
        roomNumberEditText = findViewById(R.id.faculty_hostel_add_student_room_number);
        genderEditText = findViewById(R.id.faculty_hostel_add_student_gender);

        Button submitButton = findViewById(R.id.faculty_hostel_add_student_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the EditTexts
                srn = srnEditText.getText().toString();
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                branch = branchEditText.getText().toString();
                mobileNumber = mobileNumberEditText.getText().toString();
                emergencyContact = emergencyContactEditText.getText().toString();
                hostelBlock = hostelBlockEditText.getText().toString();
                roomNumber = roomNumberEditText.getText().toString();
                gender = genderEditText.getText().toString();
                Hostel_student cur = new Hostel_student(srn,firstName,lastName,branch,mobileNumber,emergencyContact,hostelBlock,roomNumber,gender);
                upload_to_firebase(cur);

                // Now you can use these variables for your needs
            }
        });
    }
    All_Data obj = new All_Data();
    private void upload_to_firebase(Hostel_student cur) {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Hostel").child(cur.getSrn()).setValue(cur).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Activity_Faculty_Hostel_Entry.this, "Student Added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity_Faculty_Hostel_Entry.this,Activity_Faculty_home.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Activity_Faculty_Hostel_Entry.this, "Try after some time", Toast.LENGTH_SHORT).show();
            }
        });
    }
}