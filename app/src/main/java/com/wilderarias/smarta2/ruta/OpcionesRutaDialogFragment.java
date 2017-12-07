package com.wilderarias.smarta2.ruta;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wilderarias.smarta2.R;

/**
 * Created by WilderArias on 11/17/2017.
 */


public class OpcionesRutaDialogFragment extends DialogFragment {

    private long idFactura,idSucursal;
    private long pos;
    @SuppressLint("ValidFragment")
    public OpcionesRutaDialogFragment(long idFactura,long idSucursal,long pos) {
        this.idFactura=idFactura;
        this.idSucursal=idSucursal;
        this.pos=pos;
    }

    public OpcionesRutaDialogFragment(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_ruta_layout, null);
        builder.setView(v);

        TextView tIngresarAbono = v.findViewById(R.id.tIngresarAbono);
        TextView tNuevaVenta = v.findViewById(R.id.tNuevaVenta);

        tIngresarAbono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IngresarAbonoDialogFragment ingresarAbonoDialogFragment=new IngresarAbonoDialogFragment(idFactura,idSucursal);
                ingresarAbonoDialogFragment.show(getFragmentManager(),"ingresarAbono");
                dismiss();
            }
        });

        tNuevaVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), IngresarVentaActivity.class);
                intent.putExtra("posRuta",pos);
                intent.putExtra("idSucursal",idSucursal);
                startActivity(intent);
                dismiss();
            }
        });

        return builder.create();
    }
}