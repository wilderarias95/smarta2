package com.wilderarias.smarta2.ruta;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class VerProductosActivity extends AppCompatActivity {

    private TextView tIdFact,tNombreC,tValorVenta,tFechaFactura;
    private String nombreC,fechaFactura;
    private long idFact,valorVenta;
    private ListView lProd;
    private ArrayList<ProductosData> productosData=new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef,myRef2,myRef3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.verproductos);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getIntent()!=null){
            idFact=getIntent().getLongExtra("idFactura",-1);
            valorVenta=getIntent().getLongExtra("valorV",-1);
            nombreC=getIntent().getStringExtra("nombreC");
            fechaFactura=getIntent().getStringExtra("fechaFactura");
        }
        tIdFact=findViewById(R.id.tIdFact);
        tNombreC=findViewById(R.id.tNombreC);
        tValorVenta=findViewById(R.id.tValorVenta);
        tFechaFactura=findViewById(R.id.tFechaFactura);
        lProd=findViewById(R.id.lProductos);

        tIdFact.setText(String.valueOf(idFact));
        tNombreC.setText(nombreC);
        tValorVenta.setText(String.valueOf(valorVenta));
        tFechaFactura.setText(fechaFactura);

        final AdaptadorProductos adaptadorProductos=new AdaptadorProductos(getApplicationContext(),productosData);

        lProd.setAdapter(adaptadorProductos);

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference().child("productos_venta");
        myRef2=firebaseDatabase.getReference().child("producto");
        myRef3=firebaseDatabase.getReference().child("articulo_producto");
        Timer timer=new Timer();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot prodSnapshot:dataSnapshot.getChildren()){
                    Log.i("VerProductosActivity","myRef");
                    if ((long)prodSnapshot.child("idFacturaVenta").getValue()==idFact){
                        productosData.add(prodSnapshot.getValue(ProductosData.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {

                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (int i=0;i<productosData.size();i++){
                            for (DataSnapshot nomProSnapshot:dataSnapshot.getChildren() ){
                                Log.i("VerProductosActivity","myRef2");
                                if (Objects.equals(nomProSnapshot.child("codigoP").getValue().toString(),productosData.get(i).getCodigoP())){
                                    productosData.get(i).setNombreP(nomProSnapshot.child("nombreP").getValue().toString());
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (int j=0;j<productosData.size();j++){
                            for (DataSnapshot valProSnapshot:dataSnapshot.getChildren() ){
                                Log.i("VerProductosActivity","myRef3");
                                if (Objects.equals(valProSnapshot.child("codigoP").getValue().toString(),productosData.get(j).getCodigoP())){
                                    productosData.get(j).setValorNeto((long)valProSnapshot.child("valorNeto").getValue());
                                }
                            }
                        }
                        adaptadorProductos.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        timer.schedule(timerTask,400);
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
