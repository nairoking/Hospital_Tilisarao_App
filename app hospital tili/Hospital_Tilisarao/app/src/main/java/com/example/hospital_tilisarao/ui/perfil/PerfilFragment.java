package com.example.hospital_tilisarao.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.R;


public class PerfilFragment extends Fragment {


    private EditText etDni, etNombre, etApellido, etTelefono, etContraseña, etMail;
    private Button btEditar;
    private Switch swEditar;
    private PerfilViewModel perfilViewModel;
    int id;
    private Paciente pac;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =  new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializarVista(root);
        perfilViewModel.iniciar();

        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente paciente) {
                pac = paciente;
                id = paciente.getId();
                etDni.setText(paciente.getDni());
                etApellido.setText(paciente.getApellido());
                etNombre.setText(paciente.getNombre());
                etTelefono.setText(paciente.getTelefono());
                etMail.setText(paciente.getEmail());
                etContraseña.setText(paciente.getClave());
            }
        });

        return root;
    }

    private void inicializarVista(View root) {
        etDni=root.findViewById(R.id.etDniPerfil);
        etNombre=root.findViewById(R.id.etNombrePerfil);
        etApellido=root.findViewById(R.id.etApellidoPerfil);
        etTelefono=root.findViewById(R.id.etTelefonoPerfil);
        etMail=root.findViewById(R.id.etEmailPerfil);
        etContraseña=root.findViewById(R.id.etContraseñaPerfil);
        btEditar=root.findViewById(R.id.btEditarPerfil);
        swEditar = root.findViewById(R.id.swEditar);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paciente paciente=new Paciente();
                paciente.setDni(etDni.getText().toString());
                paciente.setApellido(etApellido.getText().toString());
                paciente.setNombre(etNombre.getText().toString());
                paciente.setTelefono(etTelefono.getText().toString());
                paciente.setClave("");
                paciente.setEmail(etMail.getText().toString());
                paciente.setId(id);

                perfilViewModel.cambiar(paciente);

            }
        });
        swEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swEditar.isChecked()){
                    etApellido.setEnabled(true);
                    etContraseña.setEnabled(true);
                    etMail.setEnabled(true);
                    etDni.setEnabled(true);
                    etNombre.setEnabled(true);
                    etTelefono.setEnabled(true);
                    btEditar.setVisibility(view.VISIBLE);

                    Toast.makeText(getContext(), "Edicion Habilidata", Toast.LENGTH_SHORT).show();
                }else{
                    etApellido.setEnabled(false);
                    etContraseña.setEnabled(false);
                    etMail.setEnabled(false);
                    etDni.setEnabled(false);
                    etNombre.setEnabled(false);
                    etTelefono.setEnabled(false);
                    btEditar.setVisibility(view.INVISIBLE);
                    Toast.makeText(getContext(), "Edicion Deshabilidata", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}