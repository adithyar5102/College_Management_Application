package com.example.college_management_application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    All_Data obj = new All_Data();
    View v;
    private TextView fname, mname, lname, usn, branch, sem;
    Button certificates,fees;
    private TextView[] subjects = new TextView[10];
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Check1","settings");
        v = view;
        fname = view.findViewById(R.id.fragment_settings_fname);
        mname = view.findViewById(R.id.fragment_settings_mname);
        lname = view.findViewById(R.id.fragment_settings_lname);
        usn = view.findViewById(R.id.fragment_settings_usn);
        branch = view.findViewById(R.id.fragment_settings_branch);
        sem = view.findViewById(R.id.fragment_settings_sem);
        certificates = view.findViewById(R.id.fragment_settings_certificate_button);
        fees = view.findViewById(R.id.fragment_settings_fees_button);

        // Initialize subject TextViews
        subjects[0] = view.findViewById(R.id.fragment_settings_sub1);
        subjects[1] = view.findViewById(R.id.fragment_settings_sub2);
        subjects[2] = view.findViewById(R.id.fragment_settings_sub3);
        subjects[3] = view.findViewById(R.id.fragment_settings_sub4);
        subjects[4] = view.findViewById(R.id.fragment_settings_sub5);
        subjects[5] = view.findViewById(R.id.fragment_settings_sub6);
        subjects[6] = view.findViewById(R.id.fragment_settings_sub7);
        subjects[7] = view.findViewById(R.id.fragment_settings_sub8);
        subjects[8] = view.findViewById(R.id.fragment_settings_sub9);
        subjects[9] = view.findViewById(R.id.fragment_settings_sub10);

        int year = SharedPrefManager.getInstance(getContext()).getYear();
        String username = SharedPrefManager.getInstance(getContext()).getUsername();


        get_section(year,username);

        certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(),Activity_get_certificates.class);
                startActivity(i);
            }
        });
        fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(),Activity_Student_fees.class);
                startActivity(i);
            }
        });

    }
    private void get_section(int yop, String userName) {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child(Integer.toString(yop)+"_Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Student cur = snap.getValue(Student.class);
                    if(cur.getUsn().equals(userName)){
                        Log.d("Check1",cur.getUsn());
                        obj.User_details = snap.getValue(Student.class);
                        get_attendance(cur.getYOP(),cur.getSec(),cur.getBranch(),cur.getUsn(),cur.getSem(),cur);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(v.getContext(), "Couldn't load the data try after sometime", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void get_attendance(int yop, String sec, String branch, String userName, int sem,Student user_detail) {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child(Integer.toString(yop)+"_"+branch+"_"+sec).child("Total_Class").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                obj.totalclass = snapshot.getValue(Attendance.class);
                Log.d("Check1","got tc attendance");
                Log.d("Check1",obj.totalclass.getUsn());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(v.getContext(), "Couldn't load the data try after sometime", Toast.LENGTH_SHORT).show();
            }
        });

        mdatabase.child(Integer.toString(yop)+"_"+branch+"_"+sec).child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                obj.student_attendance = snapshot.getValue(Attendance.class);
                get_Subjects(sem,user_detail,obj.totalclass,obj.student_attendance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(v.getContext(), "Couldn't load the data try after sometime", Toast.LENGTH_SHORT).show();

            }
        });
    }



    public void get_Subjects(int sem,Student user_detail,Attendance total_class,Attendance student_attendance){
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Subjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                All_Data obj = new All_Data();
                for(DataSnapshot snap : snapshot.getChildren()){
                    Subjects current = snap.getValue(Subjects.class);
                    try{
                        if(current.getSem() == sem){
                            //Log.d("check1",current.getBranch() );
                            //Log.d("check1",obj.User_details.getBranch());

                            if(current.getBranch().equals(user_detail.getBranch())){
                                display_data(current,user_detail,total_class,student_attendance);
                                return;
                            }
                        }
                    }catch(Exception e){
                        Log.d("Error","null sum exception in get_subjects()");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_subjects()");
            }
        });
    }

    private void display_data(Subjects cur_subs,Student user_detail,Attendance total_class,Attendance student_attendance) {
        Student cur = user_detail;
        Attendance user_att = student_attendance;
        //Subjects cur_subs = obj.subjects;
        Attendance tc = total_class;
        fname.setText("First Name : "+cur.getFirstName());
        mname.setText("Middle Name : "+cur.getMidName());
        lname.setText("Last Name : "+cur.getLastName());
        usn.setText("USN : "+cur.getUsn());
        branch.setText("Branch : "+cur.getBranch());
        sem.setText("Semister : "+cur.getSem());

        if(cur_subs.getSub1().length()!=0){
            subjects[0].setText(cur_subs.getSub1() +" : "+user_att.getsub1()+"/"+tc.getsub1());
        }
        if(cur_subs.getSub2().length()!=0){
            subjects[1].setText(cur_subs.getSub2() +" : "+user_att.getsub2()+"/"+tc.getsub2());
        }
        if(cur_subs.getSub3().length()!=0){
            subjects[2].setText(cur_subs.getSub3() +" : "+user_att.getsub3()+"/"+tc.getsub3());
        }
        if(cur_subs.getSub4().length()!=0){
            subjects[3].setText(cur_subs.getSub4() +" : "+user_att.getsub4()+"/"+tc.getsub4());
        }
        if(cur_subs.getSub5().length()!=0){
            subjects[4].setText(cur_subs.getSub5() +" : "+user_att.getsub5()+"/"+tc.getsub5());
        }
        if(cur_subs.getSub6().length()!=0){
            subjects[5].setText(cur_subs.getSub6() +" : "+user_att.getsub6()+"/"+tc.getsub6());
        }
        if(cur_subs.getSub7().length()!=0){
            subjects[6].setText(cur_subs.getSub7() +" : "+user_att.getsub7()+"/"+tc.getsub7());
        }
        if(cur_subs.getSub8().length()!=0){
            subjects[7].setText(cur_subs.getSub8() +" : "+user_att.getsub8()+"/"+tc.getsub8());
        }
        if(cur_subs.getSub9().length()!=0){
            subjects[8].setText(cur_subs.getSub9() +" : "+user_att.getsub9()+"/"+tc.getsub9());
        }
        if(cur_subs.getSub10().length()!=0){
            subjects[9].setText(cur_subs.getSub10() +" : "+user_att.getsub10()+"/"+tc.getsub10());
        }
    }


}