package com.example.inmobiliariamiguel.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.ui.inmuebles.InmuebleAdapter;
import com.example.inmobiliariamiguel.ui.inmuebles.InmueblesViewModel;

import java.util.ArrayList;
import java.util.List;

public class InquilinosFragment extends Fragment {

    private RecyclerView rvInquilino;
    private AdapterInquilino adapter;
    private InquilinosViewModel inquilinosViewModel;
    private Context context;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilinos_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }
    private void inicializar(View root) {
        rvInquilino = root.findViewById(R.id.rvInquilino);
        inquilinosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinosViewModel.class);
        inquilinosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvInquilino.setLayoutManager(gridLayoutManager);
                adapter = new AdapterInquilino(context,inmuebles,getLayoutInflater());
                rvInquilino.setAdapter(adapter);
            }
        });
        inquilinosViewModel.cargarInmueblesAlquilados();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



}