package com.administra.feriaCaballo.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.administra.feriaCaballo.Activities.ContactUsActivity;
import com.administra.feriaCaballo.Activities.FAQSActivity;
import com.administra.feriaCaballo.Activities.LoginActivity;
import com.administra.feriaCaballo.Activities.LugarActivity;
import com.administra.feriaCaballo.Activities.PerfilActivity;
import com.administra.feriaCaballo.Activities.VenueDia2;
import com.administra.feriaCaballo.Adaptadores.MenuAdapter;
import com.administra.feriaCaballo.Interfaces.IfFirebaseLoadDone;
import com.administra.feriaCaballo.Model.Menu;
import com.administra.feriaCaballo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuFragment extends Fragment implements IfFirebaseLoadDone {

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
        alInfo.add(new Menu("Picadero", 1));
//        alInfo.add(new Menu("Q & A", 2));
        alInfo.add(new Menu("Eventos Especiales", 2));
        alInfo.add(new Menu("Ubicación", 6));
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("menu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("hola2", document.getId() + " => " + document.getData());
                                if(document.getLong("activo").intValue()==1){
                                    alInfo.add(new Menu("My profile", 1));
//        alInfo.add(new Menu("Q & A", 2));
                                    alInfo.add(new Menu("Venue", 2));
                                    alInfo.add(new Menu("Venue Day Two", 6));
//        alInfo.add(new Menu("Download content", 4));
                                    alInfo.add(new Menu("Contact us", 3));
                                    alInfo.add(new Menu("FAQs", 4));
//        alInfo.add(new Menu("Polls", 7));
                                    alInfo.add(new Menu("Log out", 5));
                                }else if(document.getLong("activo").intValue()==0){


                                    alInfo.add(new Menu("My profile", 1));
//        alInfo.add(new Menu("Q & A", 2));
                                    alInfo.add(new Menu("Venue", 2));
//        alInfo.add(new Menu("Download content", 4));
                                    alInfo.add(new Menu("Contact us", 3));
                                    alInfo.add(new Menu("FAQs", 4));
//        alInfo.add(new Menu("Polls", 7));
                                    alInfo.add(new Menu("Log out", 5));
                                }




                            }



                            onFirebaseLoadSuccess();
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });


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
                switch (alInfo.get(posicion).id) {

                    case 1:
                        intent = new Intent(getContext(), PerfilActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getContext(), LugarActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(getContext(), VenueDia2.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getContext(), ContactUsActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getContext(), FAQSActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        logout();
                        break;



                }
//
            }
        });


        return rootView;
    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFirebaseLoadSuccess() {
        adapter.notifyDataSetChanged();

    }
}
