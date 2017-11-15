package com.wilderarias.smarta2.ruta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wilderarias.smarta2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WilderArias on 11/15/2017.
 */

public class AdaptadorAbonos extends ArrayAdapter {

    private ArrayList<AbonosData>  abonosData;
    public AdaptadorAbonos(@NonNull Context context, @NonNull ArrayList<AbonosData> datosAbo) {
        super(context, R.layout.abonos_list, datosAbo);

        abonosData=datosAbo;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View item=inflater.inflate(R.layout.abonos_list,null);

        TextView tFecha=item.findViewById(R.id.tFecha);
        TextView tValorAbono=item.findViewById(R.id.tValorAbono);

        tFecha.setText(abonosData.get(position).getDiaRegistroAC()+"/"+abonosData.get(position).getMesRegistroAC()+"/"+abonosData.get(position).getAnoRegistroAC());
        tValorAbono.setText(String.valueOf(abonosData.get(position).getValorAbono()));


        return item;
    }
}
