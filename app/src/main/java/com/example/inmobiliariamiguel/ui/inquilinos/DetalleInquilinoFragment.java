package com.example.inmobiliariamiguel.ui.inquilinos;

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

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Contrato;
import com.example.inmobiliariamiguel.modelo.Inquilino;
import com.example.inmobiliariamiguel.ui.contratos.DetalleContratoViewModel;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel mViewModel;
    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvDni;
    private TextView tvEmail;
    private TextView tvTelefono;
    private TextView tvGarnate;
    private TextView tvTelGarante;

    public static DetalleInquilinoFragment newInstance() {
        return new DetalleInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
        inicializar(root);

        return root;
    }

    private void inicializar(View root) {
        tvNombre = root.findViewById(R.id.tvNombreInquilino);
        tvApellido = root.findViewById(R.id.tvApellidoInquilino);
        tvDni = root.findViewById(R.id.tvDNIInquilino);
        tvEmail = root.findViewById(R.id.tvEmailInquilino);
        tvTelefono = root.findViewById(R.id.tvTelefonoInquilino);


        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleInquilinoViewModel.class);

        mViewModel.getInquilino().observe(getActivity(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvNombre.setText(inquilino.getNombre() + " ");
                tvApellido.setText(inquilino.getApellido() + " ");
                tvDni.setText(inquilino.getDNI() + " ");
                tvTelefono.setText(inquilino.getTelefono() + " ");
                tvEmail.setText(inquilino.getEmail() + " ");

            }
        });
        mViewModel.cargarInquilino(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}