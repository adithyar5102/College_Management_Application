  package com.example.college_management_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.college_management_application.databinding.ActivityAdminHomeBinding;

  public class Admin_Activity_Home extends AppCompatActivity {
    ActivityAdminHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        openFragment(new Admin_addpost_Fragment());
        binding.AdminTextView.setText("Upload Posts");
        binding.AdminBottomNavigationView.setOnItemSelectedListener(Item ->{
            int itemid = Item.getItemId();
            if(itemid == R.id.anb_post){
                openFragment(new Admin_addpost_Fragment());
                binding.AdminTextView.setText("Upload Posts");
            }
            else if(itemid == R.id.anb_mail){
                openFragment(new Admin_setmail_Fragment());
                binding.AdminTextView.setText("Upload Mails");
            } else if (itemid == R.id.anb_marks) {
                openFragment(new Admin_addmarks_Fragment());
                binding.AdminTextView.setText("Upload Marks");
            }else if (itemid == R.id.anb_more) {
                openFragment(new Admin_more_Fragment());
                binding.AdminTextView.setText("More");
            }else if (itemid == R.id.anb_student){
                openFragment(new Admin_addstudent_Fragment());
                binding.AdminTextView.setText("Add Students");
            }
            return true;
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity_Home.this,Activity_Sign_in.class);
                startActivity(intent);
            }
        });
    }

      private void openFragment(Fragment fragment){
          FragmentManager fragmentManager = getSupportFragmentManager();
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.Admin_frameLayout,fragment);
          fragmentTransaction.commit();
      }
}