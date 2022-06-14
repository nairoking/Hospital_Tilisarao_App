package com.example.hospital_tilisarao.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.Modelo.Medico;
import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.R;
import com.example.hospital_tilisarao.request.ApiRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoTurnoViewModel extends AndroidViewModel {

    private Context context;
    private List<Medico> medicos = new ArrayList<>();;
    private List<String> lista = new ArrayList<>() ;

    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();


    }

    public List<String> getLista() {
        return lista;
    }
    public List<Medico> getMedicos() {
        return medicos;
    }


    public void cargarMedico(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        retrofit2.Call<List<Medico>> pag;
         pag = ApiRest.getMyApiClient().obtenerMedicos(token);
        pag.enqueue(new Callback<List<Medico>>() {
            @Override
            public void onResponse(Call<List<Medico>> call, Response<List<Medico>> response) {
                if(response.isSuccessful()){
                    for (Medico m : response.body())
                    {
                        lista.add(m.getItem() + "");
                        medicos.add(m);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Medico>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}