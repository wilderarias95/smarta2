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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by WilderArias on 11/16/2017.
 */

public class AdaptadorProductos extends ArrayAdapter {

    ArrayList<ProductosData> productosData;

    public AdaptadorProductos(Context context, ArrayList<ProductosData> productosData){
        super(context, R.layout.productos_list,productosData);
        this.productosData=productosData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.productos_list, null);

        TextView tIdProd=item.findViewById(R.id.tIdProd);
        TextView tNombreProd=item.findViewById(R.id.tNombreProd);
        TextView tCantidadProd=item.findViewById(R.id.tCantidadProd);
        TextView tValorProd=item.findViewById(R.id.tValorProd);

        tIdProd.setText(productosData.get(position).getCodigoP()+"-"+String.valueOf(productosData.get(position).getCodigoAP()));
        tNombreProd.setText(productosData.get(position).getNombreP());
        tCantidadProd.setText(String.valueOf(productosData.get(position).getCantidadP()));
        tValorProd.setText(String.valueOf(productosData.get(position).getValorNeto()));

        return item;
    }
}
