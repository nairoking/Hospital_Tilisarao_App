package com.example.hospital_tilisarao.ui.turnoPendiente;

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

import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.R;

import java.util.List;


public class PendienteFragment extends Fragment {

    public RecyclerView rvPendientes;
    public PendienteViewModel pvm;
    public AdapterTurnoPendiente adapter;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

                pvm = new ViewModelProvider(this).get(PendienteViewModel.class);

        View root = inflater.inflate(R.layout.fragment_pendiente, container, false);
        context = root.getContext();
        inicializar(root);


        return root;
    }

    private void inicializar(View root) {

        rvPendientes = root.findViewById(R.id.rvTurnosPendientes);

        pvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PendienteViewModel.class);
        pvm.getLista().observe(getViewLifecycleOwner(), new Observer<List<Turno>>() {
            @Override
            public void onChanged(List<Turno> turnos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false);
                rvPendientes.setLayoutManager(gridLayoutManager);
                adapter = new AdapterTurnoPendiente(context,turnos,getLayoutInflater());
                rvPendientes.setAdapter(adapter);
            }
        });

        pvm.cargarTurnosPendientes(getArguments());
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}