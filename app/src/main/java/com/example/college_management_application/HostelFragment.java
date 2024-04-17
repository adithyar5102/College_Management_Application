package com.example.college_management_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HostelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HostelFragment extends Fragment {
    TextView fnameTextView, lnameTextView, blockTextView, roomnoTextView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HostelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HostelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HostelFragment newInstance(String param1, String param2) {
        HostelFragment fragment = new HostelFragment();
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
        return inflater.inflate(R.layout.fragment_hostel, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fnameTextView = view.findViewById(R.id.fragment_hostel_fname);
        lnameTextView = view.findViewById(R.id.fragment_hostel_lname);
        blockTextView = view.findViewById(R.id.fragment_hostel_block);
        roomnoTextView = view.findViewById(R.id.fragment_hostel_roomno);
        getdetails();
    }

    All_Data obj = new All_Data();
    private void getdetails() {
        String usn = SharedPrefManager.getInstance(getContext()).getUsername();
        DatabaseReference mdatabase = obj.mdatabase;
        try{
            mdatabase.child("Hostel").child(usn).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Hostel_student std = snapshot.getValue(Hostel_student.class);
                    fnameTextView.setText("Name : "+std.getFirstName());
                    lnameTextView.setText("Last Name : "+std.getLastName());
                    blockTextView.setText("Hostel Block : "+std.getHostelBlock());
                    roomnoTextView.setText("Room Number : "+std.getRoomNumber());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    fnameTextView.setText("Not an Hostelite");
                }
            });
        }catch (Exception e){
            fnameTextView.setText("Not an Hostelite");
        }
    }
}