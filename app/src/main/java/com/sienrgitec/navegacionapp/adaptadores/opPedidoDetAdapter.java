package com.sienrgitec.navegacionapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sienrgitec.navegacionapp.R;

import com.sienrgitec.navegacionapp.modelos.opPedidoDet;

import java.util.ArrayList;

public class opPedidoDetAdapter extends RecyclerView.Adapter<opPedidoDetAdapter.ViewHolder> implements View.OnClickListener{
    ArrayList<opPedidoDet> detallePed;
    private View.OnClickListener listener;


    public opPedidoDetAdapter(ArrayList<opPedidoDet> detallePed) {
        this.detallePed = detallePed;
    }

    @NonNull
    @Override
    public opPedidoDetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ordendet_listv,null,false);

        view.setOnClickListener(this);
        return new opPedidoDetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull opPedidoDetAdapter.ViewHolder holder, final int position) {
        holder.asignaDatos(detallePed.get(position));
    }

    @Override
    public int getItemCount() {
        return this.detallePed.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pedido, cant, nparte, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pedido = (TextView)  itemView.findViewById(R.id.tvPedido);
            cant   = (TextView)  itemView.findViewById(R.id.tvCant);
            nparte = (TextView)  itemView.findViewById(R.id.tvNParte);
            desc   = (TextView)  itemView.findViewById(R.id.tvDesc);
        }

        public void asignaDatos(opPedidoDet opPedidoDet) {
            if(opPedidoDet.getiFolioSusp() != null){
                pedido.setText(opPedidoDet.getiFolioSusp().toString());
            }else{
                pedido.setText("0");
            }

            cant.setText(opPedidoDet.getDeCantidad().toString());
            nparte.setText(opPedidoDet.getcNumParte());
            desc.setText(opPedidoDet.getcDescripcion());
        }
    }
}
