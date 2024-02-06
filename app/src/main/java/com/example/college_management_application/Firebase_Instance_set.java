package com.example.college_management_application;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Firebase_Instance_set {
     private DatabaseReference mdatabase;
     private String Result;
     public void set_Student(ArrayList<Student> students_list,int YOP){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         for(Student current:students_list){
             mdatabase.child(Integer.toString(YOP)+"_Students").child(current.getUsn()).setValue(current);
         }
     }

     public String set_Student_Auth(UserAuth user,int YOP){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         mdatabase.child(Integer.toString(YOP)+"_Students").child(user.getUsername()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DataSnapshot> task) {
                 Firebase_Instance_set obj = new Firebase_Instance_set();
                 All_Data dobj = new All_Data();
                 if (task.isSuccessful()){
                     mdatabase.child("Student_Auth").child(user.getUsername()).setValue(user);
                     dobj.User_name = user.getUsername();
                     dobj.YOP = YOP;
                     obj.Result = "Success";
                 }
                 else{
                     obj.Result = "Failed";
                 }
             }
         });
         return Result;
     }

     public void set_Faculty_Auth(UserAuth user){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         mdatabase.child("Faculty_Auth").child(user.getUsername()).setValue(user);
    }

    public void set_Admin_Auth(UserAuth user){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("Admin_Auth").child(user.getUsername()).setValue(user);
    }

    public void set_posts(Posts post){
         mdatabase = FirebaseDatabase.getInstance().getReference("Posts");
         mdatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Posts value = snapshot.getValue(Posts.class);
                 assert value != null;
                 int new_id = Integer.parseInt(value.getPostId().split("_")[1]);
                 post.setPostId("post_"+Integer.toString(new_id-1));
                 mdatabase.child("Posts").child(post.getPostId()).setValue(post);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Log.d("Error","In adding post data to firebase method ==> set_posts");
             }
         });
    }


    public void set_marks(ArrayList<Marks> markslist,int YOP){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         for(Marks m : markslist){
             mdatabase.child(Integer.toString(YOP)+"_Marks_list").child(m.getUsn()).setValue(m);
         }
    }

    public void set_Placement_mail(PlacementMail mail){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         mdatabase.child("Placement_mail").child(mail.getMailId()).setValue(mail);
    }

    public void set_Subjects(Subjects subject){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("Subjects").child(subject.getSubjectId()).setValue(subject);
    }

    public void set_Attendance_Table(int YOP,String Branch,String Section,ArrayList<Attendance> list){
         mdatabase = FirebaseDatabase.getInstance().getReference();
         for(Attendance student : list){
             mdatabase.child(Integer.toString(YOP)+"_"+Branch+"_"+Section).child(student.getUsn()).setValue(student);
         }
         Attendance tot = new Attendance("Total_Class",0,0,0,0,0,0,0,0,0,0);
         mdatabase.child(Integer.toString(YOP)+"_"+Branch+"_"+Section).child("Total_Class").setValue(tot);

    }

    public void set_Subject_Attendance(int YOP,String Branch,String Section, ArrayList<Attendance> Updated_List){
        mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_"+Branch+"_"+Section);
        for(Attendance student : Updated_List){
            mdatabase.child(student.getUsn()).setValue(student);
        }
    }

    public void edit_email_n_phone(String Email,String Phone,int YOP,String USN){
         mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_Students");
         mdatabase.child(USN).child("Email").setValue(Email);
         mdatabase.child(USN).child("Phone_number").setValue(Phone);
         Firebase_Instance_get obj = new Firebase_Instance_get();
         obj.get_Student_details(YOP,USN);
    }

    public void upload_image(String Image_name, Uri imageuri, Activity context){
        StorageReference storagereference = FirebaseStorage.getInstance().getReference("Posts_image"+Image_name);
        storagereference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(context, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                Log.d("Flow","Image upload successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Flow","Image upload unsuccessful");
            }
        });
    }

    // Create forms
    // Hostel
}

