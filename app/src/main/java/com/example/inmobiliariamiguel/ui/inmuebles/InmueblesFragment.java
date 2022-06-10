package com.example.inmobiliariamiguel.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;


public class InmueblesFragment extends Fragment {

  
    private RecyclerView rvInmuebles;
    private InmuebleAdapter adapter;
    private InmueblesViewModel inmueblesViewModel;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmueblesViewModel slideshowViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_inmuebles, container, false);
        context = root.getContext();
        inicializar(root);
        
        return root;
    }

    private void inicializar(View root) {
        rvInmuebles = root.findViewById(R.id.rvInmuebles);
        inmueblesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesViewModel.class);
        inmueblesViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmuebleAdapter(context,inmuebles,getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });
        inmueblesViewModel.cargarInmuebles();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}