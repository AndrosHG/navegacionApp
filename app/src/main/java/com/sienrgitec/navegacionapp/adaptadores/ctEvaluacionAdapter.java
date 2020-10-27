package com.sienrgitec.navegacionapp.adaptadores;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



import com.sienrgitec.navegacionapp.R;

import com.sienrgitec.navegacionapp.actividades.MapsActivity;
import com.sienrgitec.navegacionapp.actividades.opEvaluaciones;
import com.sienrgitec.navegacionapp.modelos.ctEvaluacion;


public class ctEvaluacionAdapter extends RVAdapter<ctEvaluacion> {

    private Context contextc;
    private Float vdeCalificacion ;


    public ctEvaluacionAdapter(Context context, RVAdapter.OnViewHolderClick<ctEvaluacion> listener) {
        super(context, listener);
    }


    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.razonevalualist, viewGroup, false);
        contextc = context;
        return view;
    }


    @Override
    protected void bindView(final ctEvaluacion item, RVAdapter.ListViewHolder viewHolder) {
        if (item != null) {
            ImageView btnMapas    = (ImageView) viewHolder.getView(R.id.imageView9);
            RatingBar ratingBar   = (RatingBar) viewHolder.getView(R.id.ratingBar2);;



            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    vdeCalificacion = rating;
                }
            });

            btnMapas.setOnClickListener(v ->{
                Log.e("Adapter Evaluea--> ", item.getcEvalua() + " valor " + vdeCalificacion);


                opEvaluaciones objNvaEv = new opEvaluaciones();
                objNvaEv.CreaParamEvalua(vdeCalificacion, item.getiPunto(), item.getiEvalua());
                ratingBar.setRating(Float.parseFloat("0.0"));

//                Intent intent = new Intent(getContext(), MapsActivity.class);
//                intent.putExtra("ipcDom", item.getcDirProveedor());
//                intent.putExtra("ipcProv", item.getcNegocion());
//                contextc.startActivity(intent);
            });


            TextView cRazon = (TextView)viewHolder.getView(R.id.tvEvaluaDesc);
            cRazon.setText(item.getcEvalua());

        }
    }
}
