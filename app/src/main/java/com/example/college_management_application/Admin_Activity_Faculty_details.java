package com.example.college_management_application;

import static androidx.core.content.ContentProviderCompat.requireContext;

import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityAdminFacultyDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Admin_Activity_Faculty_details extends AppCompatActivity {

    ActivityAdminFacultyDetailsBinding binding;
    ActivityResultLauncher<Intent> pickCsvLauncher;
    ArrayList<Faculty_details> faculty_list;
    Uri csvUri;
    All_Data obj = new All_Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminFacultyDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pickCsvLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the data from the result intent
                        Intent data = result.getData();
                        // Get the URI of the selected CSV file
                        csvUri = data.getData();
                        // Read the CSV file into an ArrayList of Student objects
                        faculty_list = readCSV(csvUri);
                        // Do something with the ArrayList of Student objects
                        // ...
                    }
                });

        binding.adminAddFacultyDetailsFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    selectCsvFile();
                }catch(Exception e){
                    Toast.makeText(Admin_Activity_Faculty_details.this, "Couldn't read the file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.adminAddFacultyDetailsUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String branch = binding.adminAddFacultyDetailsBranch.getText().toString();
                uploadfile_todb(branch);
                upload(branch);
            }
        });
    }

    private void uploadfile_todb(String branch) {
        StorageReference storageReference = obj.storageReference;
        storageReference = storageReference.child("Faculty/").child(branch);
        storageReference.putFile(csvUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Admin_Activity_Faculty_details.this, "File uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Activity_Faculty_details.this, "Error while uploading file", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upload(String branch) {


        DatabaseReference mdatabase = obj.mdatabase;
        try{
            for(Faculty_details current:faculty_list){
                String mail = current.getMail().replace(".",",");
                mdatabase.child("Faculty_Details_"+branch).child(mail).setValue(current);
            }
            Toast.makeText(Admin_Activity_Faculty_details.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.d("check1",e.toString());
            Toast.makeText(Admin_Activity_Faculty_details.this, "Upload Failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void selectCsvFile() {
        Intent pickCsvIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickCsvIntent.setType("*/*");
        String[] mimetypes = {"text/csv", "text/comma-separated-values", "application/csv"};
        pickCsvIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        pickCsvLauncher.launch(pickCsvIntent);
    }

    private ArrayList<Faculty_details> readCSV(Uri csvUri) {
        ArrayList<Faculty_details> marksList = new ArrayList<>();
        try {
            InputStream inputStream = Admin_Activity_Faculty_details.this.getContentResolver().openInputStream(csvUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                marksList.add(new Faculty_details(values[0], values[1], values[2], values[3], values[4], Integer.parseInt(values[5]), values[6]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(Admin_Activity_Faculty_details.this, "Error in reading the csv file", Toast.LENGTH_SHORT).show();
            Toast.makeText(Admin_Activity_Faculty_details.this, "Check the csv file format", Toast.LENGTH_SHORT).show();
            Log.d("Check1",e.toString());

        }
        return marksList;
    }
}