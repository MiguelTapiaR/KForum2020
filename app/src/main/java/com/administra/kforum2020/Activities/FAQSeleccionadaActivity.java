package com.administra.kforum2020.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.kforum2020.Model.FAQS;
import com.administra.kforum2020.R;

public class FAQSeleccionadaActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqseleccionada);
        FAQS faq = (FAQS) getIntent().getExtras().getSerializable("faq");

        TextView pregunt=findViewById(R.id.pregunta_seleccionada);
        pregunt.setText(faq.pregunta);
        TextView respuesta=findViewById(R.id.respuesta_seleccionada);
        respuesta.setText(faq.respuesta);

    }
}
