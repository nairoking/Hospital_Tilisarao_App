package com.example.hospital_tilisarao.ui.historial;

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

import com.example.hospital_tilisarao.Modelo.HistorialMedico;
import com.example.hospital_tilisarao.R;

import java.util.List;

public class HistorialFragment extends Fragment {

    private HistorialViewModel mViewModel;
    public RecyclerView rvHistorial;
    private Context context;
    private HistorialAdapter adapter;

    public static HistorialFragment newInstance() {
        return new HistorialFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historial, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View root){
        rvHistorial = root.findViewById(R.id.rvHistorial);
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistorialViewModel.class);
        mViewModel.getHistorial().observe(getViewLifecycleOwner(), new Observer<List<HistorialMedico>>() {
            @Override
            public void onChanged(List<HistorialMedico> historialMedicos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
                rvHistorial.setLayoutManager(gridLayoutManager);
                adapter = new HistorialAdapter(context, historialMedicos,getLayoutInflater());
                rvHistorial.setAdapter(adapter);
            }
        });
        mViewModel.cargarHistorial();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistorialViewModel.class);
        // TODO: Use the ViewModel
    }

}