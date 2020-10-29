package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.MapsActivity;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.actividades.opEvaluaciones;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet_;
import com.sienrgitec.navegacionapp.modelos.opPerfilCli_;
import com.sienrgitec.navegacionapp.ui.pedidos.PedidoshowFragment;

public class opPerfilCliAdapter extends RVAdapter<opPerfilCli_> {

    private Context contextc;


    public opPerfilCliAdapter(Context context, RVAdapter.OnViewHolderClick<opPerfilCli_> listener) {
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
    protected void bindView(final opPerfilCli_ item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            ImageView btnBucarDet = (ImageView)viewHolder.getView(R.id.imageView8);
            ImageView btnMapas    = (ImageView)viewHolder.getView(R.id.imageView9);

            Button btnLlega  = (Button)viewHolder.getView(R.id.button);
            Button btnSalida = (Button)viewHolder.getView(R.id.button2);
            btnSalida.setVisibility(View.INVISIBLE);




            /*btnLlega.setOnClickListener(v ->{
                PedidoshowFragment RegEntrada = new PedidoshowFragment();
                RegEntrada.RegistraHrs("Llega", item.getiPedido(), item.getiPartida(), contextc);

            });*/


            TextView cCliente = (TextView)viewHolder.getView(R.id.textView6);
            cCliente.setText("Cliente.: " + item.getcCliente().toString());

            TextView cDirCli = (TextView)viewHolder.getView(R.id.textView8);
            cDirCli.setText("Dir.: " + item.getcDomicilio().toString());

            TextView iTotPed = (TextView)viewHolder.getView(R.id.textView9);
            iTotPed.setText("Tot. Pedidos:." + item.getiTotPed());

            RatingBar ratingBar = (RatingBar) viewHolder.getView(R.id.ratingBar3);;
            ratingBar.setRating(Float.parseFloat(String.valueOf(item.getDeEvalua())));


        }
    }
}
