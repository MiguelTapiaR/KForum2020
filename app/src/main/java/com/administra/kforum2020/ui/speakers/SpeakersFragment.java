package com.administra.kforum2020.ui.speakers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.administra.kforum2020.Activities.VerSpeakerActivity;
import com.administra.kforum2020.Adaptadores.SpeakerAdapter;
import com.administra.kforum2020.Interfaces.IfFirebaseLoadDone;
import com.administra.kforum2020.MainActivity;
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
    RelativeLayout loading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_speakers, container, false);

        lista = rootView.findViewById(R.id.lista_speakers);
        loading = rootView.findViewById(R.id.loadingPanel);


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
                            ArrayList<Speakers>al1= new ArrayList<>();
                            Speakers header = new Speakers("Keynote speakers","","","","","",0);
                            header.setHeader(true);
                            al1.add(header);
                            ArrayList<Speakers>al2= new ArrayList<>();
                            Speakers header2 = new Speakers("Speakers","","","","","",0);
                            header2.setHeader(true);
                            al2.add(header2);
                            ArrayList<Speakers>al3= new ArrayList<>();
                            Speakers header3 = new Speakers("Panelists","","","","","",0);
                            header3.setHeader(true);
                            al3.add(header3);

                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("hola2", document.getId() + " => " + document.getData());
                                if(document.getLong("tipo").intValue()==1){
                                    al1.add(new Speakers(document.getString("nombre"),
                                            document.getString("titulo"),
                                            "",
                                            document.getString("imagen"),
                                            document.getString("plecaSpeaker"),
                                            getBio(document.getLong("orden").intValue()),
                                            document.getLong("orden").intValue()));
                                }else if(document.getLong("tipo").intValue()==2){
                                    al2.add(new Speakers(document.getString("nombre"),
                                            document.getString("titulo"),
                                            "",
                                            document.getString("imagen"),
                                            document.getString("plecaSpeaker"),
                                            getBio(document.getLong("orden").intValue()),
                                            document.getLong("orden").intValue()
                                    ));
                                }else if(document.getLong("tipo").intValue()==3){
                                    al3.add(new Speakers(document.getString("nombre"),
                                            document.getString("titulo"),
                                            "",
                                            document.getString("imagen"),
                                            document.getString("plecaSpeaker"),
                                            getBio(document.getLong("orden").intValue()),
                                            document.getLong("orden").intValue()
                                    ));
                                }




                            }
                            alInfo.addAll(al1);
                            alInfo.addAll(al2);
                            alInfo.addAll(al3);


                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                Intent intent = new Intent(getContext(), VerSpeakerActivity.class);
                intent.putExtra("speaker", alInfo.get(posicion) );

                startActivity(intent);
            }
        });
        adapter = new SpeakerAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);
        return rootView;
    }

    public String getBio(int orden){
        String respuesta = "";
        switch (orden){
            case 1:
                respuesta = getResources().getString(R.string.b1);
                break;
            case 2:
                respuesta = getResources().getString(R.string.b2);
                break;
            case 3:
                respuesta = getResources().getString(R.string.b3);
                break;
            case 4:
                respuesta = getResources().getString(R.string.b4);
                break;
            case 5:
                respuesta = getResources().getString(R.string.b5);
                break;
            case 6:
                respuesta = getResources().getString(R.string.b6);
                break;
            case 7:
                respuesta = getResources().getString(R.string.b7);
                break;
            case 8:
                respuesta = getResources().getString(R.string.b8);
                break;
            case 9:
                respuesta = getResources().getString(R.string.b9);
                break;
            case 10:
                respuesta = getResources().getString(R.string.b10);
                break;
            case 11:
                respuesta = getResources().getString(R.string.b11);
                break;
            case 12:
                respuesta = getResources().getString(R.string.b12);
                break;
            case 13:
                respuesta = getResources().getString(R.string.b13);
                break;
            case 14:
                respuesta = getResources().getString(R.string.b14);
                break;
            case 15:
                respuesta = getResources().getString(R.string.b15);
                break;
            case 16:
                respuesta = getResources().getString(R.string.b16);
                break;
            case 17:
                respuesta = getResources().getString(R.string.b17);
                break;
            case 18:
                respuesta = getResources().getString(R.string.b18);
                break;
            case 19:
                respuesta = getResources().getString(R.string.b19);
                break;
            case 20:
                respuesta = getResources().getString(R.string.b20);
                break;

        }
        return respuesta;
    }

    @Override
    public void onFirebaseLoadSuccess() {

        loading.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}