package com.example.hospital_tilisarao.ui.turnoPendiente;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.request.ApiRest;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendienteViewModel extends AndroidViewModel {

    private MutableLiveData<List<Turno>> lista ;

    private Context context;

    public PendienteViewModel(@NonNull Application application) {
        super(application);
        lista = new MutableLiveData<>();

        context = application.getApplicationContext();
    }
    public MutableLiveData<List<Turno>> getLista() {
        return lista;
    }

    public void cargarTurnosPendientes(Bundle bundle) {
        //Turno t = (Turno) bundle.getSerializable("turno");
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<List<Turno>> pag = ApiRest.getMyApiClient().obtenerTurnosPendientes(token);
        pag.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){

                        lista.setValue(response.body());
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cancelarTurnos(int id) {
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<List<Turno>> pag = ApiRest.getMyApiClient().cancelarTurno(token,id);
        pag.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Toast.makeText(context,  "Turno Canceladooo", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Toast.makeText(context,  "Turno Canceladooo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}