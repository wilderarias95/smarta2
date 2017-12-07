package com.wilderarias.smarta2.ruta;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wilderarias.smarta2.R;

import java.util.List;

/**
 * Created by WilderArias on 10/29/2017.
 */

public class AdaptadorRuta extends RecyclerView.Adapter<AdaptadorRuta.RutaViewHolder> implements View.OnClickListener, View.OnLongClickListener {


    private List<RutaData> data;
    private View.OnClickListener listener;
    private View.OnLongClickListener longClickListener;

    public AdaptadorRuta(List<RutaData> data) {
        this.data = data;
    }

    @Override
    public RutaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ruta_list, parent, false);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        RutaViewHolder rutaViewHolder = new RutaViewHolder(itemView);
        return rutaViewHolder;
    }

    @Override
    public void onBindViewHolder(RutaViewHolder holder, int position) {
        RutaData item = data.get(position);

        holder.tPosRuta.setText(String.valueOf(item.getPos()));
        holder.tNombre.setText(item.getNombreC()+" "+item.getApellidoC());
        holder.tIdent.setText(item.getIdCliente());
        holder.tFecha.setText(String.valueOf(item.getDiaRegistroV())+"/"+String.valueOf(item.getMesRegistroV())+"/"+String.valueOf(item.getAnoRegistroV()));
        holder.tTotal.setText(String.valueOf(item.getValorVenta()));
        holder.tSaldo.setText(String.valueOf(item.getSaldoCredito()));
        holder.tComentario.setText(item.getComentarioC());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null) {
            longClickListener.onLongClick(v);
        }
        return true;
    }


    public static class RutaViewHolder extends RecyclerView.ViewHolder {


        private TextView tPosRuta, tNombre, tIdent, tFecha, tTotal, tSaldo,tComentario;



        public RutaViewHolder(View itemView) {
            super(itemView);
            tPosRuta = (TextView) itemView.findViewById(R.id.tPosRuta);
            tNombre = itemView.findViewById(R.id.tNombre);
            tIdent = itemView.findViewById(R.id.tIdent);
            tFecha = itemView.findViewById(R.id.tFechafactura);
            tTotal = itemView.findViewById(R.id.tTotalFactura);
            tSaldo = itemView.findViewById(R.id.tSaldoFactura);
            tComentario=itemView.findViewById(R.id.tComentario);
        }
    }
}
