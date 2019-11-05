package com.administra.kforum2020.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.kforum2020.Model.MySingleton;
import com.administra.kforum2020.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    EditText emailET, passwordET;
    Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.email_login);
        passwordET = findViewById(R.id.password_login);
        loginBTN = findViewById(R.id.login_btn);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void loginHttp(){
        HashMap<String, Object> map = new HashMap<>();// Mapeo previo

        map.put("id_customer", idCustomer);
        JSONObject jsonObject = new JSONObject(map);
        String url = "https://www.themyt.com/conekta_web/InformacionPago.php";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    int estado=0;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Iterator<?> keys = response.keys();

                            while( keys.hasNext() ) {
                                String key = (String)keys.next();
                                if ( response.get(key) instanceof JSONObject ) {
                                    JSONObject jsonObjectData= new JSONObject (((JSONObject) response.get(key)).get("data").toString());

                                    Iterator<?> keys2 = jsonObjectData.keys();

                                    while( keys2.hasNext() ) {

                                        String key2 = (String)keys2.next();
                                        crearRadio( ((JSONObject) jsonObjectData.get(key2)).getString("last4").toString() , ((JSONObject) jsonObjectData.get(key2)).getString("default").toString()  ,((JSONObject) jsonObjectData.get(key2)).getString("brand").toString() );
                                        hmTarjetas.put(Integer.valueOf( ((JSONObject) jsonObjectData.get(key2)).getString("last4").toString() ),  ((JSONObject) jsonObjectData.get(key2)).getString("id").toString() );
                                    }
                                }
                            }
                            estado= response.getInt("estado");
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                toastPersonalizado.crearToast("Error de conexión, revisa tu conexión a internet e intenta nuevamente", null);
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