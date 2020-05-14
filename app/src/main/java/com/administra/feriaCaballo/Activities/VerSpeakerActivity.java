package com.administra.feriaCaballo.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.administra.feriaCaballo.Model.Speakers;
import com.administra.feriaCaballo.R;
import com.squareup.picasso.Picasso;

public class VerSpeakerActivity  extends AppCompatActivity {

    TextView texto;
    ImageView pleca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_speaker);

        Speakers speaker = (Speakers) getIntent().getExtras().getSerializable("speaker");

        pleca = findViewById(R.id.pleca_speaker);
        texto = findViewById(R.id.texto_speaker);
//        texto.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

        Picasso.get().load(speaker.getPlecaSpeaker()).into(pleca);
        texto.setText(speaker.getBiografia());



    }
}