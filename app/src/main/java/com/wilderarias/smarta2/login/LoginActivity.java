package com.wilderarias.smarta2.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;
import com.wilderarias.smarta2.main.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private Button bIngresar;
    private EditText eUsername, ePassword;
    private String sUsername, sPassword;
    private ArrayList<User> users = new ArrayList<User>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bIngresar = findViewById(R.id.bIngresar);
        eUsername = findViewById(R.id.eUsername);
        ePassword = findViewById(R.id.ePassword);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("usuario");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    users.add(userSnapshot.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("BasedeDatosClass", "onClickError");
            }
        });

        bIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //eUsername.setText(users.get(0).getNombreU());
                //ePassword.setText(users.get(1).getNombreU());
                if (validarUsuario() == 1) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("nombreUsuario",sUsername);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Datos no validos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int validarUsuario() {
        sUsername = eUsername.getText().toString();
        sPassword = ePassword.getText().toString();
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(sUsername, users.get(i).getNombreU())) {
                if (Objects.equals(sPassword, users.get(i).getContraseña())) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
