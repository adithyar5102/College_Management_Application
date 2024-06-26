package com.example.college_management_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_more_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Admin_more_Fragment extends Fragment {

    Button subject,attendance,faculty,admin,facultydetails,studentfees;
    //Admin_Activity_set_

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Admin_more_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Admin_more_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Admin_more_Fragment newInstance(String param1, String param2) {
        Admin_more_Fragment fragment = new Admin_more_Fragment();
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
        return inflater.inflate(R.layout.fragment_admin_more_, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subject = view.findViewById(R.id.admin_more_set_subjects_button);
        attendance = view.findViewById(R.id.admin_more_set_attendance_button);
        admin = view.findViewById(R.id.admin_more_set_admin_button);
        faculty = view.findViewById(R.id.admin_more_set_Faculty_button);
        studentfees = view.findViewById(R.id.admin_more_set_student_fees_button);
        facultydetails = view.findViewById(R.id.admin_more_set_faculty_button);

        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_set_subjects.class);
                startActivity(intent);
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_set_attendance_table.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_add_admin.class);
                startActivity(intent);
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_add_admin.class);
                startActivity(intent);
            }
        });

        studentfees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_set_Fees.class);
                startActivity(intent);
            }
        });

        facultydetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Admin_Activity_Faculty_details.class);
                startActivity(intent);
            }
        });
    }
}