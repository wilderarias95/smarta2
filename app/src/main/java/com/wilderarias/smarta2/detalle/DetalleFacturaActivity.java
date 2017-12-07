package com.wilderarias.smarta2.detalle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.wilderarias.smarta2.R;
import com.wilderarias.smarta2.ruta.IngresarAbonoDialogFragment;
import com.wilderarias.smarta2.ruta.IngresarVentaActivity;
import com.wilderarias.smarta2.ruta.RutaData;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class DetalleFacturaActivity extends AppCompatActivity {


    private RutaData rutaData = new RutaData();
    private TextView tPosRuta, tNombreC, tIdent, tDireccion, tTelefono, tFechaFactura, tValorVenta, tCuota, tLapso, tComentario, tSaldo, tSaldoAtrasado, tCuotasAbonadas, tCuotasAtrasadas, tIdFact;
    private Button bProductos, bCuotasAbo;
    private long idFactura;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();;
    DatabaseReference myRef,myRef2;

    public DetalleFacturaActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_factura);
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
        bCuotasAbo = findViewById(R.id.bCuotasAbon);
        bProductos = findViewById(R.id.bProductos);
        tIdFact = findViewById(R.id.tIdFact);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (getIntent() != null) {
           // String jsonData = getIntent().getStringExtra("data");
            idFactura=getIntent().getLongExtra("idFactura",-1);
            //Gson gson = new Gson();
            //rutaData = gson.fromJson(jsonData, RutaData.class);
            //String pos = String.valueOf(rutaData.getPos());
        }

        myRef=firebaseDatabase.getReference().child("venta");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ventaSnapshot:dataSnapshot.getChildren()){
                    if(Long.parseLong(ventaSnapshot.child("idFacturaVenta").getValue().toString())==idFactura){
                        rutaData=ventaSnapshot.getValue(RutaData.class);
                    }
                }
                tPosRuta.setText("" + rutaData.getPos());
                tSaldo.setText(String.valueOf(rutaData.getSaldoCredito()));
                tFechaFactura.setText(rutaData.getDiaRegistroV() + "/" + rutaData.getMesRegistroV() + "/" + rutaData.getAnoRegistroV());
                tValorVenta.setText(String.valueOf(rutaData.getValorVenta()));
                tCuota.setText(String.valueOf(rutaData.getValorVenta() / rutaData.getDiasCredito()));
                tLapso.setText(String.valueOf(rutaData.getDiasCredito()));
                float numAbonos = (float) (rutaData.getValorVenta() - rutaData.getSaldoCredito()) / (rutaData.getValorVenta() / rutaData.getDiasCredito());
                tCuotasAbonadas.setText(String.format("%.2f", numAbonos));
                tIdFact.setText(String.valueOf(rutaData.getIdFacturaVenta()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef2=firebaseDatabase.getReference().child("cliente");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot clienteSnapshot : dataSnapshot.getChildren()) {
                        String idUsu = clienteSnapshot.child("idCliente").getValue().toString();
                        if (Objects.equals(idUsu,rutaData.getIdCliente())) {
                            rutaData.setNombreC(clienteSnapshot.child("nombreC").getValue().toString());
                            rutaData.setApellidoC(clienteSnapshot.child("apellidoC").getValue().toString());
                            rutaData.setComentarioC(clienteSnapshot.child("comentarioC").getValue().toString());
                            rutaData.setDireccionC(clienteSnapshot.child("direccionC").getValue().toString());
                            rutaData.setTelefonoC((long) clienteSnapshot.child("telefonoC").getValue());
                        }
                    }
                tNombreC.setText(rutaData.getNombreC() + " " + rutaData.getApellidoC());
                tIdent.setText(rutaData.getIdCliente());
                tDireccion.setText(rutaData.getDireccionC());
                tTelefono.setText(String.valueOf(rutaData.getTelefonoC()));
                tComentario.setText(rutaData.getComentarioC());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        bCuotasAbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VerAbonosActivity.class);
                intent.putExtra("nombreC", rutaData.getNombreC() + " " + rutaData.getApellidoC());
                String fecha = "" + rutaData.getDiaRegistroV() + "/" + rutaData.getMesRegistroV() + "/" + rutaData.getAnoRegistroV();
                intent.putExtra("fechaFactura", fecha);
                intent.putExtra("valorV", rutaData.getValorVenta());
                intent.putExtra("saldoV", rutaData.getSaldoCredito());
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                startActivity(intent);
            }
        });

        bProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleFacturaActivity.this, VerProductosActivity.class);
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                intent.putExtra("nombreC", rutaData.getNombreC() + " " + rutaData.getApellidoC());
                String fecha = "" + rutaData.getDiaRegistroV() + "/" + rutaData.getMesRegistroV() + "/" + rutaData.getAnoRegistroV();
                intent.putExtra("fechaFactura", fecha);
                intent.putExtra("valorV", rutaData.getValorVenta());
                startActivity(intent);

            }
        });


    }

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detalle_factura, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        String fecha = "" + rutaData.getDiaRegistroV() + "/" + rutaData.getMesRegistroV() + "/" + rutaData.getAnoRegistroV();

        switch (item.getItemId()) {
            case R.id.actCliente:
                intent = new Intent(DetalleFacturaActivity.this, ActualizarClienteActivity.class);
                intent.putExtra("idCliente", rutaData.getIdCliente());
                intent.putExtra("direccion", rutaData.getDireccionC());
                intent.putExtra("telefono", rutaData.getTelefonoC());
                intent.putExtra("comentario", rutaData.getComentarioC());
                startActivity(intent);
                //startActivityForResult(intent, 1);  //1:actualizarcliente
                return true;
            case R.id.verProductos:
                intent = new Intent(DetalleFacturaActivity.this, VerProductosActivity.class);
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                intent.putExtra("nombreC", rutaData.getNombreC() + " " + rutaData.getApellidoC());
                intent.putExtra("fechaFactura", fecha);
                intent.putExtra("valorV", rutaData.getValorVenta());
                startActivity(intent);
                return true;
            case R.id.verAbononos:
                intent = new Intent(DetalleFacturaActivity.this, VerAbonosActivity.class);
                intent.putExtra("nombreC", rutaData.getNombreC() + " " + rutaData.getApellidoC());
                intent.putExtra("fechaFactura", fecha);
                intent.putExtra("valorV", rutaData.getValorVenta());
                intent.putExtra("saldoV", rutaData.getSaldoCredito());
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                startActivity(intent);
                return true;
            case R.id.nuevaVenta:
                intent = new Intent(DetalleFacturaActivity.this, IngresarVentaActivity.class);
                intent.putExtra("posRuta",rutaData.getPos());
                intent.putExtra("idSucursal",rutaData.getIdSucursal());
                startActivity(intent);
                return true;
            case R.id.ingresarAbono:
                IngresarAbonoDialogFragment ingresarAbonoDialogFragment = new IngresarAbonoDialogFragment(rutaData.getIdFacturaVenta(), rutaData.getIdSucursal());
                ingresarAbonoDialogFragment.show(getSupportFragmentManager(), "ingresarAbono");
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
