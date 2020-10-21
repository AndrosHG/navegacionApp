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
import com.sienrgitec.navegacionapp.actividades.MapsActivity;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.modelos.ctProveedor_;
import com.sienrgitec.navegacionapp.ui.home.HomeFragment;

import java.util.ArrayList;


public class ProveedorAdapter extends RVAdapter<ctProveedor_> {

    private Context contextc;


    public ProveedorAdapter(Context context, RVAdapter.OnViewHolderClick<ctProveedor_> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ordenenc_listv, viewGroup, false);
        contextc = context;
        return view;
    }

    @Override
    protected void bindView(final ctProveedor_ item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            ImageView btnEliminar = (ImageView)viewHolder.getView(R.id.imageView8);
            ImageView btnMapas    = (ImageView)viewHolder.getView(R.id.imageView9);

            btnEliminar.setOnClickListener(v ->{
                Intent intent = new Intent(getContext(), MuestraDet.class);
                intent.putExtra("proveedor", item.getiProveedor());
                contextc.startActivity(intent);
            });


            btnMapas.setOnClickListener(v ->{
                Intent intent = new Intent(getContext(), MapsActivity.class);
                intent.putExtra("deLat", item.getDeLat());
                intent.putExtra("deLon", item.getDeLon());
                intent.putExtra("vcNegocio", item.getcRazonS());
                contextc.startActivity(intent);
            });


            TextView nombre = (TextView)viewHolder.getView(R.id.tvRSocial);
            nombre.setText(item.getcRazonS());

            TextView correo = (TextView)viewHolder.getView(R.id.tvEnregaA);
            correo.setText(item.getcNegocio());

            TextView telefono = (TextView)viewHolder.getView(R.id.tvDom);
            telefono.setText(item.getcNegocio());


        }
    }
}



