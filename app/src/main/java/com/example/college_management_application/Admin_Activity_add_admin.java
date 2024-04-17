package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityAdminAddAdminBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Activity_add_admin extends AppCompatActivity {

    All_Data obj = new All_Data();
    DatabaseReference mdatabase = obj.mdatabase;
    ActivityAdminAddAdminBinding binding;
    UserAuth newuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminAddAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.adminAddaccessAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String usern = binding.adminAddaccessSigninUsn.getText().toString();
                    String pass = binding.adminAddaccessSigninPassword.getText().toString();
                    newuser = new UserAuth(usern,pass);
                }catch(Exception e){
                    Toast.makeText(Admin_Activity_add_admin.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newuser.getUsername().startsWith("Admin@")){
                    set_Admin_Auth(newuser);
                }
                Toast.makeText(Admin_Activity_add_admin.this, "Username should start with Admin@", Toast.LENGTH_SHORT).show();
            }
        });

        binding.adminAddaccessFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String usern = binding.adminAddaccessSigninUsn.getText().toString();
                    String pass = binding.adminAddaccessSigninPassword.getText().toString();
                    newuser = new UserAuth(usern,pass);
                }catch(Exception e){
                    Toast.makeText(Admin_Activity_add_admin.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(newuser.getUsername().startsWith("Faculty@")){
                    set_Faculty_Auth(newuser);
                }
                else{
                    Toast.makeText(Admin_Activity_add_admin.this, "Username should start with Faculty@", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void set_Admin_Auth(UserAuth user){
        mdatabase.child("Admin_Auth").child(user.getUsername()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Admin_Activity_add_admin.this, "Admin access access successfully", Toast.LENGTH_SHORT).show();
                binding.adminAddaccessSigninPassword.setText("");
                binding.adminAddaccessSigninUsn.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Activity_add_admin.this, "Couldn't add the admin", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void set_Faculty_Auth(UserAuth user){
        mdatabase.child("Faculty_Auth").child(user.getUsername()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Admin_Activity_add_admin.this, "Faculty access access successfully", Toast.LENGTH_SHORT).show();
                binding.adminAddaccessSigninPassword.setText("");
                binding.adminAddaccessSigninUsn.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Activity_add_admin.this, "Couldn't add the faculty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}