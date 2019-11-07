package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        View item;
        if(datos.get(position).header){
            item = inflater.inflate(R.layout.renglon_header, null);
            TextView header = item.findViewById(R.id.titulo_header);
            header.setText(datos.get(position).evento);
        }else{
            item = inflater.inflate(R.layout.renglon_agenda, null);
            TextView hora=item.findViewById(R.id.hora_agenda);
            TextView evento=item.findViewById(R.id.evento_agenda);
            hora.setText(datos.get(position).hora);
            evento.setText(datos.get(position).evento);
        }





        return item;

    }
}