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
import com.example.inmobiliariamiguel.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoContratoFragment extends Fragment {

    private RecyclerView rvContratoFragment;
    private AdapterPagoContrato adapter;
    private PagoContratoViewModel pagoContratoViewModel;
    private Context context;

    public static PagoContratoFragment newInstance() {
        return new PagoContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pago_contrato, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View root) {
        rvContratoFragment = root.findViewById(R.id.rvPagosContrato);
        pagoContratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagoContratoViewModel.class);
        pagoContratoViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
                rvContratoFragment.setLayoutManager(gridLayoutManager);
                adapter = new AdapterPagoContrato(context,pagos,getLayoutInflater());
                rvContratoFragment.setAdapter(adapter);
            }
        });
        pagoContratoViewModel.cargarPagosDeContratos(getArguments());
    }



}