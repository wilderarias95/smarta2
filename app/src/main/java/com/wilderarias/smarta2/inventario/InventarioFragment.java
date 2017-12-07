package com.wilderarias.smarta2.inventario;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class InventarioFragment extends Fragment {

    ArrayList<InventarioData> data = new ArrayList<InventarioData>();
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef,myRef2;


    public InventarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.inventario_fragment_inventario, container, false);

        recyclerView = view.findViewById(R.id.listInventario);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("articulo_producto");
        myRef2 = firebaseDatabase.getReference().child("producto");

        final AdaptadorInventario adaptadorInventario = new AdaptadorInventario(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adaptadorInventario);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot articuloProducto : dataSnapshot.getChildren()) {
                    if ((long) articuloProducto.child("cantidadDisponible").getValue() != 0) {
                        data.add(articuloProducto.getValue(InventarioData.class));
                    }
                    Collections.sort(data, new Comparator<InventarioData>() {
                        @Override
                        public int compare(InventarioData o1, InventarioData o2) {
                            return new String((String) o1.getCodigoP()).compareTo(new String((String) o2.getCodigoP()));
                        }
                    });
                    //adaptadorInventario.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (int j = 0; j < data.size(); j++) {
                            for (DataSnapshot producto : dataSnapshot.getChildren()) {
                                String codigoP=producto.child("codigoP").getValue().toString();
                                if (Objects.equals(codigoP,data.get(j).getCodigoP())) {
                                    data.get(j).setNombreP(producto.child("nombreP").getValue().toString());
                                    data.get(j).setDescripcionP(producto.child("descripcionP").getValue().toString());
                                    data.get(j).setPos(j+1);
                                }

                                adaptadorInventario.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("onDataChange2","Cancelado Producto");
                    }
                });
            }
        };
        timer.schedule(timerTask,1000);

        adaptadorInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getContext(),IngresarArticulo.class);
              startActivity(intent);
            }
        });
        return view;
    }
}
