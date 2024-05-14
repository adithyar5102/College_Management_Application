package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivitySignInBinding;
import com.example.college_management_application.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_Sign_up extends AppCompatActivity {
   ActivitySignUpBinding binding;
   All_Data dclass = new All_Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Sign_up.this, Activity_Sign_in.class);
                startActivity(intent);
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("check1","1");
                signup();
            }
        });
    }

    private void signup(){
        String password = binding.signupPassword.getText().toString();
        String cpassword = binding.signupConfirmPassword.getText().toString();
        if(!password.equals(cpassword)){
            Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
            return;
        }
        String username = binding.signupName.getText().toString().toUpperCase();
        int yop = Integer.parseInt(binding.signupYOP.getText().toString());

        try {
            signupuser(new UserAuth(username, password),yop);
        }catch (Exception e){
            Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void signupuser(UserAuth user,int YOP){
        DatabaseReference mdatabase = dclass.mdatabase;
        /*
        mdatabase.child(Integer.toString(YOP)+"_Students").child(user.getUsername()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                Log.d("check1","3");
                All_Data dobj = new All_Data();
                    mdatabase.child(Integer.toString(YOP)+"_Student_Auth").child(user.getUsername()).setValue(user);
                    dobj.User_name = user.getUsername();
                    dobj.YOP = YOP;
                    Log.d("check1","4");
                    Intent intent = new Intent(Activity_Sign_up.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }
        });
        Toast.makeText(this, "Details Not Found, Enter Valid Details", Toast.LENGTH_SHORT).show();

         */

        mdatabase.child(Integer.toString(YOP)+"_Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()){
                    if(snap.getValue(Student.class).getUsn().equals(user.getUsername())){
                        mdatabase.child(Integer.toString(YOP)+"_Student_Auth").child(user.getUsername()).setValue(user);
                        SharedPrefManager.getInstance(Activity_Sign_up.this).saveUsername(user.getUsername());
                        SharedPrefManager.getInstance(Activity_Sign_up.this).saveYear(YOP);
                        Intent intent = new Intent(Activity_Sign_up.this, MainActivity.class);
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(Activity_Sign_up.this, "Details Not Found, Enter Valid Details", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_Sign_up.this, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onBackPressed() {
    }
}