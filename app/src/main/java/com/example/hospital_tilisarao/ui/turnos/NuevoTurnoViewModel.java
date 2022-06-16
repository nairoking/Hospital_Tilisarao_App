package com.example.hospital_tilisarao.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.MainActivity;
import com.example.hospital_tilisarao.Modelo.Medico;
import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.Modelo.TurnoView;
import com.example.hospital_tilisarao.R;
import com.example.hospital_tilisarao.RegistroActivity;
import com.example.hospital_tilisarao.request.ApiRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoTurnoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Medico>> medicos;
    private MutableLiveData<List<String>> lista ;
    private MutableLiveData<List<Turno>> turnos ;
    Turno nuevo = new Turno();






    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

        turnos = new MutableLiveData<>();


    }
    public LiveData<List<Turno>> getTurnos() {
        if (turnos == null)
        {
            turnos = new MutableLiveData<>();
        }
        return turnos;

    }

    public LiveData<List<Medico>> getMedicos() {
        if (medicos == null)
        {
            medicos = new MutableLiveData<>();
        }
        return medicos;
    }

    public LiveData<List<String>> getItems() {
        if (lista == null)
        {
            lista = new MutableLiveData<>();
        }
        return lista;
    }


    public void cargarMedico(){
        ArrayList<String> aux = new ArrayList<String>();
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        retrofit2.Call<List<Medico>> pag;
         pag = ApiRest.getMyApiClient().obtenerMedicos(token);
        pag.enqueue(new Callback<List<Medico>>() {
            @Override
            public void onResponse(Call<List<Medico>> call, Response<List<Medico>> response) {
                if(response.isSuccessful()){
                    medicos.postValue(response.body());
                    for (Medico m : response.body())
                    {
                        aux.add(m.getItem() + "");
                    }

                    lista.postValue(aux);
                }
            }
            @Override
            public void onFailure(Call<List<Medico>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void cargarTurnos(Turno turno){

        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        retrofit2.Call<List<Turno>> pag;
        pag = ApiRest.getMyApiClient().turnosDisponibles(token,turno);
        pag.enqueue(new Callback<List<Turno>>() {
            @Override
            public void onResponse(Call<List<Turno>> call, Response<List<Turno>> response) {
                ArrayList<Turno> turnosaux = new ArrayList<>();
                if(response.isSuccessful()){
                    Log.d("salida","respondio cargar turnos");
                    for (int i = 0; i < response.body().size(); i++) {
                        turnosaux.add(response.body().get(i));
                    }
                    turnos.postValue(turnosaux);
                }else{
                    Log.d("salida","respondio cargar turnos else " + response.message() + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Turno>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void nuevoTurno(Turno turno){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Turno> prop = ApiRest.getMyApiClient().altaTurno(token, turno);
        prop.enqueue(new Callback<Turno>() {
            @Override
            public void onResponse(Call<Turno> call, Response<Turno> response) {
                if(response.isSuccessful()){
                    nuevo = response.body();
                    Toast.makeText(context, "Turno Creado", Toast.LENGTH_LONG).show();
                }else {


                }
            }

            @Override
            public void onFailure(Call<Turno> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}