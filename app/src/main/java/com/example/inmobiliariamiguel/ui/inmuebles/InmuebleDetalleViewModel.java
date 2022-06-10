package com.example.inmobiliariamiguel.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private Context context;
    Inmueble i;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        inmueble = new MutableLiveData<>();
        context = application.getApplicationContext();
    }
    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void cargarInmueble(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        this.inmueble.setValue(i);
    }

    public void guardarCambios(boolean cheak){

        if(cheak == true){
            i.setEstado("si");
        }else{
            i.setEstado("no");
        }

        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Inmueble> inm = ApiRest.getMyApiClient().actualizarInmueble(token, i.getIdInmueble(), i.isEstado());
        inm.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}