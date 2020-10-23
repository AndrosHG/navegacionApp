package com.sienrgitec.navegacionapp.adaptadores;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet_;

public class opPedPainDetAdapter extends RVAdapter<opPedPainaniDet_> {

    private Context contextc;


    public opPedPainDetAdapter(Context context, RVAdapter.OnViewHolderClick<opPedPainaniDet_> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.oppedpainanidlist, viewGroup, false);
        contextc = context;
        return view;
    }

    @Override
    protected void bindView(final opPedPainaniDet_ item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            ImageView btnBucarDet = (ImageView)viewHolder.getView(R.id.imageView8);
            ImageView btnMapas    = (ImageView)viewHolder.getView(R.id.imageView9);

            btnBucarDet.setOnClickListener(v ->{
                Intent intent = new Intent(getContext(), MuestraDet.class);
                intent.putExtra("ipiPedido", item.getiPedido());
                intent.putExtra("proveedor", item.getiProveedor());
                contextc.startActivity(intent);
            });


            /*btnMapas.setOnClickListener(v ->{
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("deLat", item.getDeLat());
                intent.putExtra("deLon", item.getDeLon());
                intent.putExtra("vcNegocio", item.getcRazonS());
                contextc.startActivity(intent);
            });*/


            TextView ipedido = (TextView)viewHolder.getView(R.id.tvPedido);
            ipedido.setText(item.getiPedido().toString());

            /*TextView detotArt = (TextView)viewHolder.getView(R.id.tvTotArt);
            detotArt.setText(item.getDeTotalPiezas().toString());*/

            TextView cNegocio = (TextView)viewHolder.getView(R.id.tvNegocio);
            cNegocio.setText(item.getcNegocion());

            TextView cDomicilio = (TextView)viewHolder.getView(R.id.tvDir);
            cDomicilio.setText(item.getcDirProveedor());


        }
    }
}
