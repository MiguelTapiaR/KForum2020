package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.administra.kforum2020.Model.ConferenciaQAP;
import com.administra.kforum2020.Model.Perfil;
import com.administra.kforum2020.R;

import java.util.ArrayList;

public class QAAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ConferenciaQAP> datos;


    public QAAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_qa, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_qa, null);

        //Estado
        TextView estatus = (TextView) item.findViewById(R.id.estado_qa);
        if(datos.get(position).estado == 1){
            estatus.setBackgroundColor(Color.rgb(255,0,0));
            estatus.setText("LIVE");
        }else{
            estatus.setText("NOT ACTIVE");
        }


        //Conferencia
        TextView tituloSP = (TextView) item.findViewById(R.id.nombre_qa);
        tituloSP.setText(datos.get(position).nombre);


        return item;

    }
}