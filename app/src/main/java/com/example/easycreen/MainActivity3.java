package com.example.easycreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    Button btn_add;
    EditText corre, contrasenaa, conf_contraa, user, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        corre = findViewById(R.id.corre);
        contrasenaa = findViewById(R.id.contrasenaa);
        conf_contraa = findViewById(R.id.conf_contraa);
        btn_add = findViewById(R.id.btn_add);
        user = findViewById(R.id.user);
        tel = findViewById(R.id.tel);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailUser = corre.getText().toString().trim();
                String passUser = contrasenaa.getText().toString().trim();
                String cofconUser = conf_contraa.getText().toString().trim();
                final String username = user.getText().toString().trim();
                final String telephone = tel.getText().toString().trim();

                if (emailUser.isEmpty() || passUser.isEmpty() || cofconUser.isEmpty() || username.isEmpty() || telephone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese todos los datos", Toast.LENGTH_SHORT).show();
                } else if (!passUser.equals(cofconUser)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                } else {
                    // Registrando un nuevo usuario con Firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailUser, passUser)
                            .addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Registro exitoso
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        // Guardar datos del usuario en Firestore
                                        if (user != null) {
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            Map<String, Object> userData = new HashMap<>();
                                            userData.put("username", username);
                                            userData.put("email", emailUser);
                                            userData.put("telephone", telephone);

                                            db.collection("usuarios").document(user.getUid())
                                                    .set(userData)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(MainActivity3.this, "Usuario registrado correctamente y datos guardados en Firestore", Toast.LENGTH_SHORT).show();

                                                                // Luego de registrar al usuario, pasamos al MainActivity2
                                                                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                                                                startActivity(intent);
                                                                finish(); // Esto evita que el usuario pueda volver atrás presionando el botón de atrás
                                                            } else {
                                                                Toast.makeText(MainActivity3.this, "Error al guardar datos en Firestore", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }
                                    } else {
                                        // Si el registro falla, muestra un mensaje al usuario
                                        Toast.makeText(MainActivity3.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
