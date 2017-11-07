package com.wilderarias.smarta2.inventario;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wilderarias.smarta2.R;

import java.util.List;

/**
 * Created by WilderArias on 10/29/2017.
 */

public class AdaptadorInventario extends RecyclerView.Adapter<AdaptadorInventario.InventarioViewHolder> implements View.OnClickListener,View.OnLongClickListener {

    private List<InventarioData> dataInv;
    private View.OnClickListener listener;
    private View.OnLongClickListener longClickListener;


    public AdaptadorInventario(List<InventarioData> data){
        dataInv=data;
    }

    @Override
    public AdaptadorInventario.InventarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inventario_list, parent, false);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        InventarioViewHolder inventarioViewHolder=new InventarioViewHolder(itemView);
        return inventarioViewHolder;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);}
    }

    @Override
    public void onBindViewHolder(AdaptadorInventario.InventarioViewHolder holder, int position) {
        InventarioData item=dataInv.get(position);

        holder.tPosProd.setText(String.valueOf(item.getPos()));
        holder.tNomProd.setText(item.getNombreP());
        holder.tCodProd.setText(item.getCodigoP()+"-"+item.getCodigoAP());
        holder.tDesc.setText(item.getDescripcionP());
        holder.tCantDisp.setText(String.valueOf(item.getCantidadDisponible()));
        holder.tPrecProd.setText(String.valueOf(item.getValorNeto()));

    }

    @Override
    public int getItemCount() {
        return dataInv.size();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public static class InventarioViewHolder extends RecyclerView.ViewHolder{
        private TextView tPosProd, tNomProd, tCodProd, tDesc, tCantDisp, tPrecProd;

        public InventarioViewHolder(View itemView) {
            super(itemView);
            tPosProd=itemView.findViewById(R.id.tPosProd);
            tNomProd=itemView.findViewById(R.id.tNombreProd);
            tCodProd=itemView.findViewById(R.id.tCodigo);
            tDesc=itemView.findViewById(R.id.tDescripcion);
            tCantDisp=itemView.findViewById(R.id.tCantDisp);
            tPrecProd=itemView.findViewById(R.id.tPrecioProd);
        }
    }
}
