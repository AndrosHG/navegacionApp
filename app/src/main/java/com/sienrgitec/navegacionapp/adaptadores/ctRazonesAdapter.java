package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.sienrgitec.navegacionapp.R;

import com.sienrgitec.navegacionapp.actividades.MotivosCancela;
import com.sienrgitec.navegacionapp.modelos.ctRazones;


public class ctRazonesAdapter extends RVAdapter<ctRazones> {

    private Context contextc;


    public ctRazonesAdapter(Context context, RVAdapter.OnViewHolderClick<ctRazones> listener) {
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
    protected void bindView(final ctRazones item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            Button btnSelecc = (Button)viewHolder.getView(R.id.btnSelec);

            btnSelecc.setOnClickListener(v ->{
                MotivosCancela Selecciona = new MotivosCancela();
                Selecciona.GenerarRechazo( item.getiRazon(), item.getcRazon(), contextc);

            });

            TextView cRazon = (TextView)viewHolder.getView(R.id.tvRazones);
            cRazon.setText(item.getcRazon());

        }
    }
}
