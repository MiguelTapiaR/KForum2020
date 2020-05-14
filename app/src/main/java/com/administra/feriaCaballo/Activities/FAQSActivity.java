package com.administra.feriaCaballo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.feriaCaballo.Adaptadores.FAQSAdapter;
import com.administra.feriaCaballo.Model.FAQS;
import com.administra.feriaCaballo.R;

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
        alInfo.add(new FAQS(getResources().getString(R.string.p2),getResources().getString(R.string.r2)));
        alInfo.add(new FAQS(getResources().getString(R.string.p3),getResources().getString(R.string.r3)));
        alInfo.add(new FAQS(getResources().getString(R.string.p4),getResources().getString(R.string.r4)));
        alInfo.add(new FAQS(getResources().getString(R.string.p5),getResources().getString(R.string.r5)));
        alInfo.add(new FAQS(getResources().getString(R.string.p6),getResources().getString(R.string.r6)));
        alInfo.add(new FAQS(getResources().getString(R.string.p7),getResources().getString(R.string.r7)));
        alInfo.add(new FAQS(getResources().getString(R.string.p8),getResources().getString(R.string.r8)));
        alInfo.add(new FAQS(getResources().getString(R.string.p9),getResources().getString(R.string.r9)));
        alInfo.add(new FAQS(getResources().getString(R.string.p10),getResources().getString(R.string.r10)));
        alInfo.add(new FAQS(getResources().getString(R.string.p11),getResources().getString(R.string.r11)));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FAQSeleccionadaActivity.class);
                intent.putExtra("faq", alInfo.get(i) );

                startActivity(intent);
            }
        });
        adapter = new FAQSAdapter(getApplicationContext(), alInfo);
        lista.setAdapter(adapter);
    }
}