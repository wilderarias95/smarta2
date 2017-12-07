package com.wilderarias.smarta2.ruta;

import android.annotation.SuppressLint;
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
 * Created by USUARIO on 02/12/2017.
 */

public class AdaptadorProductosSpinner extends ArrayAdapter {

    private ArrayList<ProductoData> productoData;

    public AdaptadorProductosSpinner(@NonNull Context context, @NonNull ArrayList<ProductoData> productoData) {
        super(context, R.layout.productos_spinner, productoData);
        this.productoData=productoData;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View item=inflater.inflate(R.layout.productos_spinner,null);

         TextView tNombreProd=item.findViewById(R.id.tNombreProd);
         tNombreProd.setText(productoData.get(position).getNombreP());

        TextView tCodigo=item.findViewById(R.id.tCodigo);
        tCodigo.setText(productoData.get(position).getCodigoP()+"-"+productoData.get(position).getCodigoAP());
        return item;
    }

    //Metodo para poblar los items del spinner, no seleccionados
    @SuppressLint("ResourceAsColor")
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View item=inflater.inflate(R.layout.productos_spinner,null);

        TextView tNombreProd=item.findViewById(R.id.tNombreProd);
        tNombreProd.setText(productoData.get(position).getNombreP());

        TextView tCodigo=item.findViewById(R.id.tCodigo);
        tCodigo.setText(productoData.get(position).getCodigoP()+"-"+productoData.get(position).getCodigoAP());
        return item;
    }
}
