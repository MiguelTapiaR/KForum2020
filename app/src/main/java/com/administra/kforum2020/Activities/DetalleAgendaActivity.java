package com.administra.kforum2020.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.administra.kforum2020.Adaptadores.AgendaAdapter;
import com.administra.kforum2020.Adaptadores.DetallesAdapter;
import com.administra.kforum2020.Adaptadores.PerfilAdapter;
import com.administra.kforum2020.Interfaces.IfFirebaseLoadDone;
import com.administra.kforum2020.Model.Agenda;
import com.administra.kforum2020.Model.Detalle;
import com.administra.kforum2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetalleAgendaActivity extends AppCompatActivity implements IfFirebaseLoadDone {

    TextView texto;
    Agenda agenda;
    ArrayList<Detalle> alInfo ;
    DetallesAdapter adapter;
    ListView lista;
    String COLECCION = "agenda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agenda);

        agenda = (Agenda) getIntent().getExtras().getSerializable("agenda");
        COLECCION = "agenda/"+agenda.id+"/detalle/";
        texto = findViewById(R.id.titulo_detalle);

        texto.setText(agenda.evento);

        lista = findViewById(R.id.lista_detalle);
        alInfo = new ArrayList();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(COLECCION).orderBy("orden")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {

                                alInfo.add(new Detalle(
                                        document.getString("nombre"),
                                        document.getString("subtitulo"),
                                        document.getString("puesto"),
                                        document.getString("imagen")));
//                                Log.d("hola2", document.getId() + " => " + document.getData());


                            }//for




                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
        adapter = new DetallesAdapter(getApplicationContext(), alInfo);
        lista.setAdapter(adapter);

    }

    @Override
    public void onFirebaseLoadSuccess() {
        adapter.notifyDataSetChanged();
    }
}