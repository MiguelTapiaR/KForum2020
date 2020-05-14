package com.administra.feriaCaballo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administra.feriaCaballo.Model.Detalle;
import com.administra.feriaCaballo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetallesAdapter  extends ArrayAdapter {
    private Context context;
    private ArrayList<Detalle> datos;


    public DetallesAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_detalles, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item;


            item = inflater.inflate(R.layout.renglon_detalles, null);

            //Imagen
            ImageView imagen = (ImageView) item.findViewById(R.id.imagen_speaker);
            Picasso.get().load(datos.get(position).foto).into(imagen);
            //Nombre
            TextView titulo = (TextView) item.findViewById(R.id.renglon1);
        titulo.setText(datos.get(position).titulo);
            //Nombre
            TextView nombreSpeaker = (TextView) item.findViewById(R.id.renglon2);
            nombreSpeaker.setText(datos.get(position).nombre);
            //Conferencia
            TextView tituloSP = (TextView) item.findViewById(R.id.renglon3);
            tituloSP.setText(datos.get(position).puesto);




        return item;

    }
}