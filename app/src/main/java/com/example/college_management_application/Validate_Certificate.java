package com.example.college_management_application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Validate_Certificate {
    public int marks;
    public int get_Marks(String USN,int YOP,int sem){
        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference(Integer.toString(YOP)+"_Marks_list");
        mdatabase.child(USN).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Validate_Certificate obj = new Validate_Certificate();
                if(task.isSuccessful()){
                    Marks mark = task.getResult().getValue(Marks.class);
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(mark.getSem1());
                    temp.add(mark.getSem2());
                    temp.add(mark.getSem3());
                    temp.add(mark.getSem4());
                    temp.add(mark.getSem5());
                    temp.add(mark.getSem6());
                    temp.add(mark.getSem7());
                    temp.add(mark.getSem8());
                    obj.marks = temp.get(sem-1);
                }
            }
        });
        return marks;
    }
}
