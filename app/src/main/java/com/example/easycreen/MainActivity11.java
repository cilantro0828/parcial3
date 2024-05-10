package com.example.easycreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class MainActivity11 extends AppCompatActivity {

    ImageView btnBack;
    TextView usuarioTextView, correoTextView, telefonoTextView;
    FirebaseFirestore db;
    ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main11);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        usuarioTextView = findViewById(R.id.usuario_c);
        correoTextView = findViewById(R.id.correo_c);
        telefonoTextView = findViewById(R.id.telefono_c);

        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            listenerRegistration = db.collection("Users").document(user.getUid())
                    .addSnapshotListener((documentSnapshot, e) -> {
                        if (e != null) {
                            // Manejar el error aquí
                            return;
                        }

                        if (documentSnapshot != null && documentSnapshot.exists()) {
                            String usuario = documentSnapshot.getString("username");
                            String correo = documentSnapshot.getString("email");
                            String telefono = documentSnapshot.getString("telephone");
                            usuarioTextView.setText(usuario);
                            correoTextView.setText(correo);
                            telefonoTextView.setText(telefono);
                        }
                    });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listenerRegistration != null) {
            listenerRegistration.remove();
        }
    }
}
