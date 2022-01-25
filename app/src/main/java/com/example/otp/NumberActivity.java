package com.example.otp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.otp.databinding.ActivityNumberBinding;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//
//import java.util.concurrent.TimeUnit;
//
//public class NumberActivity extends AppCompatActivity {
//    ActivityNumberBinding binding;
//    FirebaseAuth auth;
//    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityNumberBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        auth=FirebaseAuth.getInstance();
//
//        binding.btnSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (binding.etPhone.getText().toString() == "")
//                    Toast.makeText(NumberActivity.this, "Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
//                else if (binding.etPhone.getText().toString().trim().length() != 10)
//                    Toast.makeText(NumberActivity.this, "Enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
//                else {
//                    otp();
//                }
//
//            }
//        });
//    }
//
//    private void otp() {
//        binding.progressBar.setVisibility(View.VISIBLE);
//        binding.btnSend.setVisibility(View.GONE);
//
//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//                binding.progressBar.setVisibility(View.GONE);
//                binding.btnSend.setVisibility(View.VISIBLE);
//                Toast.makeText(NumberActivity.this, "ikhbjhkjh\n\n\n"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                binding.progressBar.setVisibility(View.GONE);
//                binding.btnSend.setVisibility(View.VISIBLE);
//                Intent i=new Intent(NumberActivity.this,VerifyActivity.class);
//                i.putExtra("phone",binding.etPhone.getText().toString().trim());
//                i.putExtra("verificationId",s);
//                startActivity(i);
//            }
//        };
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(auth)
//                        .setPhoneNumber("+91"+binding.etPhone.getText().toString().trim())       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//}



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otp.databinding.ActivityNumberBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class NumberActivity extends AppCompatActivity {

    private ActivityNumberBinding binding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(NumberActivity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (binding.etPhone.getText().toString().trim().length() != 10) {
                    Toast.makeText(NumberActivity.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    otpSend();
                }
            }
        });
    }

    private void otpSend() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnSend.setVisibility(View.INVISIBLE);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                binding.progressBar.setVisibility(View.GONE);
                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(NumberActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                binding.progressBar.setVisibility(View.GONE);
                binding.btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(NumberActivity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NumberActivity.this, VerifyActivity.class);
                intent.putExtra("phone", binding.etPhone.getText().toString().trim());
                intent.putExtra("verificationId", verificationId);
                startActivity(intent);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + binding.etPhone.getText().toString().trim())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}








