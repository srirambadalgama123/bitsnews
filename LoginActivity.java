package com.example.bitsnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bitsnews.ui.HomeActivityNew;
import com.example.bitsnews.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView question, forget;
    private EditText emailEd, passwordded;
    private Button login;

    private ProgressDialog loader;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        question = findViewById(R.id.loginPageQuestion);
        emailEd = findViewById(R.id.loginEmail);
        passwordded = findViewById(R.id.loginPassword);
        login = findViewById(R.id.loginBtn);
        forget = findViewById(R.id.forgetPageQuestion);

        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEd.getText().toString();
                String password = passwordded.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailEd.setError("Email is required");
                }
                if (TextUtils.isEmpty(password)){
                    passwordded.setError("Password is required");
                }else {
                    loader.setMessage("Login in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if (mAuth.getCurrentUser().isEmailVerified()){
                                    Toast.makeText(LoginActivity.this, "Login is successful. logged in as: " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                    System.out.println("line 84");
                                    Intent intent = new Intent(LoginActivity.this, HomeActivityNew.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Please verify your Email address. " , Toast.LENGTH_SHORT).show();
                                    loader.dismiss();
                                }

                            }else {
                                Toast.makeText(LoginActivity.this, "Login Failed"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}