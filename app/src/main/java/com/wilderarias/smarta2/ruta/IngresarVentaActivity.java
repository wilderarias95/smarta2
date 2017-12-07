package com.wilderarias.smarta2.ruta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class IngresarVentaActivity extends AppCompatActivity {

    private ImageView iBuscarCliente;
    private TextView tPosRuta, tValorVent;
    private EditText eCedula, eNombre, eApellido, eDireccion, eTelefono, eComentario, eCantidad;
    private Spinner sListaProd, sDiasCred;
    private LinearLayout lProdAgre;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myRef, myRef2, myRef3, myRef4, myRef5;

    private RutaData rutaData;
    private ClienteData clienteData = new ClienteData();
    private ArrayList<ProductoData> productoData = new ArrayList<>();
    private ArrayList<ProductosVenta> productosVentas = new ArrayList<>();
    private AdaptadorProductosSpinner adaptadorProductosSpinner;

    private long posRuta, idSucursal, idFactura, valorVenta = 0, ano, mes, dia, contClientes, contProVent;
    private String idCliente;
    //si cliente existe opcExis=1 si no opcExis=0, contArti contador de articulos en la lista de la venta
    private int opcExis = 0, posArti = 0, idArti = 1, posLapso = 0, contAlmacClient = 0, contAlmacAP = 0,
            apControl = 0, contAlmacPV = 0, pvControl = 0, contPV = 0;
    private String lapso[] = new String[]{"30", "24", "0"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_venta);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null) {
            posRuta = getIntent().getLongExtra("posRuta", 0);
            idSucursal = getIntent().getLongExtra("idSucursal", 0);
        }

        myRef = firebaseDatabase.getReference("cliente");
        myRef2 = firebaseDatabase.getReference().child("articulo_producto");
        myRef3 = firebaseDatabase.getReference().child("producto");
        myRef4 = firebaseDatabase.getReference().child("venta");
        myRef5 = firebaseDatabase.getReference("productos_venta");

        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                idFactura = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contProVent = dataSnapshot.getChildrenCount();
                Log.i("sizez", "" + contProVent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        iBuscarCliente = findViewById(R.id.iBuscarCliente);
        tPosRuta = findViewById(R.id.tPosRuta);
        eCedula = findViewById(R.id.eCedula);
        eNombre = findViewById(R.id.eNombre);
        eApellido = findViewById(R.id.eApellido);
        eDireccion = findViewById(R.id.eDireccion);
        eTelefono = findViewById(R.id.eTelefono);
        eComentario = findViewById(R.id.eComentario);
        sListaProd = findViewById(R.id.sListaProd);
        sDiasCred = findViewById(R.id.sDiasCred);
        eCantidad = findViewById(R.id.eCantidad);
        tValorVent = findViewById(R.id.tValorVenta);
        lProdAgre = findViewById(R.id.lProdAgre);

        tPosRuta.setText(String.valueOf(posRuta + 1));

        iBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCliente = eCedula.getText().toString();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot clienteSnapshot : dataSnapshot.getChildren()) {
                            if (Objects.equals(clienteSnapshot.child("idCliente").getValue().toString(), idCliente)) {
                                clienteData.setIdCliente(clienteSnapshot.child("idCliente").getValue().toString());
                                clienteData.setNombreC(clienteSnapshot.child("nombreC").getValue().toString());
                                clienteData.setApellidoC(clienteSnapshot.child("apellidoC").getValue().toString());
                                clienteData.setDireccionC(clienteSnapshot.child("direccionC").getValue().toString());
                                clienteData.setTelefonoC(Long.parseLong(clienteSnapshot.child("telefonoC").getValue().toString()));
                                clienteData.setComentarioC(clienteSnapshot.child("comentarioC").getValue().toString());
                                opcExis = 1;
                            }
                        }
                        if (opcExis == 1) {
                            eNombre.setText(clienteData.getNombreC());
                            eApellido.setText(clienteData.getApellidoC());
                            eDireccion.setText(clienteData.getDireccionC());
                            eTelefono.setText(String.valueOf(clienteData.getTelefonoC()));
                            eComentario.setText(clienteData.getComentarioC());
                            eNombre.setEnabled(false);
                            eApellido.setEnabled(false);
                            eDireccion.setEnabled(false);
                            eTelefono.setEnabled(false);
                            eComentario.setEnabled(false);
                            eCedula.setEnabled(false);
                            iBuscarCliente.setEnabled(false);
                        } else {
                            Toast.makeText(getApplicationContext(), "Cliente no existe.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot prodSnapshot : dataSnapshot.getChildren()) {
                    if (Long.parseLong(prodSnapshot.child("cantidadDisponible").getValue().toString()) > 0) {
                        productoData.add(prodSnapshot.getValue(ProductoData.class));
                    }
                }

                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int lengProdDis = productoData.size();
                        for (int i = 0; i < lengProdDis; i++) {
                            for (DataSnapshot nombProdSnapshot : dataSnapshot.getChildren()) {
                                if (Objects.equals(productoData.get(i).getCodigoP()
                                        , nombProdSnapshot.child("codigoP").getValue().toString())) {
                                    productoData.get(i).setNombreP(nombProdSnapshot.child("nombreP").getValue().toString());
                                }
                            }
                        }
                        Collections.sort(productoData, new Comparator<ProductoData>() {
                            @Override
                            public int compare(ProductoData o1, ProductoData o2) {
                                //return new Integer((int) o1.getNumeroA()).compareTo(new Integer((int) o2.getNumeroA()));
                                return new String(o1.getNombreP()).compareTo(new String(o2.getNombreP()));
                            }
                        });
                        adaptadorProductosSpinner = new AdaptadorProductosSpinner(getApplicationContext(), productoData);
                        sListaProd.setAdapter(adaptadorProductosSpinner);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lapso);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDiasCred.setAdapter(arrayAdapter);
        sDiasCred.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posLapso = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sListaProd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posArti = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    @SuppressLint("ResourceAsColor")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAceptar:
                if (valorVenta > 0) {

                    Calendar nowCalendar = Calendar.getInstance();
                    ano = nowCalendar.get(Calendar.YEAR);
                    mes = (nowCalendar.get(Calendar.MONTH) + 1);
                    dia = nowCalendar.get(Calendar.DAY_OF_MONTH);

                    if (opcExis == 0) {
                        if (Objects.equals(eCedula.getText().toString(), "") ||
                                Objects.equals(eNombre.getText().toString(), "") ||
                                Objects.equals(eApellido.getText().toString(), "") ||
                                Objects.equals(eTelefono.getText().toString(), "") ||
                                Objects.equals(eDireccion.getText().toString(), "")) {
                            Toast.makeText(getApplicationContext(), R.string.datosfaltantes, Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            clienteData.setIdCliente(eCedula.getText().toString());
                            clienteData.setNombreC(eNombre.getText().toString());
                            clienteData.setApellidoC(eApellido.getText().toString());
                            clienteData.setDireccionC(eDireccion.getText().toString());
                            clienteData.setTelefonoC(Long.parseLong(eTelefono.getText().toString()));
                            clienteData.setComentarioC(eComentario.getText().toString());
                            clienteData.setAnoRegistroC(ano);
                            clienteData.setMesRegistroC(mes);
                            clienteData.setDiaRegistroC(dia);
                            clienteData.setIdSucursal(idSucursal);
                            clienteData.setIdUsuario(1);//valor quemado tomar en un futuro de rutaFragment
                            clienteData.setTipoIdentificacion("cc");
                        }
                    }

                    rutaData = new RutaData(0, Long.parseLong(lapso[posLapso]), posLapso == 2 ? "cancelado" : "activo",
                            clienteData.getIdCliente(), idFactura, idSucursal, 1, posRuta + 1, valorVenta, valorVenta,
                            ano, mes, dia);

                    for (pvControl = 0; pvControl < idArti - 1; pvControl++) {
                        if (productosVentas.get(pvControl).getConf() == 1) {
                            productosVentas.get(pvControl).setNumeroP((long) contAlmacPV);
                            contAlmacPV++;
                        } else {
                            productosVentas.remove(pvControl);
                            idArti -= 1;
                            pvControl -= 1;
                        }
                    }

                    myRef5.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            contProVent = dataSnapshot.getChildrenCount();
                            int pv = (int) contProVent + 1;
                            Log.i("pv", "" + pv);
                            if (contPV < productosVentas.size()) {
                                myRef5.child("pv" + pv).setValue(productosVentas.get(contPV));
                                contPV++;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (apControl = 0; apControl < productosVentas.size(); apControl++) {
                                for (DataSnapshot cantDisSnapshot : dataSnapshot.getChildren()) {
                                    if (Objects.equals(productosVentas.get(apControl).getCodigoP(),
                                            cantDisSnapshot.child("codigoP").getValue().toString()) &&
                                            productosVentas.get(apControl).getCodigoAP() == Long.
                                                    parseLong(cantDisSnapshot.child("codigoAP").
                                                            getValue().toString())) {
                                        Map<String, Object> newData = new HashMap<>();
                                        long nuevoSaldo = Long.parseLong(cantDisSnapshot.child("cantidadDisponible").
                                                getValue().toString()) - productosVentas.get(apControl).getCantidadP();
                                        newData.put("cantidadDisponible", nuevoSaldo);
                                        if (contAlmacAP < idArti - 1) {
                                            myRef2.child(cantDisSnapshot.getKey()).updateChildren(newData);
                                            contAlmacAP++;
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    contClientes = dataSnapshot.getChildrenCount();
                                    if (contAlmacClient < 1) {
                                        Log.i("a", "creando Cli");
                                        int c = (int) contClientes + 1;
                                        if (opcExis==0) {
                                            myRef.child("c" + c).setValue(clienteData);
                                        }
                                        int v = (int) idFactura + 1;
                                        myRef4.child("v" + v).setValue(rutaData);
                                        contAlmacClient++;
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    };
                    timer.schedule(timerTask, 1000);

                    Log.i("hola", "como estas");
                    onBackPressed();

                } else {
                    Toast.makeText(getApplicationContext(), R.string.cant_no_valida, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bCancelar:
                onBackPressed();
                break;
            case R.id.iAgregar:
                if (productoData.get(posArti).getCantidadDisponible() > 0 && Integer.parseInt(eCantidad
                        .getText().toString())<productoData.get(posArti).getCantidadDisponible()) {
                    int cantProd = eCantidad.getText().toString().equals("") ? 0 : Integer.parseInt(eCantidad
                            .getText().toString());
                    if (cantProd != 0) {
                        productosVentas.add(new ProductosVenta(cantProd, productoData
                                .get(posArti).getCodigoAP(), idFactura
                                , idSucursal, productoData.get(posArti).getCodigoP(), 1, (int) productoData.get(posArti).getValorNeto()));
                        valorVenta += productoData.get(posArti).getValorNeto() * cantProd;

                        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout
                                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5));
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setPadding(5, 2, 5, 2);
                        linearLayout.setId(idArti);

                        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout
                                .LayoutParams.MATCH_PARENT);

                        TextView textViewCodi = new TextView(getApplicationContext());
                        textViewCodi.setTextColor(R.color.black);
                        textViewCodi.setTextSize(20);
                        textViewCodi.setText(productoData.get(posArti).getCodigoP() + "-"
                                + productoData.get(posArti).getCodigoAP());
                        textViewCodi.setPadding(0, 0, 25, 0);


                        LinearLayout.LayoutParams nombreParams = new LinearLayout.LayoutParams(
                                0, LinearLayout
                                .LayoutParams.MATCH_PARENT, 4);

                        TextView textViewNomb = new TextView(getApplicationContext());
                        textViewNomb.setTextColor(R.color.black);
                        textViewNomb.setTextSize(20);
                        textViewNomb.setText(productoData.get(posArti).getNombreP());


                        TextView textViewCant = new TextView(getApplicationContext());
                        textViewCant.setTextColor(R.color.black);
                        textViewCant.setTextSize(20);
                        textViewCant.setText("" + cantProd);
                        textViewCant.setPadding(0, 0, 25, 0);

                        final LinearLayout.LayoutParams valorParams = new LinearLayout.LayoutParams(
                                0, LinearLayout
                                .LayoutParams.MATCH_PARENT, 1);


                        TextView textViewValo = new TextView(getApplicationContext());
                        textViewValo.setTextColor(R.color.black);
                        textViewValo.setTextSize(20);
                        textViewValo.setText(String.valueOf(productoData.get(posArti).getValorNeto()));

                        ImageView imageButtonremo = new ImageView(getApplicationContext());
                        imageButtonremo.setImageResource(R.drawable.ic_clear);
                        imageButtonremo.setPadding(0, 0, 20, 0);
                        imageButtonremo.setId(idArti + 10);
                        imageButtonremo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                @SuppressLint("ResourceType") int idLin = v.getId() - 10;
                                LinearLayout linearLayout1 = findViewById(idLin);
                                linearLayout1.setVisibility(View.GONE);

                                productosVentas.get(idLin - 1).setConf(0);
                                valorVenta -= productosVentas.get(idLin - 1).getValorNeto() * productosVentas.get(idLin - 1).getCantidadP();
                                tValorVent.setText("$ " + valorVenta);
                            }
                        });


                        linearLayout.addView(textViewCodi, viewParams);
                        linearLayout.addView(textViewNomb, nombreParams);
                        linearLayout.addView(textViewCant, viewParams);
                        linearLayout.addView(textViewValo, valorParams);
                        linearLayout.addView(imageButtonremo, viewParams);
                        lProdAgre.addView(linearLayout);

                        tValorVent.setText("$ " + valorVenta);
                        idArti++;
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.cant_no_valida, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.agotado, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
