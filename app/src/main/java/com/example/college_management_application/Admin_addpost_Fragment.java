package com.example.college_management_application;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_addpost_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_addpost_Fragment extends Fragment {

    ImageView imageView;
    EditText editText;
    Button select_image;
    Button submit;
    private Uri imageUri;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_addpost_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_addpost_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_addpost_Fragment newInstance(String param1, String param2) {
        Admin_addpost_Fragment fragment = new Admin_addpost_Fragment();
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
        return inflater.inflate(R.layout.fragment_admin_addpost_, container, false);
    }
    private ActivityResultLauncher<Intent> pickImageLauncher;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.fragment_admin_addpost_imageView);
        editText = view.findViewById(R.id.fragment_admin_addpost_editText);
        select_image = view.findViewById(R.id.fragment_admin_addpost_button);
        submit = view.findViewById(R.id.fragment_admin_addpost_submit_btn);

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the data from the result intent
                        Intent data = result.getData();
                        // Do something with the data, such as displaying the image
                        // Get the URI of the selected image
                        imageUri = data.getData();
                        // Do something with the image, such as displaying it in an ImageView

                        imageView.setImageURI(imageUri);
                    }
                });

        // Call selectImage() when you want to pick an image
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_to_firebase();
            }
        });

    }
    int id ;
    All_Data dobj = new All_Data();
    private void upload_to_firebase() {
        All_Data dobj = new All_Data();
        DatabaseReference mdatabase = dobj.mdatabase;
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the value of a single field
                Log.d("check1","before call");
                id = dataSnapshot.child("Id_counts").child("Posts").getValue(Integer.class);
                changeid(id-1);
                // Do something with the value
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        };
        mdatabase.addListenerForSingleValueEvent(postListener);

    }

    private void changeid(int i) {
        DatabaseReference mdatabase = dobj.mdatabase;
        mdatabase.child("Id_counts").child("Posts").setValue(i);
        upload_details(i);
    }

    private void upload_details(int nid) {
        DatabaseReference mdatabase = dobj.mdatabase;
        StorageReference storageReference = dobj.storageReference;
        String text = editText.getText().toString();
        Posts newpost = new Posts(nid,text,Integer.toString(nid)+"."+getextension(imageUri));
        storageReference = storageReference.child("Posts_image/").child(Integer.toString(nid)+"."+getextension(imageUri));
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "Uploaded successfully", Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.image);
                editText.setText("");
                mdatabase.child("Posts").child(String.valueOf(nid)).setValue(newpost);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Unable to upload", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getextension(Uri imageUri) {
        // Use the ContentResolver to get the mime type of the file
        String mimeType = getContext().getContentResolver().getType(imageUri);

        // Use MimeTypeMap to get the extension from the mime type
        String extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);

        return extension;
    }


    private void selectImage() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK);
        pickImageIntent.setType("image/*");
        pickImageLauncher.launch(pickImageIntent);
    }

}