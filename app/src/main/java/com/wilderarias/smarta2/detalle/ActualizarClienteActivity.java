package com.wilderarias.smarta2.detalle;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActualizarClienteActivity extends AppCompatActivity {
    private String direccionC, comentarioC, idCliente;
    private long telefonoC, telefono;
    private EditText eDireccionC, eTelefonoC, eComentario;
    private String direccion, comentario;
    private int cont = 0;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            idCliente = getIntent().getStringExtra("idCliente");
            direccionC = getIntent().getStringExtra("direccion");
            telefonoC = getIntent().getLongExtra("telefono", 0);
            comentarioC = getIntent().getStringExtra("comentario");
        }
        eDireccionC = findViewById(R.id.eDireccion);
        eTelefonoC = findViewById(R.id.eTelefono);
        eComentario = findViewById(R.id.eComentario);

        eDireccionC.setText(direccionC);
        eTelefonoC.setText(String.valueOf(telefonoC));
        eComentario.setText(comentarioC);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void click(View view) {
        int bOpc = view.getId();
        switch (bOpc) {
            case R.id.bAceptar:
                direccion = eDireccionC.getText().toString();
                telefono = Long.parseLong(eTelefonoC.getText().toString());
                comentario = eComentario.getText().toString();

                myRef = firebaseDatabase.getReference("cliente");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot actSnapshot : dataSnapshot.getChildren()) {
                            if (Objects.equals(actSnapshot.child("idCliente").getValue().toString(), idCliente)) {
                                Map<String, Object> newData = new HashMap<>();
                                newData.put("direccionC", direccion);
                                newData.put("telefonoC", telefono);
                                newData.put("comentarioC", comentario);
                                Log.i("ActualizarCliente", "actualizando");
                                if (cont < 1) {
                                    myRef.child(actSnapshot.getKey()).updateChildren(newData);
                                    ++cont;
                                }
                                break;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            case R.id.bCancelar:
                onBackPressed();
                break;
        }
    }
}
