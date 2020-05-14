package com.administra.feriaCaballo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
        MobileAds.initialize(container.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(container.getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getResources().getString(R.string.clave_xml));
        AdView  mAdView = root.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView adView2 = new AdView(container.getContext());
        adView2.setAdSize(AdSize.BANNER);
        adView2.setAdUnitId(getResources().getString(R.string.clave_xml));
        AdView  mAdView2 = root.findViewById(R.id.adView2);

        mAdView2.loadAd(adRequest);
// TODO: Add adView to your view hierarchy.

//        String id = (String) getActivity().getIntent().getExtras().getSerializable("id");
//        String nombre = (String) getActivity().getIntent().getExtras().getSerializable("nombre");
//        textView.setText(nombre);
//
//        ImageView imagen = (ImageView) root.findViewById(R.id.imageViewQR);
//        Picasso.get().load("https://www.registro-eventos.com/core/2019/backend/qrgenerator/generate.php?text="+id).into(imagen);
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