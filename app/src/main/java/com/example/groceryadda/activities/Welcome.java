package com.example.groceryadda.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.groceryadda.R;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    Button btn_register,btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        int status1= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int status2=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int status3=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        if(status1== PackageManager.PERMISSION_GRANTED&&status2==PackageManager.PERMISSION_GRANTED&&status3==PackageManager.PERMISSION_GRANTED)
        {
            btn_register=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);
        btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(Welcome.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Welcome.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION},123);
        }





    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            Intent i=new Intent(this,DashboardActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED)
        { btn_register=findViewById(R.id.btn_register);
            btn_login=findViewById(R.id.btn_login);

            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(Welcome.this,RegisterActivity.class);
              startActivity(intent);
}
          });

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Welcome.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {

            Toast.makeText(this,"Kindly Allow The Permission",Toast.LENGTH_LONG).show();
            finish();
        }
    }


}
