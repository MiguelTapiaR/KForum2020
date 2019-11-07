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
        switch (datos.get(position).id){
            case 1:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 2:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 3:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 4:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 5:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 6:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 7:
                imagen.setImageResource(R.drawable.download_64);
                break;
            case 8:
                imagen.setImageResource(R.drawable.download_64);
                break;



        }
        //Nombre
        TextView texto = (TextView) item.findViewById(R.id.texto_menu);
        texto.setText(datos.get(position).texto);


        return item;

    }
}
