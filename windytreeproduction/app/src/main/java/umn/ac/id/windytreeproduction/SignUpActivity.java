package umn.ac.id.windytreeproduction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button btnSignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mEmail = findViewById(R.id.time);
        mPassword = findViewById(R.id.name);
        btnSignup = findViewById(R.id.next);
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });

    }

    private void createUser(){
        String email = mEmail.getText().toString();
        String pass = mPassword.getText().toString();

        if (!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "SignUp Success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, SignActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUpActivity.this, "Can't Sign Up!", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                mPassword.setError("Empty");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty");
        }else {
            mEmail.setError("Filled with the correct Email");
        }
    }

    public void signup(View view) {
    }
}

