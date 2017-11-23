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

/**
 * Created by WilderArias on 11/21/2017.
 */

public class IngresarAbonoDialogFragment extends DialogFragment {

    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();;
    private DatabaseReference myRef=firebaseDatabase.getReference().child("abono_credito");
    private long idFactura,idSucursal,ano,mes,dia,contabonos,abono;
    private AbonosData abonosData;

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
                    Date date=new Date();
                    ano=date.getYear();
                    mes=date.getMonth();
                    dia=date.getDay();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            contabonos=dataSnapshot.getChildrenCount();
                            Log.i("abonoDialog","rutaFragament");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    abonosData=new AbonosData(ano,dia,idFactura,idSucursal,mes,abono);
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
