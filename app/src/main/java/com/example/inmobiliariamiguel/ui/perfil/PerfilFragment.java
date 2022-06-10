package com.example.inmobiliariamiguel.ui.perfil;

import android.content.Context;
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

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private EditText etDni, etNombre, etApellido, etTelefono, etContraseña, etMail;
    private Button btEditar;
    private PerfilViewModel perfilViewModel;
    private Propietario prop;
    private Switch swEditar;
    int id;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializarVista(root);
        perfilViewModel.iniciar();

        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                prop = propietario;
                id = propietario.getId();
                etDni.setText(propietario.getDni());
                etApellido.setText(propietario.getApellido());
                etNombre.setText(propietario.getNombre());
                etTelefono.setText(propietario.getTelefono());
                etMail.setText(propietario.getEmail());
                etContraseña.setText(propietario.getclave());
            }
        });
        /*perfilViewModel.getEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etDni.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etNombre.setEnabled(aBoolean);
                etTelefono.setEnabled(aBoolean);
                etMail.setEnabled(aBoolean);
                etContraseña.setEnabled(aBoolean);
            }
        });

        perfilViewModel.getTextoBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btEditar.setText(s);
            }
        });


        perfilViewModel.traerDatos();*/

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
                Propietario propietario=new Propietario();
                propietario.setDni(etDni.getText().toString());
                propietario.setApellido(etApellido.getText().toString());
                propietario.setNombre(etNombre.getText().toString());
                propietario.setTelefono(etTelefono.getText().toString());
                propietario.setClave(etContraseña.getText().toString());
                propietario.setEmail(etMail.getText().toString());
                propietario.setId(id);

                perfilViewModel.cambiar(propietario);

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