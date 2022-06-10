package com.example.inmobiliariamiguel.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.databinding.FragmentInicioBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment {

private TextView  tvHome;
    private GoogleMap mapa;
    private LatLng INMOBILIARIA_MIGUEL = new LatLng(-32.734684,  -65.288513);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioViewModel homeViewModel =
                new ViewModelProvider(this).get(InicioViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frMap);
        smf.getMapAsync(new MapaActual());


        return root;

    }
    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            mapa = googleMap;
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(INMOBILIARIA_MIGUEL)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate camUpdICT = CameraUpdateFactory.newCameraPosition(camPos);

            mapa.animateCamera((camUpdICT));

            mapa.addMarker(new MarkerOptions().position(INMOBILIARIA_MIGUEL)).setTitle("Inmobiliaria Miguel");

            mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            mapa.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    mapa.addMarker(new MarkerOptions().position(latLng).title("Marcador Nuevo"));

                }
            });

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}