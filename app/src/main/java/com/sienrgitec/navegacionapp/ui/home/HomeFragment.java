package com.sienrgitec.navegacionapp.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.MotivosCancela;
import com.sienrgitec.navegacionapp.adaptadores.opPerfilCliAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.opPedPainani;
import com.sienrgitec.navegacionapp.modelos.opPerfilCli_;
import com.sienrgitec.navegacionapp.ui.pedidos.PedidoshowFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private  static RequestQueue mRequestQueue;
    private Button btnContinuar, btnRechazar;


    public Globales globales;
    public String   url = globales.URL;



    List<opPerfilCli_> opPerfilCliList = null;

    public RecyclerView recycler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        btnContinuar = root.findViewById(R.id.btnContinua);
        btnRechazar  = root.findViewById(R.id.btnRechaza);

        
        recycler      = (RecyclerView) root.findViewById(R.id.lista);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnContinuar.setOnClickListener(v ->{
            Intent Home = new Intent(getContext(), PedidoshowFragment.class);
            startActivity(Home);
        });


        btnRechazar.setOnClickListener(v ->{
            CancelarPedido();
        });


        if(globales.g_opPedPainani.size() == 0){
            Log.e("iniciando home---> ", "no tienes pedido");
        }else{
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle(Html.fromHtml("<font color ='#FF0000'> Tienes un nuevo pedido </font>"));
            builder.setMessage("¿Aceptar Pedido?");
            builder.setPositiveButton("Si",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActualizaPedido(true);

                        }
                    });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActualizaPedido(false);

                }
            });

            final AlertDialog alert = builder.create();
            alert.show();
        }

        //BuscaTareas();
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

    public void ActualizaPedido(Boolean vlAcetpado){
        Log.e("mi Respuesta es-->" , " vlAceptado= " + vlAcetpado);


        globales.opPedPainaniList.clear();
        opPedPainani objActualizaPed  = new opPedPainani();
        objActualizaPed.setcCvePedido  ("globales.g_opPedPainani.get(0).getcCvePedido()");
        /*objActualizaPed.setcDirCliente (globales.g_opPedPainani.get(0).getcDirCliente());
        objActualizaPed.setDtAvisado   (globales.g_opPedPainani.get(0).getDtAvisado());
        objActualizaPed.setDtContestado(globales.g_opPedPainani.get(0).getDtContestado());
        objActualizaPed.setDtFecha     (globales.g_opPedPainani.get(0).getDtFecha());
        objActualizaPed.setDtReasignado(globales.g_opPedPainani.get(0).getDtReasignado());
        objActualizaPed.setiAsignadoA  (globales.g_opPedPainani.get(0).getiAsignadoA());
        objActualizaPed.setiCliente    (globales.g_opPedPainani.get(0).getiCliente());
        objActualizaPed.setiPainani    (globales.g_opPedPainani.get(0).getiPainani());  //
        objActualizaPed.setiPedido     (globales.g_opPedPainani.get(0).getiPedido()); //
        objActualizaPed.setlAceptado   (vlAcetpado);
        objActualizaPed.setlContestado (true);
        objActualizaPed.setlReasignado (false);*/

        globales.opPedPainaniList.add(objActualizaPed);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opPedPainani = gson.toJson(
                globales.opPedPainaniList,
                new TypeToken<ArrayList<opPedPainani>>() {
                }.getType());
        try {
            JSONArray opPedPainaniJS   = new JSONArray(JS_opPedPainani);
            jsonDataSet.put("tt_opPedPainani",  opPedPainaniJS);
            jsonParams.put("ds_opPedido", jsonDataSet);
            jsonBody.put("request", jsonParams);
            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error conectar con el servidor: " +  e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url + "opPedPainani/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");

                            JSONObject ds_opPedido          = respuesta.getJSONObject("tt_opPedido");
                            JSONObject ds_opPedidoProveedor = respuesta.getJSONObject("tt_opPedidoProveedor");
                            JSONObject ds_opPedidoDet       = respuesta.getJSONObject("tt_opPedidoDet");


                            if (Error == true) {
                                Toast.makeText(getContext(), "Error conectar con el servidor: " +  Mensaje, Toast.LENGTH_SHORT).show();
                            } else {
                                if (vlAcetpado == false){

                                }else{
                                    BuscarCli();
                                    btnRechazar.setVisibility(View.VISIBLE);
                                    btnContinuar.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error conectar con el servidor: " +  e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        Toast.makeText(getContext(), "Error conectar con el servidor: " +  error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 20000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 20000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }

    public void BuscarCli(){
        getmRequestQueue();
        String urlParams = String.format(url + "perfilCli?ipiPedido=%1$s&ipiCliente=%2$s", 403, 130 ); //globales.g_opPedPainani.get(0).getiPedido(), globales.g_opPedPainani.get(0).getiCliente()
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

                                //MuestraMensaje("Aviso", Mensaje);
                                return;

                            } else {
                                JSONObject ds_perfil = respuesta.getJSONObject("ttPerfilCli");
                                JSONArray ttPerfilCli = ds_perfil.getJSONArray("ttPerfilCli");
                                opPerfilCliList = Arrays.asList(new Gson().fromJson(ttPerfilCli.toString(), opPerfilCli_[].class));

                                opPerfilCliAdapter adapDet = new opPerfilCliAdapter(getContext(),null);
                                adapDet.setList((List<opPerfilCli_>) opPerfilCliList);
                                recycler.setAdapter(adapDet);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error conversión de Datos: " +  e, Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        Toast.makeText(getContext(), "Error conectar con el servidor: " +  error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipiPedido",   "403" );// globales.g_opPedPainani.get(0).getiPedido().toString());
                params.put("ipiCliente","130" ); //globales.g_opPedPainani.get(0).getiCliente().toString());
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

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 20000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 20000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    public void CancelarPedido(){
        ActualizaPedido(false);
        Intent Home = new Intent(getContext(), MotivosCancela.class);
        startActivity(Home);

    }

}

/*
*
*
*
*
*
*
* */