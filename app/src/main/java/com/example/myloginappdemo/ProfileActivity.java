package com.example.myloginappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, ageEditText;
    private Button saveButton;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("Profile Activity");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("User Data");

        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        saveButton = findViewById(R.id.saveButtonId);
        progressBar = findViewById(R.id.progressbarId);

        saveButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profileMenuId) {
            finish();
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.changePasswordMenuId) {
            finish();
            Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.signOutMenuId) {
            mAuth.signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        saveData();
    }

    private void saveData() {

        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

        UserData userDataObj = new UserData(name, age);

        databaseReference.child(key).setValue(userDataObj);
        Toast.makeText(this, "User Data is added", Toast.LENGTH_SHORT).show();
    }
}































