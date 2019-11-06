package com.administra.kforum2020.ui.speakers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.administra.kforum2020.Adaptadores.SpeakerAdapter;
import com.administra.kforum2020.Interfaces.IfFirebaseLoadDone;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SpeakersFragment extends Fragment implements IfFirebaseLoadDone {
    View rootView;
    ArrayList<Speakers> alInfo ;
    SpeakerAdapter adapter;
    ListView lista;
    static String COLECCION = "speakers";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_speakers, container, false);

        lista = rootView.findViewById(R.id.lista_speakers);


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

                                alInfo.add(new Speakers(document.getString("nombre"),
                                        document.getString("titulo"),
                                        "",
                                        document.getString("imagen"),
                                        document.getString("plecaSpeaker"),
                                        document.getString("bio"),
                                        document.getLong("orden").intValue()
                                        ));
                            }
                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        adapter = new SpeakerAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onFirebaseLoadSuccess() {
        adapter.notifyDataSetChanged();
    }
}