package com.wilderarias.smarta2.ruta;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VerAbonosActivity extends AppCompatActivity {
    private String nombreC,fechaFactura;
    private long valorV,saldoV,idFactura;
    private TextView tNombreC,tVenta,tSaldo,tFecha,tIdFact;
    private ListView lAbonos;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
    private ArrayList<AbonosData> abonosData=new ArrayList<AbonosData>();
    private AdaptadorAbonos adaptadorAbonos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_abonos);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.verabonos);
        actionBar.setDisplayHomeAsUpEnabled(true);

         if(getIntent()!=null){
            nombreC=getIntent().getStringExtra("nombreC");
            valorV=getIntent().getLongExtra("valorV",-1);
            saldoV=getIntent().getLongExtra("saldoV",-1);
            fechaFactura=getIntent().getStringExtra("fechaFactura");
            idFactura=getIntent().getLongExtra("idFactura",-1);
        }

        tVenta=findViewById(R.id.tValorVenta);
        tSaldo=findViewById(R.id.tSaldoFactura);
        tNombreC=findViewById(R.id.tNombreC);
        tFecha=findViewById(R.id.tFechaFactura);
        lAbonos=findViewById(R.id.lAbonos);
        tIdFact=findViewById(R.id.tIdFact);

        tNombreC.setText(nombreC);
        tVenta.setText(String.valueOf(valorV));
        tSaldo.setText(String.valueOf(saldoV));
        tFecha.setText(fechaFactura);
        tIdFact.setText(String.valueOf(idFactura));

        adaptadorAbonos=new AdaptadorAbonos(getApplicationContext(),abonosData);

        lAbonos.setAdapter(adaptadorAbonos);

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference().child("abono_credito");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot abonosSnapshot:dataSnapshot.getChildren()) {
                    long id= (long) abonosSnapshot.child("idFacturaVenta").getValue();
                    if (id==idFactura){
                    abonosData.add(abonosSnapshot.getValue(AbonosData.class));
                    }
                }
                Collections.sort(abonosData, new Comparator<AbonosData>() {
                    @Override
                    public int compare(AbonosData o1, AbonosData o2) {
                        return new Integer((int) o1.getNumeroA()).compareTo(new Integer((int) o2.getNumeroA()));
                    }
                });
                adaptadorAbonos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
