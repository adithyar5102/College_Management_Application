package com.example.college_management_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mail_display_adapter extends RecyclerView.Adapter<Mail_display_adapter.Mail_view_holder> {

    Context context;
    ArrayList<PlacementMail> mail_list;
    Mail_display_adapter(Context context,ArrayList<PlacementMail> maillist){
        this.context = context;
        this.mail_list = maillist;
    }

    @NonNull
    @Override
    public Mail_display_adapter.Mail_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_mail,parent,false);
        return new Mail_view_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Mail_display_adapter.Mail_view_holder holder, int position) {
        PlacementMail cur = mail_list.get(position);
        holder.company.setText(cur.getCompany());
        holder.description.setText(cur.getDescription());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Activity_Mail_Indetail.class);
                intent.putExtra("company",cur.getCompany());
                intent.putExtra("description",cur.getDescription());
                intent.putExtra("id",cur.getMailId());
                intent.putExtra("file_link",cur.getFileLink());
                intent.putExtra("form_link",cur.getForm_link());
                intent.putExtra("photo_link",cur.getPhotoLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mail_list.size();
    }

    public static class Mail_view_holder extends RecyclerView.ViewHolder{
        TextView company,description;
        LinearLayout layout;
        public Mail_view_holder(@NonNull View itemView) {
            super(itemView);
            company = itemView.findViewById(R.id.rv_mail_company_name);
            description = itemView.findViewById(R.id.rv_mail_description);
            layout = itemView.findViewById(R.id.rv_mail_layout);
        }
    }
}
