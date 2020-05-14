package com.administra.feriaCaballo.ui.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.administra.feriaCaballo.Activities.DetalleAgendaActivity;
import com.administra.feriaCaballo.Adaptadores.AgendaAdapter;
import com.administra.feriaCaballo.Adaptadores.SpeakerAdapter;
import com.administra.feriaCaballo.Interfaces.IfFirebaseLoadDone;
import com.administra.feriaCaballo.Model.Agenda;
import com.administra.feriaCaballo.Model.Speakers;
import com.administra.feriaCaballo.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;

public class AgendaFragment extends Fragment implements IfFirebaseLoadDone {
    View rootView;
    ArrayList<Speakers> alInfo ;
    SpeakerAdapter adapter;
    ListView lista;
    static String COLECCION = "palenque";
    RelativeLayout loading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_speakers, container, false);

        lista = rootView.findViewById(R.id.lista_speakers);
        loading = rootView.findViewById(R.id.loadingPanel);

        //ADMOB
        MobileAds.initialize(container.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(container.getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getResources().getString(R.string.clave_xml));
        AdView  mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


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




                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("hola2", document.getId() + " => " + document.getData());

                                al1.add(new Speakers(document.getString("nombre"),
                                        document.getString("fecha"),
                                        "",
                                        document.getString("imagen"),
                                        document.getString("pleca"),
                                        "",
                                        document.getLong("orden").intValue()));



                            }
                            alInfo.addAll(al1);


                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

//                Intent intent = new Intent(getContext(), VerSpeakerActivity.class);
//                intent.putExtra("speaker", alInfo.get(posicion) );
//
//                startActivity(intent);
            }
        });
        adapter = new SpeakerAdapter(rootView.getContext(), alInfo);
        lista.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onFirebaseLoadSuccess() {

        loading.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}