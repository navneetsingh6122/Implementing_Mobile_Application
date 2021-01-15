package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.PasswordAuthentication;

public class LoginActivity extends AppCompatActivity {
TextView signup;
Button signin;
EditText email , pass;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (TextView)findViewById(R.id.connectSignup);
        signin  =(Button)findViewById(R.id.signinButton);

        email = (EditText)findViewById(R.id.login_email);
        pass= (EditText)findViewById(R.id.login_password);

        fAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivity = new Intent(LoginActivity.this, signupActivity.class);
                startActivity(newActivity);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    pass.setError("Password id required");
                    return;
                }
                if(Password.length()<6){
                    pass.setError("Password should be greater than 6 characters");
                    return;
                }
                fAuth.signInWithEmailAndPassword(Email , Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this , "User successfully Logged In" , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(LoginActivity.this , "Error!! :- " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}