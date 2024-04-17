package com.example.college_management_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.college_management_application.databinding.ActivityPaymentGatewayBinding;
import com.google.firebase.database.DatabaseReference;

public class Payment_gateway extends AppCompatActivity {
    ActivityPaymentGatewayBinding binding;
    String year;
    Double topay,paid;
    String username;
    All_Data obj = new All_Data();
    int yop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentGatewayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        year = intent.getStringExtra("year");
        topay = intent.getDoubleExtra("To pay",0);
        paid = intent.getDoubleExtra("paid",0);
        binding.paymentGatewayTopay.setText(Double.toString(topay));
        yop = SharedPrefManager.getInstance(Payment_gateway.this).getYear();
        username = SharedPrefManager.getInstance(Payment_gateway.this).getUsername();
        binding.paymentGatewayFailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onpaymentfailure();
            }
        });
        binding.paymentGatewaySuccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onpaymentsuccess();
            }
        });
    }

    private void onpaymentsuccess() {
        DatabaseReference mdatabase = obj.mdatabase;
        mdatabase.child("Student_fees_details_"+yop).child(username).child(year).setValue(paid+topay);
        Toast.makeText(Payment_gateway.this, "Transaction Successfull", Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(Payment_gateway.this,Activity_Student_fees.class);
        startActivity(i);
    }

    private void onpaymentfailure() {
        Toast.makeText(Payment_gateway.this, "Transaction Failed", Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(Payment_gateway.this,Activity_Student_fees.class);
        startActivity(i);
    }


}