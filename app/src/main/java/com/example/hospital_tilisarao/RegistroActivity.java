package com.example.hospital_tilisarao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private EditText etDni, etNombre, etApellido, etTelefono, etContraseña, etMail, etContraseña2;
    private Button btRegistrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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

                    Call<Paciente> prop = ApiRest.getMyApiClient().altaPropietario(paciente);
                    prop.enqueue(new Callback<Paciente>() {
                        @Override
                        public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(RegistroActivity.this, "Usuario registrado con exito.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(RegistroActivity.this, "Kasi jaj", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Paciente> call, Throwable t) {

                            Toast.makeText(RegistroActivity.this, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(RegistroActivity.this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
