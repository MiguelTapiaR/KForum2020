package com.administra.kforum2020.ui.ui.notificaciones;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.administra.kforum2020.R;

public class NotificacionesFragment extends Fragment {

    private NotificacionesViewModel mViewModel;
    View rootView;

    public static NotificacionesFragment newInstance() {
        return new NotificacionesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.notificaciones_fragment, container, false);
        TextView hola = rootView.findViewById(R.id.message);
        hola.setText("Hola desde notidicaciones");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificacionesViewModel.class);
        // TODO: Use the ViewModel
    }

}
