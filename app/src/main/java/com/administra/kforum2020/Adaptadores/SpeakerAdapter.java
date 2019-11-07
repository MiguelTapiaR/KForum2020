package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administra.kforum2020.Model.Agenda;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SpeakerAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Speakers> datos;


    public SpeakerAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_speaker, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item;
        if(datos.get(position).getHeader()){
             item = inflater.inflate(R.layout.renglon_header, null);
             TextView textView = item.findViewById(R.id.titulo_header);
             textView.setText(datos.get(position).getNombre());

        }else{
             item = inflater.inflate(R.layout.renglon_speaker, null);

            //Imagen
            ImageView imagen = (ImageView) item.findViewById(R.id.imagen_speaker);
            Picasso.get().load(datos.get(position).getImagenSpeaker()).into(imagen);
            //Nombre
            TextView nombreSpeaker = (TextView) item.findViewById(R.id.nombre_speaker);
            nombreSpeaker.setText(datos.get(position).getNombre());
            //Conferencia
            TextView tituloSP = (TextView) item.findViewById(R.id.titulo_sp);
            tituloSP.setText(datos.get(position).getTitulo());
        }



        return item;

    }
}