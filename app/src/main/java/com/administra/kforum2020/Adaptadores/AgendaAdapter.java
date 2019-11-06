package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.administra.kforum2020.Model.Agenda;
import com.administra.kforum2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AgendaAdapter  extends ArrayAdapter {
    private Context context;
    private ArrayList<Agenda> datos;


    public AgendaAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_agenda, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_agenda, null);

        /*ImageView imagen = (ImageView) item.findViewById(R.id.imagen_patrocinador);



        Picasso.get().load(datos.get(position).getUrlFoto()).into(imagen);
*/


        return item;

    }
}