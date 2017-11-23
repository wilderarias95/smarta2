package com.wilderarias.smarta2.ruta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.wilderarias.smarta2.R;
import com.wilderarias.smarta2.detalle.DetalleFacturaActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RutaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<RutaData> data = new ArrayList<RutaData>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef, myRef2;
    AdaptadorRuta rutaAdaptadorRuta;

    public RutaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("onCreateView","rutaFragament");
        View view = inflater.inflate(R.layout.ruta_fragment_ruta, container, false);
        recyclerView = view.findViewById(R.id.listRuta);

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("venta");
        myRef2 = firebaseDatabase.getReference().child("cliente");
        rutaAdaptadorRuta = new AdaptadorRuta(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rutaAdaptadorRuta);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    data.add(userSnapshot.getValue(RutaData.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (int i = 0; i < data.size(); i++) {
                    for (DataSnapshot clienteSnapshot : dataSnapshot.getChildren()) {
                        String idUsu = clienteSnapshot.child("idCliente").getValue().toString();
                        if (Objects.equals(idUsu, data.get(i).getIdCliente())) {
                            data.get(i).setNombreC(clienteSnapshot.child("nombreC").getValue().toString());
                            data.get(i).setApellidoC(clienteSnapshot.child("apellidoC").getValue().toString());
                            data.get(i).setComentarioC(clienteSnapshot.child("comentarioC").getValue().toString());
                            data.get(i).setDireccionC(clienteSnapshot.child("direccionC").getValue().toString());
                            data.get(i).setTelefonoC((long) clienteSnapshot.child("telefonoC").getValue());
                        }
                    }
                }
                Collections.sort(data, new Comparator<RutaData>() {
                    @Override
                    public int compare(RutaData o1, RutaData o2) {
                        return new Integer((int) o1.getPos()).compareTo(new Integer((int) o2.getPos()));
                    }
                });
                rutaAdaptadorRuta.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rutaAdaptadorRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("click", "YES");
                Intent intent = new Intent(getContext(), DetalleFacturaActivity.class);
                Gson gson = new Gson();
                String gsonData = gson.toJson(data.get(recyclerView.getChildPosition(v)));
                // intent.putExtra("idFactura",data.get(recyclerView.getChildPosition(v)).getIdFacturaVenta());
                intent.putExtra("data", gsonData);
                startActivity(intent);
                //hola

            }
        });

        rutaAdaptadorRuta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Toast.makeText(getContext(), "LongClick sobre" + recyclerView.getChildPosition(v), Toast.LENGTH_SHORT).show();
                OpcionesRutaDialogFragment dialog = new OpcionesRutaDialogFragment(data.get(recyclerView.getChildPosition(v)).getIdFacturaVenta(),data.get(recyclerView.getChildPosition(v)).getIdSucursal());
                dialog.show(getFragmentManager(), "opcionesruta");
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume","rutaFragament");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop","rutaFragament");
    }
}



