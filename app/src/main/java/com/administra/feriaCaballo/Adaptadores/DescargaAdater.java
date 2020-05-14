package com.administra.feriaCaballo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.administra.feriaCaballo.Model.Descarga;
import com.administra.feriaCaballo.R;

import java.util.ArrayList;

public class DescargaAdater extends ArrayAdapter {
    private Context context;
    private ArrayList<Descarga> datos;


    public DescargaAdater(Context context, ArrayList datos) {
        super(context, R.layout.renglon_descarga, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_descarga, null);

        //Imagen
        /*ImageView imagen = (ImageView) item.findViewById(R.id.imagen_s);
        Picasso.get().load(datos.get(position).getImagenSpeaker()).into(imagen);*/
        //Nombre
        TextView nombreSpeaker = (TextView) item.findViewById(R.id.nombre_descarga);
        nombreSpeaker.setText(datos.get(position).titulo);
        //Conferencia
        TextView tituloSP = (TextView) item.findViewById(R.id.titulo_descarga);
        tituloSP.setText(datos.get(position).speaker);


        return item;

    }
}