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
import android.widget.TextView;

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
import com.sienrgitec.navegacionapp.R;
import com.sienrgitec.navegacionapp.actividades.MuestraDet;
import com.sienrgitec.navegacionapp.adaptadores.ProveedorAdapter;
import com.sienrgitec.navegacionapp.configuracion.Globales;
import com.sienrgitec.navegacionapp.modelos.ctProveedor;
import com.sienrgitec.navegacionapp.modelos.ctProveedor_;

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


    public Globales globales;
    public String   url = globales.URL;


    public static ArrayList<ctProveedor>  g_ctDetalleFinal = new ArrayList<ctProveedor>();
    public static ArrayList<ctProveedor_> listafinal       = new ArrayList<>();


    public RecyclerView recycler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);



        recycler      = (RecyclerView) root.findViewById(R.id.lista);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }



    public void BuscaTareas(){
        getmRequestQueue();
        listafinal.clear();

        String urlParams = String.format(url + "buscaProv");

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
                                JSONObject ds_ctProveedor = respuesta.getJSONObject("tt_ctProveedor");
                                JSONArray  tt_ctProveedor = ds_ctProveedor.getJSONArray("tt_ctProveedor");

                                globales.g_ctProvList      = Arrays.asList(new Gson().fromJson(tt_ctProveedor.toString(), ctProveedor[].class));


                                for(ctProveedor obj: globales.g_ctProvList){
                                    ctProveedor_ pasaLista = new ctProveedor_();
                                    Log.e("mi ubicacion--> " , obj.getDeLat().toString());

                                    pasaLista.setcNegocio(obj.getcNegocio());
                                    pasaLista.setcRazonS(obj.getcRazonS());
                                    pasaLista.setiProveedor(obj.getiProveedor());
                                    pasaLista.setDeLat(obj.getDeLat());
                                    pasaLista.setDeLon(obj.getDeLon());
                                    listafinal.add(pasaLista);
                                }

                                ProveedorAdapter adapDet = new ProveedorAdapter(getContext(),null);
                                adapDet.setList((List<ctProveedor_>) listafinal);
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
                            return;
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
               /* params.put("ipiProveedor","0");*/
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

    public void CargaDet(Integer viProeedor){
        Log.e("Probando -->", "imageview--> " + viProeedor);
        Intent intent = new Intent(getContext(), MuestraDet.class);
        intent.putExtra("proveedor", viProeedor);
        startActivity(intent);


    }


}