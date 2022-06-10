package com.example.inmobiliariamiguel.ui.contratos;

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
import com.example.inmobiliariamiguel.request.ApiClient;
import com.example.inmobiliariamiguel.request.ApiRest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Inmueble i;

    private Context context;

    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        contrato = new MutableLiveData<>();

        context = application.getApplicationContext();
    }


    public MutableLiveData<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        SharedPreferences sp = context.getSharedPreferences("datos", 0);
        String token = sp.getString("token", "-1");
        Call<Contrato> con = ApiRest.getMyApiClient().obtenerContratos(token, i.getIdInmueble());
        con.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {
                    contrato.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
}