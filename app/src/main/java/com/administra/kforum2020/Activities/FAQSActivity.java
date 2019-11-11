package com.administra.kforum2020.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.kforum2020.Adaptadores.FAQSAdapter;
import com.administra.kforum2020.Adaptadores.PerfilAdapter;
import com.administra.kforum2020.Model.FAQS;
import com.administra.kforum2020.Model.Perfil;
import com.administra.kforum2020.R;

import java.util.ArrayList;

public class FAQSActivity  extends AppCompatActivity {
    ArrayList<FAQS> alInfo ;
    FAQSAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        lista = findViewById(R.id.lista_faqs);


        //Lleno la informacion de base de datos
        alInfo = new ArrayList();

        alInfo.add(new FAQS(getResources().getString(R.string.p1),getResources().getString(R.string.r1)));
        adapter = new FAQSAdapter(getApplicationContext(), alInfo);
        lista.setAdapter(adapter);
    }
}