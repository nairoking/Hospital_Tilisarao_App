package com.example.hospital_tilisarao;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hospital_tilisarao.MainActivity;
import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.RegistroActivity;
import com.example.hospital_tilisarao.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }

    public void registrar(Paciente paciente){
        Call<Paciente> prop = ApiRest.getMyApiClient().altaPaciente(paciente);
        prop.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Usuario registrado con exito.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else {
                    Toast.makeText(context, "Kasi jaj", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
