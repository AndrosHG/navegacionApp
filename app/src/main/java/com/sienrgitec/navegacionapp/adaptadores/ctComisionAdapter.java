package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.Aportacion;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctComisiones;


public class ctComisionAdapter extends RVAdapter<ctComisiones> {

    private Context contextc;
    private Globales globales;


    public ctComisionAdapter(Context context, RVAdapter.OnViewHolderClick<ctComisiones> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.catalogoslist, viewGroup, false);
        contextc = context;
        return view;
    }


    @Override
    protected void bindView(final ctComisiones item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            Button btnSelecc = (Button)viewHolder.getView(R.id.btnSelec);

            btnSelecc.setOnClickListener(v ->{
                globales.vgiComision = item.getiComision();
                Toast.makeText(getContext(), "Seleccionaste el siguiente pordentaje: " + item.getDeValor() , Toast.LENGTH_SHORT).show();
            });

            TextView deValor = (TextView)viewHolder.getView(R.id.tvRazones);
            deValor.setText(item.getDeValor() + "%");

        }
    }
}
