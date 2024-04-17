package com.example.college_management_application;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.ViewHolder> {
    private ArrayList<Section_Attendance> students;

    public StudentAttendanceAdapter(ArrayList<Section_Attendance> students) {
        this.students = students;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_attendance_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Section_Attendance student = students.get(position);
        Log.d("check1",student.getName());
        holder.nameTextView.setText(student.getName());
        holder.attendanceSwitch.setChecked(student.isPresent());
        holder.attendanceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> student.setPresent(isChecked));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Switch attendanceSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.rv_nameTextView);
            attendanceSwitch = itemView.findViewById(R.id.rv_attendanceSwitch);
        }
    }
}
