package com.example.hospital_tilisarao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospital_tilisarao.Modelo.Paciente;

public class RegistroActivity extends AppCompatActivity {

    private EditText etDni, etNombre, etApellido, etTelefono, etContraseña, etMail, etContraseña2;
    private Button btRegistrar;
    private RegistroActivityViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        inicalizarVista();
    }

    public void inicalizarVista(){
        etDni= findViewById(R.id.etDniRegistro);
        etNombre=findViewById(R.id.etNombreRegistro);
        etApellido=findViewById(R.id.etApellidoRegistro);
        etTelefono=findViewById(R.id.etTelefonoRegistro);
        etMail=findViewById(R.id.etEmailRegistro);
        etContraseña=findViewById(R.id.etContraseñaRegistro);
        btRegistrar=findViewById(R.id.btRegistrarUser);
        etContraseña2 = findViewById(R.id.etContraseña2Registro);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etContraseña.getText().toString().equals(etContraseña2.getText().toString())){
                    Paciente paciente = new Paciente();
                    paciente.setDni(etDni.getText().toString());
                    paciente.setApellido(etApellido.getText().toString());
                    paciente.setNombre(etNombre.getText().toString());
                    paciente.setTelefono(etTelefono.getText().toString());
                    paciente.setClave(etContraseña.getText().toString());
                    paciente.setEmail(etMail.getText().toString());

                    mViewModel.registrar(paciente);

                }else{
                    Toast.makeText(RegistroActivity.this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
