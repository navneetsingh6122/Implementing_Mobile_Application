package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {
    EditText regemail , regpass , regphone;
    Button register;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regemail = (EditText)findViewById(R.id.register_email);
        regpass= (EditText)findViewById(R.id.register_password);
        regphone= (EditText)findViewById(R.id.register_phone);

        register = (Button)findViewById(R.id.register_button);
        fAuth = FirebaseAuth.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regemail.getText().toString().trim();
                String password = regpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    regemail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    regpass.setError("Password id required");
                    return;
                }
                if(password.length()<6){
                    regpass.setError("Password should be greater than 6 characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signupActivity.this , "User created" , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (signupActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(signupActivity.this , "Error!! :-" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}