package com.example.tejas.logindiary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Username =   findViewById(R.id.etUsername);
        Password =   findViewById(R.id.etPassword);
        Login    =   findViewById(R.id.btnLogin);

        firebaseAuth    = FirebaseAuth.getInstance();
        progressDialog  = new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user != null)
        {
            finish();
            startActivity(new Intent(MainActivity.this, PersonalHomepage.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().toString().matches("")|| Password.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Info daalo", Toast.LENGTH_SHORT).show();
                }
                else {
                    validate(Username.getText().toString(), Password.getText().toString());
                }
            }
        });

    }

    private void validate(String userName, String userPassword)
    {
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Kya haal hain?", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, PersonalHomepage.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(MainActivity.this, FakeActivity.class));
                }
            }
        });
    }
}
