package com.administra.feriaCaballo.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.feriaCaballo.Model.MySingleton;
import com.administra.feriaCaballo.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity  extends AppCompatActivity {

    EditText nombreET, emailET, mensajeET;
    Button enviarBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        nombreET = findViewById(R.id.nombre_contacto_et);
        emailET = findViewById(R.id.email_contacto_et);
        mensajeET = findViewById(R.id.mensaje_contacto_et);

        enviarBTN = findViewById(R.id.btn_enviar_contacto);

        enviarBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginHttp();
            }
        });

    }

    public void loginHttp(){
        HashMap<String, Object> map = new HashMap<>();// Mapeo previo
        String email=emailET.getText().toString();
        String nombre=nombreET.getText().toString();
        String mensaje=mensajeET.getText().toString();
        map.put("email", email);
        map.put("nombre", nombre);
        map.put("mensaje", mensaje);
        JSONObject jsonObject = new JSONObject(map);
        String url = "https://www.kforum2020.com/backend/apps/contact_mail.php";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,
                new Response.Listener<JSONObject>() {

                    int estado=0;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            Toast.makeText(getApplicationContext(),"Email sent successfully", Toast.LENGTH_LONG).show();
                            finish();
                            response.getString("estado");
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
