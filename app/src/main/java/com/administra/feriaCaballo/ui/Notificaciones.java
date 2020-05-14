package com.administra.feriaCaballo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.administra.feriaCaballo.R;
import com.administra.feriaCaballo.ui.ui.notificaciones.NotificacionesFragment;


public class Notificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificaciones_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NotificacionesFragment.newInstance())
                    .commitNow();
        }
    }
}
