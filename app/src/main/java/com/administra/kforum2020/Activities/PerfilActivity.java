package com.administra.kforum2020.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.kforum2020.Adaptadores.PerfilAdapter;
import com.administra.kforum2020.Model.MySingleton;
import com.administra.kforum2020.Model.Perfil;
import com.administra.kforum2020.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity {

    ArrayList<Perfil> alInfo ;
    PerfilAdapter adapter;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        lista = findViewById(R.id.lista_perfil);


        //Lleno la informacion de base de datos
        alInfo = new ArrayList();

        alInfo.add(new Perfil("","Name"));
        alInfo.add(new Perfil("","E-mail"));
        alInfo.add(new Perfil("","Job title"));
        alInfo.add(new Perfil("","Executive Assistant Name"));
        alInfo.add(new Perfil("","Executive Assistant E-mail"));
        alInfo.add(new Perfil("","Company"));
        alInfo.add(new Perfil("","Dietary restrictions"));
        alInfo.add(new Perfil("","Special requirements"));
        alInfo.add(new Perfil("","Arrival date"));
        alInfo.add(new Perfil("","Departure date"));
        alInfo.add(new Perfil("","Customer-Centricity | Session"));
        alInfo.add(new Perfil("","Significant other name"));
        alInfo.add(new Perfil("","Significant other dietary restrictions"));
        alInfo.add(new Perfil("","Significant other special requirements"));
        adapter = new PerfilAdapter(getApplicationContext(), alInfo);
        lista.setAdapter(adapter);
        loginHttp();
    }

    public void loginHttp(){
        HashMap<String, Object> map = new HashMap<>();// Mapeo previo
        String email="ismael.gonzalez@e-administra.com";
        String password="Kforum2020";
        map.put("email", email);
        map.put("password", password);
        JSONObject jsonObject = new JSONObject(map);
        String url = "https://www.kforum2020.com/backend/apps/user_info.php";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    int estado=0;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            alInfo.clear();

                            alInfo.add(new Perfil(response.getString("nombre"),"Name"));
                            alInfo.add(new Perfil(response.getString("email"),"E-mail"));
                            alInfo.add(new Perfil(response.getString("puesto"),"Job title"));
                            alInfo.add(new Perfil(response.getString("nombreAsistente"),"Executive Assistant Name"));
                            alInfo.add(new Perfil(response.getString("emailAsistente"),"Executive Assistant E-mail"));
                            alInfo.add(new Perfil(response.getString("empresa"),"Company"));
                            alInfo.add(new Perfil(response.getString("requerimientosDieta"),"Dietary restrictions"));
                            alInfo.add(new Perfil(response.getString("requerimientosEspecales"),"Special requirements"));
                            alInfo.add(new Perfil(response.getString("fechaLlegada"),"Arrival date"));
                            alInfo.add(new Perfil(response.getString("fechaSalida"),"Departure date"));
                            alInfo.add(new Perfil(response.getString("taller"),"Customer-Centricity | Session"));
                            alInfo.add(new Perfil(response.getString("nombreAcompanante"),"Significant other name"));
                            alInfo.add(new Perfil(response.getString("requerimientosDietaAcompanante"),"Significant other dietary restrictions"));
                            alInfo.add(new Perfil(response.getString("requerimientosEspecialesAcompanante"),"Significant other special requirements"));

                            adapter.notifyDataSetChanged();
                            //progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                /*progressDialog.dismiss();
                toastPersonalizado.crearToast("Error de conexión, revisa tu conexión a internet e intenta nuevamente", null);*/
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", "My useragent");
                return headers;
            }


        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);

    }

}
