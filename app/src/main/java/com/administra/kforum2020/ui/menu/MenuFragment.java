package com.administra.kforum2020.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.administra.kforum2020.Activities.LugarActivity;
import com.administra.kforum2020.Activities.PerfilActivity;
import com.administra.kforum2020.Adaptadores.MenuAdapter;
import com.administra.kforum2020.Adaptadores.NotificacionesAdapter;
import com.administra.kforum2020.Model.Aviso;
import com.administra.kforum2020.Model.Menu;
import com.administra.kforum2020.R;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    View rootView;
    ArrayList<Menu> alInfo;
    MenuAdapter adapter;
    ListView lista;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuViewModel =
                ViewModelProviders.of(this).get(MenuViewModel.class);
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);


        lista = rootView.findViewById(R.id.lista_menu);


        //Lleno la informacion de base de datos
        alInfo = new ArrayList();

        alInfo.add(new Menu("My profile", 1));
        alInfo.add(new Menu("Q & A", 2));
        alInfo.add(new Menu("Venue", 3));
        alInfo.add(new Menu("Download content", 4));
        alInfo.add(new Menu("Contact us", 5));
        alInfo.add(new Menu("Help", 6));
        alInfo.add(new Menu("Polls", 7));
        alInfo.add(new Menu("Log out", 8));
        menuViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });
        adapter = new MenuAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent = null;
                switch (posicion+1) {

                    case 1:
                        intent = new Intent(getContext(), PerfilActivity.class);

                        break;
                    case 2:
                        intent = new Intent(getContext(), PerfilActivity.class);

                        break;
                    case 3:
                        intent = new Intent(getContext(), LugarActivity.class);
                        break;
                    case 4:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        break;
                    case 5:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        break;
                    case 6:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        break;
                    case 7:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        break;
                    case 8:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        break;


                }
                startActivity(intent);
            }
        });


        return rootView;
    }
}
