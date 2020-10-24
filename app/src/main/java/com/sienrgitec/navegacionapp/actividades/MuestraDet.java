package com.sienrgitec.navegacionapp.actividades;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.adaptadores.opPedidoDetAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctProveedor_;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet_;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MuestraDet  extends Activity {

    private static RequestQueue mRequestQueue;
    public  Globales globales;
    public  String   URL = globales.URL;
    private opPedidoDetAdapter adapterDet;

    private  Integer viPedido = 0, viProvs = 0;

    public static ArrayList<opPedidoDet>  g_ctDetallePed    = new ArrayList<opPedidoDet>();
    public RecyclerView recyclerDet;
    ArrayList<opPedidoDet> listaDet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.muestra_detalle);

        viPedido = getIntent().getExtras().getInt("ipiPedido");
        viProvs  = getIntent().getExtras().getInt("ipiPartida");


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        recyclerDet      = (RecyclerView) findViewById(R.id.listadet);
        recyclerDet.setLayoutManager(new LinearLayoutManager(MuestraDet.this,LinearLayoutManager.VERTICAL,false));


        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int)(width *.9),(int)(height*.6));
        CargaDet(viPedido, viProvs);
    }

    public void CargaDet(Integer viFolPed, Integer viProvID){

        listaDet = new ArrayList<>();

        for(opPedidoDet objPedDet: globales.g_opPedidoDetList){


                if(objPedDet.getiPedidoProv().equals(viProvID)
                    && objPedDet.getiPedido().equals(viPedido)) {

                    listaDet.add(objPedDet);
                }




        }

        adapterDet = new opPedidoDetAdapter(listaDet);
        adapterDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CargaDet(listaProvs.get(recycler.getChildAdapterPosition(view)).getcRazonS());
            }
        });
        recyclerDet.setAdapter(adapterDet);

    }




}
