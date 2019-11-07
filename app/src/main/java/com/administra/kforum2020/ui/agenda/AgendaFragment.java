package com.administra.kforum2020.ui.agenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.administra.kforum2020.Adaptadores.AgendaAdapter;
import com.administra.kforum2020.Adaptadores.SpeakerAdapter;
import com.administra.kforum2020.Interfaces.IfFirebaseLoadDone;
import com.administra.kforum2020.Model.Agenda;
import com.administra.kforum2020.Model.Speakers;
import com.administra.kforum2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AgendaFragment extends Fragment implements IfFirebaseLoadDone {
    View rootView;
    ArrayList<Agenda> alInfo ;
    AgendaAdapter adapter;
    ListView lista;
    static String COLECCION = "agenda";
    private AgendaViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AgendaViewModel.class);
        rootView = inflater.inflate(R.layout.fragment_agenda, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);

        lista = rootView.findViewById(R.id.lista_agenda);

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
                            ArrayList<Agenda>al1= new ArrayList<>();
                            Agenda header = new Agenda("Day 1 | November 14th","Day 1 | November 14th");
                            header.header=true;
                            al1.add(header);
                            ArrayList<Agenda>al2= new ArrayList<>();
                            Agenda header2 = new Agenda("Day 2 | November 15th","Day 2 | November 15th");
                            header2.header=true;
                            al2.add(header2);

                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("hola2", document.getId() + " => " + document.getData());
                                if(document.getLong("dia").intValue()==1&&document.getLong("participante").intValue()==1){
                                    al1.add(new Agenda(document.getString("hora"),
                                            document.getString("evento")
                                            ));
                                }else if(document.getLong("dia").intValue()==2&&document.getLong("participante").intValue()==1){
                                    al2.add(new Agenda(document.getString("hora"),
                                            document.getString("evento")
                                    ));

                                }//else if

                            }//for
                            alInfo.addAll(al1);
                            alInfo.addAll(al2);



                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
        adapter = new AgendaAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onFirebaseLoadSuccess() {
        adapter.notifyDataSetChanged();

    }
}