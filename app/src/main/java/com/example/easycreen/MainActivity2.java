package com.example.easycreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    private Button btn_login, btn_sing;
    private EditText email, password;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_login = findViewById(R.id.btn_log);
        btn_sing = findViewById(R.id.btn_sing);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);

        btn_sing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                // Verifica si el usuario ingresó sus credenciales correctamente
                if (emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    // Autentica al usuario con Firebase Authentication
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailUser, passUser)
                            .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Inicio de sesión exitoso, ahora verifica si el usuario tiene datos en Firebase
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (user != null) {
                                            // El usuario tiene datos en Firebase, ahora puedes ir a MainActivity
                                            startActivity(new Intent(MainActivity2.this, MainActivity.class));
                                            finish();
                                        } else {
                                            // El usuario no tiene datos en Firebase
                                            Toast.makeText(getApplicationContext(), "El usuario no tiene datos en Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Fallo en el inicio de sesión
                                        Toast.makeText(getApplicationContext(), "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
