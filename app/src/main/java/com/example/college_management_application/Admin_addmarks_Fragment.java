package com.example.college_management_application;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_addmarks_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_addmarks_Fragment extends Fragment {
    Button selectfile;
    Button adddetails;
    EditText YearOP;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_addmarks_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_addmarks_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_addmarks_Fragment newInstance(String param1, String param2) {
        Admin_addmarks_Fragment fragment = new Admin_addmarks_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_addmarks_, container, false);
    }

    ActivityResultLauncher<Intent> pickCsvLauncher;
    ArrayList<Marks> marks;
    Uri csvUri;
    View v;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectfile = view.findViewById(R.id.admin_add_marks_file_button);
        adddetails = view.findViewById(R.id.admin_add_marks_button);
        YearOP = view.findViewById(R.id.admin_add_marks_year_of_passing);
        v = view;
        pickCsvLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the data from the result intent
                        Intent data = result.getData();
                        // Get the URI of the selected CSV file
                        csvUri = data.getData();
                        // Read the CSV file into an ArrayList of Student objects
                        marks = readCSV(csvUri);
                        // Do something with the ArrayList of Student objects
                        // ...
                    }
                });

        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCsvFile();
            }
        });

        adddetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload(Integer.parseInt(YearOP.getText().toString()),marks);
            }
        });
    }

    private void upload(int YOP, ArrayList<Marks> Mark_list) {
        All_Data obj = new All_Data();
        DatabaseReference mdatabase = obj.mdatabase;
        try{
            for(Marks current:Mark_list){
                mdatabase.child(Integer.toString(YOP)+"_Marks_list").child(current.getUsn()).setValue(current);
            }
            Toast.makeText(getContext(), "Data Uploaded", Toast.LENGTH_SHORT).show();
            upload_file(YOP);
        }catch(Exception e){
            Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void upload_file(int yop) {
        All_Data obj = new All_Data();
        StorageReference storageReference = obj.storageReference;
        storageReference = storageReference.child("Students_Marks").child(Integer.toString(yop)+"_Marks");
        storageReference.putFile(csvUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error while uploading file", Toast.LENGTH_SHORT).show();
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

    private ArrayList<Marks> readCSV(Uri csvUri) {
        ArrayList<Marks> marksList = new ArrayList<>();
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(csvUri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                marksList.add(new Marks(values[0], Float.parseFloat(values[1]), Float.parseFloat(values[2]), Float.parseFloat(values[3]), Float.parseFloat(values[4]), Float.parseFloat(values[5]), Float.parseFloat(values[6]), Float.parseFloat(values[7]), Float.parseFloat(values[8])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(v.getContext(), "Error in reading the csv file", Toast.LENGTH_SHORT).show();
            Toast.makeText(v.getContext(), "Check the csv file format", Toast.LENGTH_SHORT).show();
            Log.d("Check1",e.toString());

        }
        return marksList;
    }


}