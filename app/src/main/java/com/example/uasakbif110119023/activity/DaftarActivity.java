package com.example.uasakbif110119023.activity;
/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
tanggal : 13/08/2022
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uasakbif110119023.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnDaftar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.emailDaftar);
        inputPassword = findViewById(R.id.passwordDaftar);
        btnDaftar = findViewById(R.id.btn_daftar2);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (email.equals("") || password.equals("")){
                    Toast.makeText(DaftarActivity.this,
                            "Isi email dan atau password",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(DaftarActivity.this,
                            "Registering...",
                            Toast.LENGTH_SHORT).show();
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                            DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DaftarActivity.this,
                                                "Berhasil Daftar",
                                                Toast.LENGTH_LONG).show();
                                        finish();
                                    } else {
                                        Toast.makeText(DaftarActivity.this,
                                                "Gagal",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                    );
                }
            }
        });
    }
}