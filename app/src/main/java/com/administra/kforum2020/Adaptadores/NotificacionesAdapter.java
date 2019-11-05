package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administra.kforum2020.Model.Aviso;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NotificacionesAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Aviso> datos;


    public NotificacionesAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_notificacion, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_notificacion, null);


        //Texto
        TextView texto = (TextView) item.findViewById(R.id.texto_notificacion);
        texto.setText(datos.get(position).texto);



        return item;

    }
}