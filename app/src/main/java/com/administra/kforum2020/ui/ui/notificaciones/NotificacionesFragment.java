package com.administra.kforum2020.ui.ui.notificaciones;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.administra.kforum2020.Adaptadores.NotificacionesAdapter;
import com.administra.kforum2020.Adaptadores.SpeakerAdapter;
import com.administra.kforum2020.Interfaces.IfFirebaseLoadDone;
import com.administra.kforum2020.Model.Aviso;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.administra.kforum2020.ui.Notificaciones;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotificacionesFragment extends Fragment implements IfFirebaseLoadDone {

    private NotificacionesViewModel mViewModel;

    View rootView;
    ArrayList<Aviso> alInfo ;
    NotificacionesAdapter adapter;
    ListView lista;
    static String COLECCION = "notificaciones";

    public static NotificacionesFragment newInstance() {
        return new NotificacionesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.notificaciones_fragment, container, false);



        lista = rootView.findViewById(R.id.lista_notificaciones);


        //Lleno la informacion de base de datos
        alInfo = new ArrayList();
//Init DB
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(COLECCION).orderBy("orden")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("hola2", document.getId() + " => " + document.getData());

                                alInfo.add(new Aviso(

                                        document.getString("texto"),
                                        document.getString("id"),
                                        document.getString("url")
                                ));
                            }
                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        adapter = new NotificacionesAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificacionesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onFirebaseLoadSuccess() {
        adapter.notifyDataSetChanged();
    }
}
