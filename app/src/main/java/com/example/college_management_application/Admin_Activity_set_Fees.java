package com.example.college_management_application;

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

import com.example.college_management_application.databinding.ActivityAdminSetFeesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Admin_Activity_set_Fees extends AppCompatActivity {
    ActivityResultLauncher<Intent> pickCsvLauncher;
    ArrayList<Student_Fees> fee_list;
    Uri csvUri;
    ActivityAdminSetFeesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSetFeesBinding.inflate(getLayoutInflater());
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
                        fee_list = readCSV(csvUri);
                        // Do something with the ArrayList of Student objects
                        // ...
                    }
                });
        binding.adminSetFeesFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    selectCsvFile();
                }catch (Exception e){
                    Toast.makeText(Admin_Activity_set_Fees.this, "Couldn't read csv file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.adminSetFeesDetailsUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yop = binding.adminSetFeesYop.getText().toString();
                uploadfile_todb(yop);
            }
        });
    }
    private void uploadfile_todb(String YOP) {
        All_Data obj = new All_Data();
        StorageReference storageReference = obj.storageReference;
        storageReference = storageReference.child("Student_Fees/").child(YOP);
        storageReference.putFile(csvUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Admin_Activity_set_Fees.this, "File uploaded successfully", Toast.LENGTH_SHORT).show();
                upload(YOP);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Activity_set_Fees.this, "Error while uploading file", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upload(String YOP) {

        All_Data obj = new All_Data();
        DatabaseReference mdatabase = obj.mdatabase;
        try{
            for(Student_Fees current:fee_list){
                mdatabase.child("Student_fees_details_"+YOP).child(current.getUSN()).setValue(current);
            }
            Toast.makeText(Admin_Activity_set_Fees.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(Admin_Activity_set_Fees.this, "Upload Failed", Toast.LENGTH_SHORT).show();
        }

    }
    private void selectCsvFile() {
        Intent pickCsvIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickCsvIntent.setType("*/*");
        String[] mimetypes = {"text/csv", "text/comma-separated-values", "application/csv"};
        pickCsvIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        pickCsvLauncher.launch(pickCsvIntent);
    }

    private ArrayList<Student_Fees> readCSV(Uri csvUri) {
        ArrayList<Student_Fees> marksList = new ArrayList<>();
        try {
            InputStream inputStream = Admin_Activity_set_Fees.this.getContentResolver().openInputStream(csvUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                marksList.add(new Student_Fees(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]),
                        Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8]),
                        Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(Admin_Activity_set_Fees.this, "Error in reading the csv file", Toast.LENGTH_SHORT).show();
            Toast.makeText(Admin_Activity_set_Fees.this, "Check the csv file format", Toast.LENGTH_SHORT).show();
            Log.d("Check1",e.toString());

        }
        return marksList;
    }
}