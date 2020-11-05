package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;

import com.sienrgitec.navegacionapp.modelos.opPedPainani_;


public class HistorialPedAdapter extends RVAdapter<opPedPainani_> {

    private Context contextc;


    public HistorialPedAdapter(Context context, RVAdapter.OnViewHolderClick<opPedPainani_> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.terminadosenc_listv, viewGroup, false);
        contextc = context;
        return view;
    }

    @Override
    protected void bindView(final opPedPainani_ item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
           // ImageView btnBucarDet = (ImageView)viewHolder.getView(R.id.imageView8);


            TextView dtFecha = (TextView)viewHolder.getView(R.id.tvFechaF);
            dtFecha.setText("Fecha: " + item.getDtFecha());

            TextView ipedido = (TextView)viewHolder.getView(R.id.tviPedidof);
            ipedido.setText("Pedido: " + item.getiPedido().toString());

            TextView cCliente = (TextView)viewHolder.getView(R.id.tvCliF);
            cCliente.setText("Cliente: " + item.getcCliente());

            TextView cDomicilio = (TextView)viewHolder.getView(R.id.tvDomCliF);
            cDomicilio.setText("Domicilio: " + item.getcDirCliente());



        }
    }
}
