package com.example.hospital_tilisarao.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Paciente> paciente ;
    private MutableLiveData<Boolean> guardar ;
    private MutableLiveData<Boolean> editar ;

    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        paciente =  new MutableLiveData<>();

        guardar =  new MutableLiveData<>();
        editar =  new MutableLiveData<>();
        context = application.getApplicationContext();
    }


    public MutableLiveData<Paciente> getPropietario() {
        if(paciente == null){
            paciente =  new MutableLiveData<>();
        }
        return paciente;
    }

    public MutableLiveData<Boolean> getGuardar() {
        if(guardar == null){
            guardar =  new MutableLiveData<>();
        }
        return guardar;
    }

    public MutableLiveData<Boolean> getEditar() {
        if(editar == null){
            editar =  new MutableLiveData<>();
        }
        return editar;
    }
    public void iniciar(){

        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Paciente> prop = ApiRest.getMyApiClient().obtenerUsuario(token);
        prop.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if(response.isSuccessful()){
                    paciente.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void cambiar(Paciente p){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Paciente> prop = ApiRest.getMyApiClient().actualizarPropietario(token,p);
        prop.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if(response.isSuccessful()){
                    paciente.postValue(response.body());
                    Toast.makeText(context, "Se Modifico con exito", Toast.LENGTH_SHORT).show();
                }else {

                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}