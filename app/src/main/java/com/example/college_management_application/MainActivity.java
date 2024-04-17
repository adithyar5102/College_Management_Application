 package com.example.college_management_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import com.example.college_management_application.R;
 public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //binding.bottomNavigationView.setBackground(null);
        openFragment(new HomeFragment());
        binding.textView.setText("Home");
        binding.bottomNavigationView.setOnItemSelectedListener(Item ->{
            int itemid = Item.getItemId();
            if(itemid == R.id.Home){
                openFragment(new HomeFragment());
                binding.textView.setText("Home");
            }
            else if(itemid == R.id.Mail){
                openFragment(new MailFragment());
                binding.textView.setText("Placement Mails");
            } else if (itemid == R.id.Hostel) {
                openFragment(new HostelFragment());
                binding.textView.setText("Hostel");
            }else if (itemid == R.id.Settings) {
                openFragment(new SettingsFragment());
                binding.textView.setText("Settings");
            }
            return true;
        });

    }

    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }


}
