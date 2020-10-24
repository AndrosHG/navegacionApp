package com.sienrgitec.navegacionapp.ui.terminados;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.sienrgitec.navegacionapp.actividades.MapsActivity;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.adaptadores.ProveedorAdapter;
import com.sienrgitec.navegacionapp.adaptadores.opPedPainDetAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctProveedor;
import com.sienrgitec.navegacionapp.modelos.ctProveedor_;
import com.sienrgitec.navegacionapp.modelos.opPedPainani;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet;
import com.sienrgitec.navegacionapp.modelos.opPedPainaniDet_;
import com.sienrgitec.navegacionapp.modelos.opPedidoDet;
import com.sienrgitec.navegacionapp.ui.cobranza.CobranzaViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerminadosFragment extends Fragment {

    private TerminadosViewModel terminadosViewModel;

    private  static RequestQueue mRequestQueue;

    public Globales globales;
    public String   url = globales.URL;

    public RecyclerView recycler;
    public static ArrayList<opPedPainaniDet_> listafinal       = new ArrayList<>();
    public static List<opPedPainani> opPedPainaniList = null;

    private TextView tvEntregaA, tvDomentrega;
    private ImageView ibtnMaps;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        terminadosViewModel =
                ViewModelProviders.of(this).get(TerminadosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_terminados, container, false);
        final TextView textView = root.findViewById(R.id.text_fin);
        ibtnMaps = root.findViewById(R.id.ibtnMapsCli);

        ibtnMaps.setVisibility(View.INVISIBLE);


        tvEntregaA = root.findViewById(R.id.tvEntregaA);
        tvDomentrega = root.findViewById(R.id.tvEntregaEn);


        recycler      = (RecyclerView) root.findViewById(R.id.lista);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        terminadosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        BuscarPedidos();



        ibtnMaps.setOnClickListener(v ->{
            Intent intent = new Intent(getContext(), MapsActivity.class);
            intent.putExtra("ipcDom", opPedPainaniList.get(0).getcDirCliente());
            intent.putExtra("ipcProv", opPedPainaniList.get(0).getcCliente());
            startActivity(intent);


        });



        return root;
    }

    public void getmRequestQueue(){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void MuestraMensaje(String vcTitulo, String vcMensaje, Context ipcContext){

            AlertDialog.Builder myBuild = new AlertDialog.Builder(ipcContext);
            myBuild.setMessage(vcMensaje);
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;

    }

    public void BuscarPedidos(){
        listafinal.clear();
        getmRequestQueue();
        String urlParams = String.format(url + "opPedPainani?ipiPainani=%1$s",  1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");


                            if (Error == true) {

                                return;

                            } else {
                                ibtnMaps.setVisibility(View.VISIBLE);

                                JSONObject ds_opPedido = respuesta.getJSONObject("tt_opPedido");
                                JSONObject ds_opPedidoDet = respuesta.getJSONObject("tt_opPedidoDet");
                                JSONObject ds_opPedidoProv = respuesta.getJSONObject("tt_opPedidoProveedor");
                                JSONObject ds_opPedPainani = respuesta.getJSONObject("tt_opPedPainani");
                                JSONObject ds_opPedPainaniDet = respuesta.getJSONObject("tt_opPedPainaniDet");

                                JSONArray tt_opPedidoDet  = ds_opPedidoDet.getJSONArray("tt_opPedidoDet");
                                JSONArray tt_opPedPainani = ds_opPedPainani.getJSONArray("tt_opPedPainani");
                                JSONArray tt_opPedPainaniDet = ds_opPedPainaniDet.getJSONArray("tt_opPedPainaniDet");

                                globales.g_opPedidoDetList     = Arrays.asList(new Gson().fromJson(tt_opPedidoDet.toString(), opPedidoDet[].class));
                                opPedPainaniList     = Arrays.asList(new Gson().fromJson(tt_opPedPainani.toString(), opPedPainani[].class));
                                globales.g_ctPedPainaniDetList = Arrays.asList(new Gson().fromJson(tt_opPedPainaniDet.toString(), opPedPainaniDet[].class));


                                for(opPedPainaniDet obj: globales.g_ctPedPainaniDetList){
                                    opPedPainaniDet_ pasaLista = new opPedPainaniDet_();
                                    pasaLista.setiPainani(obj.getiPainani());
                                    pasaLista.setiPedido(obj.getiPedido());
                                    pasaLista.setiProveedor(obj.getiProveedor());
                                    pasaLista.setcNegocion(obj.getcNegocion());
                                    pasaLista.setcDirProveedor(obj.getcDirProveedor());
                                    pasaLista.setiPartida(obj.getiPartida());
                                    pasaLista.setiPedidoProv(obj.getiPedidoProv());
                                    pasaLista.setDeTotalPiezas(obj.getDeTotalPiezas());
                                    listafinal.add(pasaLista);
                                }

                                tvEntregaA.setText("Entregar a: " + opPedPainaniList.get(0).getcCliente());
                                tvDomentrega.setText("En: " + opPedPainaniList.get(0).getcDirCliente());

                                opPedPainDetAdapter adapDet = new opPedPainDetAdapter(getContext(),null);
                                adapDet.setList((List<opPedPainaniDet_>) listafinal);
                                recycler.setAdapter(adapDet);
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
                            myBuild.setMessage("Error en la conversión de Datos. Vuelva a Intentar. " + e);
                            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR CONVERSION </font>"));
                            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog dialog = myBuild.create();
                            dialog.show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(getContext());
                        myBuild.setMessage("No se pudo conectar con el servidor. Vuelva a Intentar. " + error.toString());
                        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR RESPUESTA </font>"));
                        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = myBuild.create();
                        dialog.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPainani","1");
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }

    public void RegistraHrs(String ipcAccion, Integer ipiPedido , Integer ipiPedProv, Context ipcContextp){

        getmRequestQueue();
        String urlParams = String.format(url + "opPedPainaniDet?ipiPedido=%1$s&ipiPainani=%2$s&ipiPartida=%3$s&ipcAccion=%4$s", ipiPedido, globales.g_ctUsuario.getiPersona(),ipiPedProv, ipcAccion );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");

                            if (Error == true) {
                                MuestraMensaje("ERROR", Mensaje, ipcContextp);
                                return;
                            } else {
                                if(ipcAccion == "Llega"){
                                    MuestraMensaje("Aviso", "Asegurate de que los productos correspondan con el pedido", ipcContextp);
                                }else{
                                    MuestraMensaje("Aviso", "Hecho",ipcContextp);
                                }
                            }
                        } catch (JSONException e) {
                            MuestraMensaje("ERROR CONVERSION", "Error en la conversión de Datos. Vuelva a Intentar. " + e,ipcContextp);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        MuestraMensaje("ERROR RESPUESTA", "No se pudo conectar con el servidor. Vuelva a Intentar. " + error.toString(),ipcContextp);

                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPedido", "ipiPedido");
                params.put("ipiPainani", globales.g_ctUsuario.getiPersona().toString());
                params.put("ipiPartida", "ipiPedProv");
                params.put("ipcAccion", ipcAccion);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Access the RequestQueue through your singleton class.
       /* jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        mRequestQueue.add(jsonObjectRequest);

    }


}