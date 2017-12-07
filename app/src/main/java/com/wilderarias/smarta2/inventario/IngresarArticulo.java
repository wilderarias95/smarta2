package com.wilderarias.smarta2.inventario;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.Calendar;
import java.util.Objects;

/**
 * Created by USUARIO on 07/12/2017.
 */

public class IngresarArticulo extends AppCompatActivity {
    EditText eCodigo, eNombre, eDescripcion, eCantidad, eValorCapital, eValorNeto;
    ImageView iBuscarProd;

    int codExis=0,contAP=0,contAlma=0;
    long tamP,tamAP;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef, myRef2;
    ProductoInfo productoInfo=new ProductoInfo();
    ArticuloProductoInfo articuloProductoInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_articulo);
        setTitle(R.string.ingresararticulo);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("producto");
        myRef2 = firebaseDatabase.getReference("articulo_producto");

        eCodigo = findViewById(R.id.eCodigo);
        eNombre = findViewById(R.id.eNombre);
        eDescripcion = findViewById(R.id.eDescripcion);
        eCantidad = findViewById(R.id.eCantidad);
        eValorCapital = findViewById(R.id.eValorCapital);
        eValorNeto = findViewById(R.id.eValorNeto);
        iBuscarProd = findViewById(R.id.iBuscarProducto);
        iBuscarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tamP=dataSnapshot.getChildrenCount()+1;
                        for (DataSnapshot prodSnapshot : dataSnapshot.getChildren()) {
                            if (Objects.equals(eCodigo.getText().toString(), prodSnapshot.child("codigoP").getValue().toString())) {
                                productoInfo.setCodigoP(eCodigo.getText().toString());
                                productoInfo.setNombreP(prodSnapshot.child("nombreP").getValue().toString());
                                productoInfo.setDescripcionP(prodSnapshot.child("descripcionP").getValue().toString());
                                codExis=1;
                            }
                        }
                        if (codExis==1){
                                eNombre.setText(productoInfo.getNombreP());
                                eDescripcion.setText(productoInfo.getDescripcionP());
                                eCodigo.setEnabled(false);
                                eNombre.setEnabled(false);
                                eDescripcion.setEnabled(false);
                        }else{
                            Toast.makeText(getApplicationContext(), R.string.prod_no_existente, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAceptar:
                Calendar nowCalendar = Calendar.getInstance();
                final int ano = nowCalendar.get(Calendar.YEAR);
                final int mes = nowCalendar.get(Calendar.MONTH) + 1;
                final int dia = nowCalendar.get(Calendar.DAY_OF_MONTH);

                final long cantidad=Long.parseLong(eCantidad.getText().toString());
                final long valorCapital=Long.parseLong(eValorCapital.getText().toString());
                final long valorNeto=Long.parseLong(eValorNeto.getText().toString());

                if (codExis==0){
                    if (Objects.equals(eCodigo.getText().toString(),"") ||
                            Objects.equals(eNombre.getText().toString(),"")||
                            cantidad<=0 || valorCapital<=0||valorNeto<=0){
                        Toast.makeText(getApplicationContext(), R.string.datosfaltantes, Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                       productoInfo.setCodigoP(eCodigo.getText().toString());
                       productoInfo.setNombreP(eNombre.getText().toString());
                       productoInfo.setDescripcionP(Objects.equals(eDescripcion.getText().toString(),"")?"":eDescripcion.getText().toString());
                    }
                }

                productoInfo.setAnoRegistroP(ano);
                productoInfo.setDiaRegistroP(dia);
                productoInfo.setIdSucursal(1);//
                productoInfo.setMesRegistroP(mes);

                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tamAP=dataSnapshot.getChildrenCount()+1;
                        for (DataSnapshot artProdSnapshot:dataSnapshot.getChildren()){
                            if (Objects.equals(productoInfo.getCodigoP(),artProdSnapshot.child("codigoP").getValue().toString())){
                                contAP++;
                            }
                        }
                        articuloProductoInfo=new ArticuloProductoInfo(ano,cantidad,cantidad,contAP,dia,
                                1,1,mes,valorCapital,valorNeto,productoInfo.getCodigoP());

                        if (contAlma<1) {
                            if (codExis == 0) {
                                myRef.child("p" + tamP).setValue(productoInfo);
                            }
                            myRef2.child("ap"+tamAP).setValue(articuloProductoInfo);
                            contAlma++;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                onBackPressed();
                break;
            case R.id.bCancelar:
                onBackPressed();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
