package com.administra.kforum2020.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.administra.kforum2020.Model.Menu;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Menu> datos;


    public MenuAdapter(Context context, ArrayList datos) {
        super(context, R.layout.renglon_menu, datos);

        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.renglon_menu, null);

        //Imagen
        ImageView imagen = (ImageView) item.findViewById(R.id.imagen_menu);
        Picasso.get().load(datos.get(position).imagen).into(imagen);
        //Nombre
        TextView texto = (TextView) item.findViewById(R.id.texto_menu);
        texto.setText(datos.get(position).texto);


        return item;

    }
}
