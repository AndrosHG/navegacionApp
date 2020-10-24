package com.sienrgitec.navegacionapp.adaptadores;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.MapsActivity;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet_;
import com.sienrgitec.navegacionapp.ui.terminados.TerminadosFragment;

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

            Button btnLlega  = (Button)viewHolder.getView(R.id.button);
            Button btnSalida = (Button)viewHolder.getView(R.id.button2);
            btnSalida.setVisibility(View.INVISIBLE);

            btnBucarDet.setOnClickListener(v ->{

                Log.e("Buscare detallle", "con " + item.getiPedidoProv());

                Intent intent = new Intent(getContext(), MuestraDet.class);
                intent.putExtra("ipiPedido", item.getiPedido());
                intent.putExtra("ipiPartida", item.getiPedidoProv());
                contextc.startActivity(intent);
            });

            btnMapas.setOnClickListener(v ->{
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("ipcDom", item.getcDirProveedor());
                intent.putExtra("ipcProv", item.getcNegocion());
                contextc.startActivity(intent);
            });

            btnLlega.setOnClickListener(v ->{
                btnLlega.setVisibility(View.INVISIBLE);
                btnSalida.setVisibility(View.VISIBLE);
                TerminadosFragment RegEntrada = new TerminadosFragment();
                RegEntrada.RegistraHrs("Llega", item.getiPedido(), item.getiPartida(), contextc);

            });

            btnSalida.setOnClickListener(v ->{
                TerminadosFragment RegEntrada = new TerminadosFragment();
                RegEntrada.RegistraHrs("Sale", item.getiPedido(), item.getiPartida(), contextc);
            });


            TextView ipedido = (TextView)viewHolder.getView(R.id.tvPedido);
            ipedido.setText("Pedido: " + item.getiPedido().toString());

            TextView detotArt = (TextView)viewHolder.getView(R.id.tvTotArt);
            detotArt.setText("Tot. Art.: " + item.getDeTotalPiezas().toString());

            TextView cNegocio = (TextView)viewHolder.getView(R.id.tvNegocio);
            cNegocio.setText(item.getcNegocion());

            TextView cDomicilio = (TextView)viewHolder.getView(R.id.tvDir);
            cDomicilio.setText(item.getcDirProveedor());


        }
    }
}
