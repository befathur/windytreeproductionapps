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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button btnSignin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        btnSignin = findViewById(R.id.next);
        mAuth = FirebaseAuth.getInstance();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        String email = mEmail.getText().toString();
        String pass = mPassword.getText().toString();

        if (!email.isEmpty()&& Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignActivity.this, "Sign in success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignActivity.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
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
        Intent signupIntent = new Intent(SignActivity.this,SignUpActivity.class);
        startActivity(signupIntent);
        finish();
    }
}