package com.administra.kforum2020.Views;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.administra.kforum2020.R;

public class Loading {
    Activity act;
    public Loading(Activity a) {
        act = a;
    }

    public void crearToast(String mensaje, Drawable icon) {
        LayoutInflater inflater = act.getLayoutInflater();
        View layout = inflater.inflate(R.layout.splash, null);



        Toast notificacion = new Toast(act.getApplicationContext());
        notificacion.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        notificacion.setDuration(Toast.LENGTH_LONG);
        notificacion.setView(layout);
        notificacion.show();
    }
}
