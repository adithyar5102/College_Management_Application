package com.example.college_management_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MailFragment newInstance(String param1, String param2) {
        MailFragment fragment = new MailFragment();
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
        return inflater.inflate(R.layout.fragment_mail, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        get_mail(view);
    }
    All_Data obj = new All_Data();
    RecyclerView recyclerView;
    ArrayList<PlacementMail> mail_list;
    int yop;
    private void get_mail(View view) {
        DatabaseReference mdatabase = obj.mdatabase;
        yop = SharedPrefManager.getInstance(getContext()).getYear();
        mdatabase.child("Mails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                All_Data obj = new All_Data();
                for(DataSnapshot snap : snapshot.getChildren()){
                    PlacementMail cur = snap.getValue(PlacementMail.class);
                    assert cur != null;
                    if(cur.getYop() == yop){
                        obj.Mail_list.add(cur);
                    }
                }
                startrv(view,obj.Mail_list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("check1","Fetching m,ai error");
                Log.d("Error","occurred while fetching data in Firebase_Instance_get ==> get_placement_mail()");
            }
        });

    }

    private void startrv(View view,ArrayList<PlacementMail> list) {

        recyclerView = view.findViewById(R.id.fragment_mail_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        Mail_display_adapter adapter = new Mail_display_adapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}