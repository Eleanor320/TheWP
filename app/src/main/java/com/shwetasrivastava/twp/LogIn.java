package com.shwetasrivastava.twp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LogIn Activity", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LogIn Activity", "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(final String email, String password) {
        Log.d("LogIn", "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Log In", "signInWithEmail:success");
                            Intent i;
                            switch(email.toString()){
                                case "a@mail.com": i=new Intent(getApplicationContext(),admin_page.class);
                                                    startActivity(i);
                                                    break;
                                case "v@mail.com": i=new Intent(getApplicationContext(),volunteer_page.class);
                                                    startActivity(i);
                                                    break;
                                default: Toast.makeText(LogIn.this, "Cannot start intent!",Toast.LENGTH_SHORT).show();
                                            break;

                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Log In", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;
        EditText email=(EditText)findViewById(R.id.email);
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        EditText password = (EditText)findViewById(R.id.password);
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }
        return valid;
    }

    public void onClick(View view){

        String email=((EditText)findViewById(R.id.email)).getText().toString();
        String password=((EditText)findViewById(R.id.password)).getText().toString();
        signIn(email,password);

    }
}

