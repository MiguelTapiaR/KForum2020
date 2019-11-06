package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.administra.kforum2020.Model.Descarga;
import com.administra.kforum2020.Model.Perfil;
import com.administra.kforum2020.R;

import java.util.ArrayList;

public class PerfilAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Perfil> datos;


    public PerfilAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_perfil, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_perfil, null);

        //Imagen
        /*ImageView imagen = (ImageView) item.findViewById(R.id.imagen_s);
        Picasso.get().load(datos.get(position).getImagenSpeaker()).into(imagen);*/
        //Nombre
        TextView nombreSpeaker = (TextView) item.findViewById(R.id.titulo_descarga);
        nombreSpeaker.setText(datos.get(position).encabezado);
        //Conferencia
        TextView tituloSP = (TextView) item.findViewById(R.id.valor_perfil);
        tituloSP.setText(datos.get(position).valor);


        return item;

    }
}