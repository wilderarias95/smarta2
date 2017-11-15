package com.wilderarias.smarta2.ruta;

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

import com.google.gson.Gson;
import com.wilderarias.smarta2.R;

public class DetalleFacturaActivity extends AppCompatActivity {


    private RutaData rutaData = new RutaData();
    private TextView tPosRuta, tNombreC, tIdent, tDireccion, tTelefono, tFechaFactura, tValorVenta, tCuota, tLapso, tComentario, tSaldo, tSaldoAtrasado, tCuotasAbonadas, tCuotasAtrasadas, tIdFact;

    private Button bProductos, bCuotasAbo;

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
            String jsonData = getIntent().getStringExtra("data");
            Gson gson = new Gson();
            rutaData = gson.fromJson(jsonData, RutaData.class);
            String pos = String.valueOf(rutaData.getPos());
        }

        tPosRuta.setText("" + (rutaData.getPos() + 1));
        tSaldo.setText(String.valueOf(rutaData.getSaldoCredito()));
        tNombreC.setText(rutaData.getNombreC() + " " + rutaData.getApellidoC());
        tIdent.setText(rutaData.getIdCliente());
        tDireccion.setText(rutaData.getDireccionC());
        tFechaFactura.setText(rutaData.getDiaRegistroV() + "/" + rutaData.getMesRegistroV() + "/" + rutaData.getAnoRegistroV());
        tValorVenta.setText(String.valueOf(rutaData.getValorVenta()));
        tCuota.setText(String.valueOf(rutaData.getValorVenta() / rutaData.getDiasCredito()));
        tTelefono.setText(String.valueOf(rutaData.getTelefonoC()));
        tLapso.setText(String.valueOf(rutaData.getDiasCredito()));
        tComentario.setText(rutaData.getComentarioC());
        tIdFact.setText(String.valueOf(rutaData.getIdFacturaVenta()));

        float numAbonos = (float) (rutaData.getValorVenta() - rutaData.getSaldoCredito()) / (rutaData.getValorVenta() / rutaData.getDiasCredito());
        tCuotasAbonadas.setText(String.format("%.2f", numAbonos));

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
        switch (item.getItemId()) {
            case R.id.actCliente:
                intent = new Intent(DetalleFacturaActivity.this, ActualizarClienteActivity.class);
                intent.putExtra("idCliente", rutaData.getIdCliente());
                startActivity(intent);
                return true;
            case R.id.verProductos:
                intent = new Intent(DetalleFacturaActivity.this, VerProductosActivity.class);
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                startActivity(intent);
                return true;
            case R.id.verAbononos:
                intent = new Intent(DetalleFacturaActivity.this, VerAbonosActivity.class);
                intent.putExtra("idFactura", rutaData.getIdFacturaVenta());
                startActivity(intent);
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
