package com.wilderarias.smarta2.ruta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

public class DetalleFacturaActivity extends AppCompatActivity {

    long idFacturaVenta = -1;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
    private RutaData rutaData=new RutaData();
    private TextView tPosRuta, tNombreC, tIdent, tDireccion, tTelefono, tFechaFactura, tValorVenta
            , tCuota, tLapso, tComentario, tSaldo, tSaldoAtrasado, tCuotasAbonadas, tCuotasAtrasadas;

    public DetalleFacturaActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null) {
            idFacturaVenta = getIntent().getLongExtra("idFactura", -1);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("venta");

        tPosRuta = (TextView) findViewById(R.id.tPosRuta);
        tNombreC = findViewById(R.id.tNombreC);
        tIdent = (TextView) findViewById(R.id.tIdentt);
        tDireccion = findViewById(R.id.tDireccion);
        tTelefono = findViewById(R.id.tTelefono);
        tFechaFactura = findViewById(R.id.tFechaFactura);
        tValorVenta = findViewById(R.id.tValorVenta);
        tCuota = findViewById(R.id.tCuota);
        tLapso = findViewById(R.id.tLapso);
        tComentario = findViewById(R.id.tComentario);
        tSaldo = findViewById(R.id.tSaldo);
        tSaldoAtrasado = findViewById(R.id.tSaldoAtrasado);
        tCuotasAbonadas = findViewById(R.id.tCuotasAbon);
        tCuotasAtrasadas = findViewById(R.id.tCuotasAtras);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("v" + (idFacturaVenta + 1)).exists()) {
                    rutaData = dataSnapshot.child("v" + (idFacturaVenta + 1)).getValue(RutaData.class);
                    Log.i("rutaData",rutaData.getIdCliente());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            tIdent.setText(rutaData.getIdCliente());
        //tIdent.setText(rutaData.getIdCliente());
/*
        myRef=firebaseDatabase.getReference().child("cliente");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot clienteSnapshot : dataSnapshot.getChildren()) {
                    String idUsu = clienteSnapshot.child("idCliente").getValue().toString();
                    if (Objects.equals(idUsu, rutaData.getIdCliente())) {
                        rutaData.setNombreC(clienteSnapshot.child("nombreC").getValue().toString());
                        rutaData.setApellidoC(clienteSnapshot.child("apellidoC").getValue().toString());
                        rutaData.setComentarioC(clienteSnapshot.child("comentarioC").getValue().toString());
                        rutaData.setTelefonoC((long) clienteSnapshot.child("telefonoC").getValue());
                        rutaData.setDireccionC(clienteSnapshot.child("direccionC").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tPosRuta.setText(String.valueOf(rutaData.getPos()));
        tNombreC.setText(rutaData.getNombreC()+""+rutaData.getApellidoC());
        tIdent.setText(rutaData.getIdCliente());
        tDireccion.setText(rutaData.getDireccionC());
        tTelefono.setText(String.valueOf(rutaData.getTelefonoC()));
        tFechaFactura.setText(""+rutaData.getDiaRegistroV()+"/"+rutaData.getMesRegistroV()+"/"+rutaData.getAnoRegistroV());
        tValorVenta.setText(String.valueOf(rutaData.getValorVenta()));
        tLapso.setText(String.valueOf(rutaData.getDiasCredito()));
        tCuota.setText(String.valueOf(rutaData.getValorVenta()/rutaData.getDiasCredito()));
        tComentario.setText(rutaData.getComentarioC());
        tSaldo.setText(String.valueOf(rutaData.getSaldoCredito())); */



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalle_factura, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.actCliente:
                intent = new Intent(DetalleFacturaActivity.this, ActualizarClienteActivity.class);
                startActivity(intent);
                return true;
            case R.id.verProductos:

                return true;
            case R.id.verAbononos:

                return true;
            case R.id.nuevaVenta:
                intent = new Intent(DetalleFacturaActivity.this, IngresarVentaActivity.class);
                startActivity(intent);
                return true;
            case R.id.ingresarAbono:

                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
