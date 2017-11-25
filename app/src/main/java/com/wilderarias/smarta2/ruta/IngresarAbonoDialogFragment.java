package com.wilderarias.smarta2.ruta;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wilderarias.smarta2.R;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by WilderArias on 11/21/2017.
 */

public class IngresarAbonoDialogFragment extends DialogFragment {

    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();;
    private DatabaseReference myRef=firebaseDatabase.getReference().child("abono_credito");
    private DatabaseReference myRef2=firebaseDatabase.getReference("venta");
    private long idFactura,idSucursal,ano,mes,dia,posabono,abono;
    private AbonosData abonosData;
    private int contAbono=0;

    public IngresarAbonoDialogFragment() {
    }

@SuppressLint("ValidFragment")
    public IngresarAbonoDialogFragment(long idFactura, long idSucursal) {
        this.idFactura = idFactura;
        this.idSucursal = idSucursal;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_ingresar_abono, null);
        builder.setView(v);

        final EditText eAbono = v.findViewById(R.id.eAbono);
        Button bAceptar = v.findViewById(R.id.bAceptar);
        Button bCancelar = v.findViewById(R.id.bCancelar);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abono = eAbono.getText().toString().equals("")?0:Long.parseLong(eAbono.getText().toString());
                if(abono!=0){

                    Calendar nowCalendar=Calendar.getInstance();
                    ano=nowCalendar.get(Calendar.YEAR);
                    mes=(nowCalendar.get(Calendar.MONTH)+1);
                    dia=nowCalendar.get(Calendar.DAY_OF_MONTH);

                    abonosData=new AbonosData(ano,dia,idFactura,idSucursal,mes,abono);

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(contAbono<1) {
                                posabono=dataSnapshot.getChildrenCount();
                                Log.i("abonoDialog","cargando longitud"+posabono);
                                myRef=firebaseDatabase.getReference().child("abono_credito").child("ac"+posabono);


                                myRef2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot actSnapshot:dataSnapshot.getChildren()){
                                            if (Long.parseLong(actSnapshot.child("idFacturaVenta").getValue().toString())==idFactura){
                                                Map<String,Object> newData = new HashMap<>();
                                                long nuevoSaldo=Long.parseLong(actSnapshot.child("saldoCredito").getValue().toString())-abono;
                                                Log.i("abonoDialog","actualizando nuevo saldo"+nuevoSaldo);
                                                newData.put("saldoCredito",nuevoSaldo);
                                               if(contAbono<1){
                                                    myRef.setValue(abonosData);
                                                    myRef2.child(actSnapshot.getKey()).updateChildren(newData);
                                                    ++contAbono;
                                                   Log.i("abonoDialog","contAbono: "+contAbono);
                                                }
                                                break;
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {}
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    dismiss();
                }else{
                    Toast.makeText(getContext(), R.string.msgvalorvalido, Toast.LENGTH_SHORT).show();
                }

            }
        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.create();
    }
}
