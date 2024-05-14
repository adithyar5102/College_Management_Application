package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityMailIndetailBinding;
import com.example.college_management_application.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Activity_Mail_Indetail extends AppCompatActivity {
    ActivityMailIndetailBinding binding;
    All_Data obj = new All_Data();
    //PlacementMail curmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMailIndetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String company = intent.getStringExtra("company");
        String description = intent.getStringExtra("description");
        int id = intent.getIntExtra("id",0);
        String fileLink = intent.getStringExtra("file_link");
        String formLink = intent.getStringExtra("form_link");
        String photoLink = intent.getStringExtra("photo_link");

        //curmail = new PlacementMail(id,company,description,photoLink,fileLink,formLink);
        binding.mailIndetailCompanyName.setText(company);
        binding.mailIndetailDescription.setText(description);
        binding.mailIndetailLink.setText(formLink);
        get_image_from_storage(photoLink);
        binding.mailIndetailDownloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    get_pdf_from_storage(fileLink, company);
                }catch (Exception e){
                    Toast.makeText(Activity_Mail_Indetail.this, "Unable to download", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    File finalLocalFile;
    File localFile;
    private void get_pdf_from_storage(String pdflink,String companyname) {
        StorageReference sr = obj.storageReference;
        StorageReference storageRef = sr.child("Mails_Pdfs/"+pdflink);
        localFile = null;
        try {
            localFile = File.createTempFile("companyname", "pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (localFile != null) {
            finalLocalFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pdflink);
            storageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // File downloaded successfully
                            Toast.makeText(getApplicationContext(), "File downloaded to " + finalLocalFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            Toast.makeText(getApplicationContext(), "Failed to download file", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    Bitmap bitmap;
    private void get_image_from_storage(String imagelink) {
        StorageReference sr = obj.storageReference;
        StorageReference nsr = sr.child("Mails_image/"+imagelink);
        try {
            File localfile=File.createTempFile(imagelink,"jpg");
            nsr.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    binding.mailIndetailImage.setImageBitmap(bitmap);
                    //holder.image.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("check1","Load image failed");
                    Log.d("check1",e.toString());

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}