package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityGetCertificatesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Activity_get_certificates extends AppCompatActivity {
    int sem,year;
    String username;
    ActivityGetCertificatesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetCertificatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getCertificatesDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sem = Integer.parseInt(binding.getCertificatesGetSem.getText().toString());
                username = SharedPrefManager.getInstance(Activity_get_certificates.this).getUsername();
                year = SharedPrefManager.getInstance(Activity_get_certificates.this).getYear();
                downloadFile();
            }
        });
    }
    All_Data obj = new All_Data();
    private void downloadFile() {
        // Get Firebase Storage reference
        StorageReference storageRef = obj.storageReference;

        // Create local file in internal storage
        File outputDir = getApplicationContext().getFilesDir(); // context being the Activity pointer
        File outputFile = null;
        try {
            outputFile = File.createTempFile("image", "jpg", outputDir);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (outputFile != null) {
            //File finalOutputFile = outputFile;
            File finalOutputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), username + "_" + sem + ".png");
            storageRef.child(year+"_Certificates/"+username+"_"+sem+".png").getFile(finalOutputFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // File downloaded successfully

                            Toast.makeText(getApplicationContext(), "Image downloaded to " + finalOutputFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            Log.d("check1",exception.toString());
                            Toast.makeText(getApplicationContext(), "Failed to download image", Toast.LENGTH_LONG).show();
                            Toast.makeText(Activity_get_certificates.this, "Certificate Might Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}