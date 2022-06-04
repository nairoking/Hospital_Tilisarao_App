package com.example.hospital_tilisarao.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;



public class PerfilFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel galleryViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}