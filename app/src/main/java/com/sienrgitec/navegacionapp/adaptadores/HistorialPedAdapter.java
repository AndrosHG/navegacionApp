package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;

import com.sienrgitec.navegacionapp.actividades.MuestraDet;

import com.sienrgitec.navegacionapp.modelos.opHistorialPed;



public class HistorialPedAdapter extends RVAdapter<opHistorialPed> {

    private Context contextc;


    public HistorialPedAdapter(Context context, RVAdapter.OnViewHolderClick<opHistorialPed> listener) {
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
    protected void bindView(final opHistorialPed item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {


            TextView dtFecha = (TextView)viewHolder.getView(R.id.tvFechaF);
            dtFecha.setText("Fecha: " + item.getDtFecha());

            TextView vipedido = (TextView)viewHolder.getView(R.id.tviPedidof);
            vipedido.setText("Pedido: " + item.getiPedido().toString());

            TextView detotArt = (TextView)viewHolder.getView(R.id.tvdeTotArtF);
            detotArt.setText("Tot. Art.: " + item.getDeTotalPiezas().toString());

            TextView cNegocio = (TextView)viewHolder.getView(R.id.tvNegocioF);
            cNegocio.setText("Proveedor: " + item.getcNegocio());

            TextView cDomicilio = (TextView)viewHolder.getView(R.id.tvDomF);
            cDomicilio.setText("Domicilio: " + item.getcDirProveedor());


            TextView cCliente = (TextView)viewHolder.getView(R.id.tvCliF);
            cCliente.setText("Cliente: " + item.getcCliente());

            TextView cDomCli = (TextView)viewHolder.getView(R.id.tvDomCliF);
            cDomCli.setText("Domicilio: " + item.getcDirCli());


        }
    }
}
