package com.example.firebaseauth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private TextView textSign;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.ediEmail);
        editPassword = findViewById(R.id.ediPassword);
        textSign = findViewById(R.id.signIn);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void loginAuth(View view) {
        final String Email = editEmail.getText().toString().trim();
        String Password = editPassword.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Email Cant be Empty..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Password Cant be Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("User Registering...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    editEmail.setText("");
                    editPassword.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Coudnt Registered..Please Try Again..", Toast.LENGTH_SHORT).show();
                    editEmail.setText("");
                    editPassword.setText("");
                }
            }
        });

    }

    public void signAuth(View view) {


    }
}
