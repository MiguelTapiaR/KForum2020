package com.administra.kforum2020.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.administra.kforum2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    static String COLECCION = "slider";
    ImageView imagenHeader;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = root.findViewById(R.id.nombre_home);

        String id = (String) getActivity().getIntent().getExtras().getSerializable("id");
        String nombre = (String) getActivity().getIntent().getExtras().getSerializable("nombre");
        textView.setText(nombre);

        ImageView imagen = (ImageView) root.findViewById(R.id.imageViewQR);
        Picasso.get().load("https://www.registro-eventos.com/core/2019/backend/qrgenerator/generate.php?text="+id).into(imagen);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        imagenHeader=root.findViewById(R.id.imageViewPleca);



        db.collection(COLECCION).orderBy("orden")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Picasso.get().load(document.getString("imagen")).into(imagenHeader);
                            }
                        }//if
                    }
                });
        return root;
    }
}