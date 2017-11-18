package com.wilderarias.smarta2.ruta;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wilderarias.smarta2.R;

/**
 * Created by WilderArias on 11/17/2017.
 */

public class OpcionesRutaDialogFragment extends DialogFragment {

    public OpcionesRutaDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.dialogrutalayout,null);
        builder.setView(v);

        TextView tIngresarAbono=v.findViewById(R.id.tIngresarAbono);
        TextView tNuevaVenta=v.findViewById(R.id.tNuevaVenta);

        tIngresarAbono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        tNuevaVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),IngresarVentaActivity.class);
                startActivity(intent);
                dismiss();
           }
        });

        return builder.create();
    }
}

/*

  AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
               builder.setItems(R.array.opc_ruta_array, new DialogInterface.OnClickListener() {
                @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"selecciono"+which,Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();

 */