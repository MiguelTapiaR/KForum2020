package com.administra.feriaCaballo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.administra.feriaCaballo.Model.Aviso;
import com.administra.feriaCaballo.R;

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
        TextView hora = (TextView) item.findViewById(R.id.hora_notificacion);
        hora.setText(datos.get(position).hora);



        return item;

    }
}