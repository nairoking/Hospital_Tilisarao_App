package com.example.hospital_tilisarao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hospital_tilisarao.Modelo.Paciente;

public class MainActivity extends AppCompatActivity {

    private Button btIngresar;
    private Button btRegistro;
    private EditText etUser, etPass;
    private MainActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);}

        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializarVista();
        mv.inicioAutomatico();
        mv.getTokenMD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                mv.token(token);
            }
        });
        mv.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
        mv.getPropietario().observe(this, new Observer<Paciente>() {
            @Override
            public void onChanged(Paciente p) {
                Intent intent = new Intent(MainActivity.this, Menu_main_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializarVista(){

        btRegistro = findViewById(R.id.btRegistrar);
        btIngresar = findViewById(R.id.btIngresar);
        etUser =findViewById(R.id.etUser);
        etPass =findViewById(R.id.etPass);

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.iniciarSesion(etUser.getText().toString(),etPass.getText().toString());

            }
        });
    }
}