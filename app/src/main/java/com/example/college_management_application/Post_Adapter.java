package com.example.college_management_application;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.Post_viewholder> {

    Context context;
    ArrayList<Posts> data_list;
    All_Data dobj = new All_Data();
    public Post_Adapter(Context context, ArrayList<Posts> data_list){
        this.context = context;
        this.data_list = data_list;
    }


    @NonNull
    @Override
    public Post_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_student_home,parent,false);
        return new Post_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_viewholder holder, int position) {
        Posts post = data_list.get(position);
        holder.text.setText(post.getPostData());
        StorageReference sr = dobj.storageReference;
        StorageReference nsr = sr.child("Posts_image/"+post.getPhotoLink());
        try {
            File localfile=File.createTempFile(post.getPhotoLink(),"jpg");
            nsr.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    holder.image.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("check1","Load image failed");
                    Log.d("check1",e.toString());

                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public static class Post_viewholder extends RecyclerView.ViewHolder{

        TextView text;
        ImageView image;
        public Post_viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.rv_student_home_imageView);
            text = itemView.findViewById(R.id.rv_student_home_post_text);
        }
    }
}
