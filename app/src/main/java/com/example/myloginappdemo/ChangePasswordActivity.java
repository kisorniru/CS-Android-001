package com.example.myloginappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText passwordEditText, rePasswordEditText;
    private Button updateButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        this.setTitle("Change Password Activity");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        passwordEditText = findViewById(R.id.passwordEditTextId);
        rePasswordEditText = findViewById(R.id.rePasswordEditTextId);
        updateButton = findViewById(R.id.updateButtonId);
        progressBar = findViewById(R.id.progressbarId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.updateButtonId:
                updatePassword();
                break;
        }
    }

    private void updatePassword() {

        String password = passwordEditText.getText().toString().trim();
        String rePassword = rePasswordEditText.getText().toString().trim();

        if(password.isEmpty())
        {
            passwordEditText.setError("Enter Password");
            passwordEditText.requestFocus();
            return;
        }

        if(rePassword.isEmpty())
        {
            rePasswordEditText.setError("Enter Password");
            rePasswordEditText.requestFocus();
            return;
        }

        if(!password.equals(rePassword))
        {
            rePasswordEditText.setError("Password does not match with each other");
            rePasswordEditText.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            passwordEditText.setError("Minimum length of a password should be 6");
            passwordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

    }
}