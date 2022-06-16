package com.example.hospital_tilisarao.ui.historial;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospital_tilisarao.Modelo.HistorialMedico;
import com.example.hospital_tilisarao.R;

public class HistorialDetalleFragment extends Fragment {

    private HistorialDetalleViewModel mViewModel;

    private TextView tvMedico, tvFecha, tvDesctripcion;

    public static HistorialDetalleFragment newInstance() {
        return new HistorialDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_historial_detalle, container, false);
        inicializar(root);
        return root;
    }
    private void inicializar(View view){
        tvMedico = view.findViewById(R.id.tvMedicoDetalleHistorial);
        tvFecha = view.findViewById(R.id.tvFechaDetalleHistorial);
        tvDesctripcion = view.findViewById(R.id.tvDescripcionHistorial);

        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistorialDetalleViewModel.class);
        mViewModel.getHistorial().observe(getActivity(), new Observer<HistorialMedico>() {
            @Override
            public void onChanged(HistorialMedico historialMedico) {
                tvMedico.setText(historialMedico.getMedico().getItem() + "");
                tvFecha.setText(historialMedico.getFecha() + "");
                tvDesctripcion.setText(historialMedico.getDescripcion() + "");
            }
        });
        mViewModel.cargarHistorial(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistorialDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}