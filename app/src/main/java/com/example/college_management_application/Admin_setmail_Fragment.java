package com.example.college_management_application;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_setmail_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Admin_setmail_Fragment extends Fragment {
    ImageView imageView;
    EditText editText,form,descr,yop;
    Button select_image;
    Button select_file;
    Button submit;

    private boolean imstatus , fistatus;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_setmail_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_setmail_Fragment newInstance(String param1, String param2) {
        Admin_setmail_Fragment fragment = new Admin_setmail_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Admin_setmail_Fragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_admin_setmail_, container, false);
    }
    private Uri imageUri;
    private Uri pdfUri;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private static final int PICK_PDF_FILE = 1;
    ActivityResultLauncher<Intent> pickPdfLauncher;

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.Admin_mail_companyName);
        form = view.findViewById(R.id.Admin_mail_formLink);
        descr = view.findViewById(R.id.Admin_mail_description);
        select_image = view.findViewById(R.id.Admin_mail_uploadPhotoButton);
        submit = view.findViewById(R.id.Admin_mail_submitButton);
        select_file = view.findViewById(R.id.Admin_mail_uploadFileButton);
        yop = view.findViewById(R.id.Admin_mail_year_of_passing);
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
                        imstatus = true;

                    }
                });

        pickPdfLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Get the data from the result intent
                        Intent data = result.getData();
                        // Get the URI of the selected PDF file
                        pdfUri = data.getData();
                        // Do something with the PDF file URI
                        fistatus = true;
                    }
                });

        // Call selectImage() when you want to pick an image
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        select_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdfFile();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imstatus || !fistatus){
                    Toast.makeText(getContext(), "Upload image and pdf file", Toast.LENGTH_SHORT).show();
                }else{
                    upload_data();
                }
            }
        });


    }
    private void selectImage() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK);
        pickImageIntent.setType("image/*");
        pickImageLauncher.launch(pickImageIntent);
    }

    private void selectPdfFile() {
        Intent pickPdfIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickPdfIntent.setType("application/pdf");
        pickPdfIntent.addCategory(Intent.CATEGORY_OPENABLE);
        pickPdfLauncher.launch(pickPdfIntent);
    }
    int id ;
    All_Data dobj = new All_Data();
    private void upload_data(){
        DatabaseReference mdatabase = dobj.mdatabase;
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the value of a single field
                Log.d("check1","before call");
                id = dataSnapshot.child("Id_counts").child("Mails").getValue(Integer.class);
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
        mdatabase.child("Id_counts").child("Mails").setValue(i);
        upload_details(i);
    }

    private void upload_details(int nid) {

        StorageReference storageReference = dobj.storageReference;

        StorageReference ref2 = storageReference;

        ref2 = ref2.child("Mails_Pdfs/").child(Integer.toString(nid)+".pdf");
        ref2.putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                upload_image(nid);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

    private void upload_image(int nid) {
        DatabaseReference mdatabase = dobj.mdatabase;
        StorageReference storageReference = dobj.storageReference;


        String company = editText.getText().toString();
        String descri = descr.getText().toString();
        String fo = form.getText().toString();
        int yo = Integer.parseInt(yop.getText().toString());

        PlacementMail newmail = new PlacementMail(nid,company,descri,Integer.toString(nid)+"."+getextension(imageUri),Integer.toString(nid)+".pdf",fo,yo);
        storageReference = storageReference.child("Mails_image/").child(Integer.toString(nid)+"."+getextension(imageUri));
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getContext(), "Uploaded successfully", Toast.LENGTH_SHORT).show();
                editText.setText("");
                descr.setText("");
                form.setText("");
                select_image.setBackgroundColor(R.color.DefaultBlue);
                select_file.setBackgroundColor(R.color.DefaultBlue);
                mdatabase.child("Mails").child(String.valueOf(nid)).setValue(newmail);
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

}