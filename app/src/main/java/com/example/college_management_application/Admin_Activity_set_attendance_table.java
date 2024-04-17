package com.example.college_management_application;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityAdminSetAttendanceTableBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Admin_Activity_set_attendance_table extends AppCompatActivity {
    ActivityResultLauncher<Intent> pickCsvLauncher;
    Uri csvUri;
    String branch,sec;
    int YOP;
    ArrayList<Attendance> attendanceList;

    ActivityAdminSetAttendanceTableBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSetAttendanceTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pickCsvLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        // Get the data from the result intent
                        Intent data = result.getData();
                        // Get the URI of the selected CSV file
                        csvUri = data.getData();
                        // Read the CSV file into an ArrayList of Attendance objects
                        attendanceList = readCSV(csvUri);

                    }
                });

        binding.adminSetAtAddfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCsvFile();
            }
        });
        binding.adminSetAtUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    branch = binding.adminSetAtBranch.getText().toString().toUpperCase();
                    sec = binding.adminSetAtSection.getText().toString().toUpperCase();
                    YOP = Integer.parseInt(binding.adminSetAtYearOfPassing.getText().toString());
                }catch (Exception e){
                    Toast.makeText(Admin_Activity_set_attendance_table.this, "Enter Valid details", Toast.LENGTH_SHORT).show();
                }
                attendanceList.add(new Attendance("Total_Class"));
                set_Attendance_Table(YOP,branch,sec,attendanceList);
            }
        });

    }

    private void selectCsvFile() {
        Intent pickCsvIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickCsvIntent.setType("*/*");
        String[] mimetypes = {"text/csv", "text/comma-separated-values", "application/csv"};
        pickCsvIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        pickCsvLauncher.launch(pickCsvIntent);
    }

    private ArrayList<Attendance> readCSV(Uri csvUri) {
        ArrayList<Attendance> attendanceList = new ArrayList<>();
        try {
            InputStream inputStream = getContentResolver().openInputStream(csvUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                attendanceList.add(new Attendance(values[0]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            Toast.makeText(Admin_Activity_set_attendance_table.this, "Error in reading the csv file", Toast.LENGTH_SHORT).show();
            Toast.makeText(Admin_Activity_set_attendance_table.this, "Check the csv file format", Toast.LENGTH_SHORT).show();
            Log.d("Check1",e.toString());
        }
        return attendanceList;
    }

    public void set_Attendance_Table(int YOP,String Branch,String Section,ArrayList<Attendance> list){
        All_Data obj = new All_Data();
        DatabaseReference mdatabase = obj.mdatabase;
        try{
            for(Attendance student : list){
                mdatabase.child(Integer.toString(YOP)+"_"+Branch+"_"+Section).child(student.getUsn()).setValue(student);
            }
            Attendance tot = new Attendance("Total_Class");
            mdatabase.child(Integer.toString(YOP)+"_"+Branch+"_"+Section).child("Total_Class").setValue(tot);
            Toast.makeText(Admin_Activity_set_attendance_table.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
            binding.adminSetAtBranch.setText("");
            binding.adminSetAtSection.setText("");
            binding.adminSetAtBranch.setText("");
        }catch (Exception e){
            Toast.makeText(Admin_Activity_set_attendance_table.this, "Error while uploading the data", Toast.LENGTH_SHORT).show();
        }

    }
}
