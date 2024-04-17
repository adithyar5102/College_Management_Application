package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityFacultyHomeBinding;
import com.example.college_management_application.databinding.ActivitySignInBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Sign_in extends AppCompatActivity {

    ActivitySignInBinding binding;
    All_Data dclass = new All_Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signinSsignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Sign_in.this, Activity_Sign_up.class);
                startActivity(i);
            }
        });

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        binding.signInValidateCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Sign_in.this,Activity_validate_certificate.class);
                startActivity(intent);
            }
        });
    }

    private void loginuser(){
        String username = "";
        String password = "";
        int yop = 0;
        try{
            username = binding.signinUsn.getText().toString();
            password = binding.signinPassword.getText().toString();
            yop = Integer.parseInt(binding.signinYearOfPassing.getText().toString());
        }catch(Exception e){
            Toast.makeText(Activity_Sign_in.this, "Enter Valid Details", Toast.LENGTH_SHORT).show();
            Log.d("Error",e.toString());
            yop = 0;
        }
        Log.d("check1",username+password);
        try {
            String regex = "([a-z A-Z]+)@\\d+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(username);
            String category = "";
            if (m.find()) {
                category = m.group(1);
                Log.d("check1",category);

            }

            if (category.equals("Admin")) {
                Log.d("check1","admin");
                Adminlogin(new UserAuth(username, password));
            } else if (category.equals("Faculty")) {
                Facultylogin(new UserAuth(username, password));
            } else {
                if(yop == 0){
                    Toast.makeText(Activity_Sign_in.this, "Enter Valid Year", Toast.LENGTH_SHORT).show();
                    return;
                }
                Studentlogin(new UserAuth(username.toUpperCase(), password), yop);
            }
        }catch (Exception e){
            Toast.makeText(Activity_Sign_in.this, "Error occurred", Toast.LENGTH_SHORT).show();

            Log.d("Check1",e.toString());
        }
    }

    private void Studentlogin(UserAuth user,int YOP) {
        DatabaseReference mdatabase = dclass.mdatabase;

        mdatabase.child(Integer.toString(YOP)+"_Student_Auth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    UserAuth cur = snap.getValue(UserAuth.class);
                    try {
                        if (cur.getUsername().equals(user.getUsername())) {
                            Log.d("Check1", cur.getUsername());
                            if (cur.getPassword().equals(user.getPassword())) {
                                SharedPrefManager.getInstance(Activity_Sign_in.this).saveUsername(user.getUsername());
                                SharedPrefManager.getInstance(Activity_Sign_in.this).saveYear(YOP);
                                Intent i = new Intent(Activity_Sign_in.this, MainActivity.class);
                                startActivity(i);
                                return;

                            } else {
                                Toast.makeText(Activity_Sign_in.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                    } catch (Exception e) {
                        Log.d("Check1", e.toString());
                        return;
                    }
                }
                Toast.makeText(Activity_Sign_in.this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Sign_in.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Facultylogin(UserAuth user) {
        DatabaseReference mdatabase = dclass.mdatabase;
        //DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("Faculty_Auth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserAuth cur = snap.getValue(UserAuth.class);
                    try{
                        if(cur.getUsername().equals(user.getUsername())){
                            Log.d("Check1",cur.getUsername());
                            if(cur.getPassword().equals(user.getPassword())){
                                Intent intent =new Intent(Activity_Sign_in.this, Activity_Faculty_home.class);
                                startActivity(intent);
                                //Finish this
                            }
                            else{
                                Toast.makeText(Activity_Sign_in.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }catch (Exception e){
                        Log.d("Check1",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Sign_in.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Adminlogin(UserAuth user) {
        DatabaseReference mdatabase = dclass.mdatabase;
        mdatabase.child("Admin_Auth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserAuth cur = snap.getValue(UserAuth.class);
                    try{
                        if(cur.getUsername().equals(user.getUsername())){
                            Log.d("Check1",cur.getUsername());
                            if(cur.getPassword().equals(user.getPassword())){
                                Intent intent = new Intent(Activity_Sign_in.this,Admin_Activity_Home.class);
                                startActivity(intent);
                                return;
                                //Finish this

                            }
                            else{
                                Toast.makeText(Activity_Sign_in.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                    }catch (Exception e){
                        Log.d("Check1",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Sign_in.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}