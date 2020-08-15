package com.example.groceryadda.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.groceryadda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
static EditText user_name,email,password,re_password,mob_no,otpEt;
Button register,login_phone,sendOtpBtn;
TextView login;
int test1;
    private static final String TAG = "MainActivity";
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String mVerificationId;
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"please login...",Toast.LENGTH_LONG).show();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name=findViewById(R.id.user_name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        re_password=findViewById(R.id.re_password);
        register=findViewById(R.id.register);
        mob_no=findViewById(R.id.mob_no);
        login_phone=findViewById(R.id.login_phone);
        sendOtpBtn=findViewById(R.id.send_otp_btn);
        login=findViewById(R.id.login);
        otpEt=findViewById(R.id.otp_et);
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }


            }
            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                //mResendToken = token;

                // ...
            }
        };




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(RegisterActivity.this, LoginActivity.class));
                    startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setComponent(new ComponentName(RegisterActivity.this,LoginActivity.class));
                startActivity(i);
            }
        });
        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //this will create credentical object with the help of OTP
                String otp=otpEt.getText().toString().trim();

                if (!otp.isEmpty() && mVerificationId!=null) {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpEt.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "mVerif id or otp is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this will send otp
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+917007469297",        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        RegisterActivity.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
            }
        });


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            //start home activity here,login success
                            Toast.makeText(RegisterActivity.this, "welcome", Toast.LENGTH_SHORT).show();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

}
