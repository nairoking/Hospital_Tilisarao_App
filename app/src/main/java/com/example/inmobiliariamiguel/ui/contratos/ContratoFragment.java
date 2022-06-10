package com.example.inmobiliariamiguel.ui.contratos;

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

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.ui.inmuebles.InmuebleAdapter;
import com.example.inmobiliariamiguel.ui.inmuebles.InmueblesViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContratoFragment extends Fragment {

    private RecyclerView rvContrato;
    private AdapterInmuebleAlquilado adapter;
    private ContratoViewModel contratoViewModel;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmueblesViewModel slideshowViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);

        View root = inflater.inflate(R.layout.contrato_fragment, container, false);
        context = root.getContext();
        inicializar(root);

        return root;
    }

    private void inicializar(View root) {
        rvContrato = root.findViewById(R.id.rvContratos);
        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        contratoViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
                rvContrato.setLayoutManager(gridLayoutManager);
                adapter = new AdapterInmuebleAlquilado(context,inmuebles,getLayoutInflater());
                rvContrato.setAdapter(adapter);
            }
        });
        contratoViewModel.cargarInmueblesAlquilados();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}