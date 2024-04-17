package com.example.college_management_application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Firebase_Instance_get {
    private DatabaseReference mdatabase;
    private String Result = "Failed";

    public String login_Student(UserAuth user,int YOP,Activity context){
    mdatabase = FirebaseDatabase.getInstance().getReference();
    mdatabase.child(Integer.toString(YOP)+"_Student_Auth").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot snap : snapshot.getChildren()){
                UserAuth cur = snap.getValue(UserAuth.class);
                try{
                    if(cur.getUsername().equals(user.getUsername())){
                        Log.d("Check1",cur.getUsername());
                        if(cur.getPassword().equals(user.getPassword())){


                        }
                        else{
                            Toast.makeText(context, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }catch (Exception e){
                    Log.d("Check1",e.toString());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(context, "Connectivity Error", Toast.LENGTH_SHORT).show();
        }
    });

        return Result;
    }

    public String login_Faculty(UserAuth user, Activity context){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("Faculty_Auth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserAuth cur = snap.getValue(UserAuth.class);
                    try{
                        if(cur.getUsername().equals(user.getUsername())){
                            Log.d("Check1",cur.getUsername());
                            if(cur.getPassword().equals(user.getPassword())){

                            }
                            else{
                                Toast.makeText(context, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }catch (Exception e){
                        Log.d("Check1",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });

        return Result;

    }

    public String login_Admin(UserAuth user,Activity context){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("Admin_Auth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    UserAuth cur = snap.getValue(UserAuth.class);
                    try{
                        if(cur.getUsername().equals(user.getUsername())){
                            Log.d("Check1",cur.getUsername());
                            if(cur.getPassword().equals(user.getPassword())){
                                Activity_Sign_in obj = new Activity_Sign_in();

                            }
                            else{
                                Toast.makeText(context, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }catch (Exception e){
                        Log.d("Check1",e.toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Connectivity Error", Toast.LENGTH_SHORT).show();
            }
        });

        return Result;
    }

    public void get_Posts(Activity context){
        All_Data data_obj = new All_Data();
            mdatabase = FirebaseDatabase.getInstance().getReference("Posts");
            mdatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot snap : snapshot.getChildren()){
                        //Posts current = snap.getValue(Posts.class);
                        //get_post_image(current.getPostId(),context);
                        //data_obj.Posts_list.add(current);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("Error","occurred while fetching data for posts in Firebase_Instance_get ==> get_posts()");
                }
            });
    }

    public void get_post_image(String Postid, Activity context){
        All_Data obj =new All_Data();
        String impath="Posts_image/"+Postid;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference img=storage.getReference().child(impath);
        try{
            File localfile=File.createTempFile("tempfille",".jpg");
            img.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap im= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    Drawable d = new BitmapDrawable(context.getResources(),im);
                    obj.post_images.add(d);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Error", "Loading post image");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void get_Student_details(int YOP,String User_name){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child(Integer.toString(YOP)+"_Students").child(User_name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                All_Data obj = new All_Data();
                if(task.isSuccessful()){
                    obj.User_details = task.getResult().getValue(Student.class);
                }
            }
        });
    }

    public void get_placement_mail(){
        mdatabase = FirebaseDatabase.getInstance().getReference("Placement_mail");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                All_Data obj = new All_Data();
                for(DataSnapshot snap : snapshot.getChildren()){
                    PlacementMail cur = snap.getValue(PlacementMail.class);
                    obj.Mail_list.add(cur);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_placement_mail()");
            }
        });
    }

    public void get_Marks(String USN,int YOP){
        mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_Marks_list");
        mdatabase.child(USN).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                All_Data obj = new All_Data();
                if(task.isSuccessful()){
                    obj.marks = task.getResult().getValue(Marks.class);
                }
            }
        });
    }

    public void get_Subjects(int sem){
        mdatabase = FirebaseDatabase.getInstance().getReference("Subjects");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                All_Data obj = new All_Data();
                for(DataSnapshot snap : snapshot.getChildren()){
                    Subjects current = snap.getValue(Subjects.class);
                    try{
                        if(current.getSem() == sem){
                            obj.subjects = current;
                            break;
                        }
                    }catch(Exception e){
                        Log.d("Error","null sum exception in get_subjects()");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_subjects()");
            }
        });
    }

    public void get_Student_Attendance(String USN,String Branch,String Section,int YOP){
        mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_"+Branch+"_"+Section);
        mdatabase.child(USN).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                All_Data obj = new All_Data();
                if(task.isSuccessful()){
                    obj.student_attendance = task.getResult().getValue(Attendance.class);
                }
                else{
                    Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_student_attendance()");
                }
            }
        });
    }

    public void get_Section_Attendance(String Branch,String Section,int YOP){
        mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_"+Branch+"_"+Section);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                All_Data obj = new All_Data();
                for(DataSnapshot snap:snapshot.getChildren()){
                    Attendance current = snap.getValue(Attendance.class);
                    obj.Section_Attendance_List.add(current);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_Section_Attendance()");
            }
        });
    }

}
