package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button btn;
    private EditText email;
    private EditText password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        auth  = FirebaseAuth.getInstance();
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        email=binding.editTextText;
        password=binding.editTextTextPassword;
                btn=binding.button;
                btn.setOnClickListener(v ->{
                    auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(this, task -> {
                                if (task.isSuccessful()) {
                                    // User signed in successfully
                                    Toast.makeText(
                                            this,"Connected",Toast.LENGTH_LONG
                                    ).show();
                                    FirebaseUser user = auth.getCurrentUser();

                                } else {
                                    Toast.makeText(
                                            this,"Error",Toast.LENGTH_LONG
                                    ).show();
                                    // If sign-in fails, display an error message
                                }
                            });
                        }

                );

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void  signup (View v){

        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // User signed up successfully
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign-up fails, display an error message
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void  signin (View v)  {
        auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // User signed in successfully
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign-in fails, display an error message
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}