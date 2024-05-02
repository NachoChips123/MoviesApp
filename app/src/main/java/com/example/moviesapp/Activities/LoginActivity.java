package com.example.moviesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviesapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText userEtd;
    private EditText passEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEtd = findViewById(R.id.editTextText);
        passEdt = findViewById(R.id.editTextPassWord);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            if(userEtd.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Fill in both your UserName and Password!", Toast.LENGTH_SHORT).show();
            }
            else if(userEtd.getText().toString().equals("test") && passEdt.getText().toString().equals("test")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
            else {
                Toast.makeText(LoginActivity.this , "Invalid UserName or Password!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}