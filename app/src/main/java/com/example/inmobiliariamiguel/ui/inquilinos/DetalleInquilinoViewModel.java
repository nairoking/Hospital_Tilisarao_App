package com.example.inmobiliariamiguel.ui.inquilinos;

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

import com.example.inmobiliariamiguel.modelo.Contrato;
import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.modelo.Inquilino;
import com.example.inmobiliariamiguel.request.ApiClient;
import com.example.inmobiliariamiguel.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inqui;
    private Inmueble i;
    private Context context;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        inqui = new MutableLiveData<>();

        context = application.getApplicationContext();

    }

    public LiveData<Inquilino> getInquilino() {
        if (inqui == null) {
            inqui = new MutableLiveData<>();
        }
        return inqui;
    }

    public void cargarInquilino(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inquilino");
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<Inquilino> inq = ApiRest.getMyApiClient().obtenerInquilino(token,i.getIdInmueble());
        inq.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()){
                    inqui.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}