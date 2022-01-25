package com.example.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.otp.databinding.ActivityMainBinding;
import com.mukesh.OnOtpCompletionListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.otpview.setOtpCompletionListener(new OnOtpCompletionListener() {
//            @Override
//            public void onOtpCompleted(String otp) {
//                Log.d("onOtpCompleted=>", otp);
//            }
//        });

    }
}