package com.example.inmobiliariamiguel.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.request.ApiClient;
import com.example.inmobiliariamiguel.request.ApiRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> inmuebles;
    private Context context;

    public ContratoViewModel(Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public LiveData<List<Inmueble>> getInmuebles(){
        if (inmuebles== null){
            inmuebles=new MutableLiveData<>();
        }
        return inmuebles;
    }

    public void cargarInmueblesAlquilados(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");

        Call<List<Inmueble>> inm = ApiRest.getMyApiClient().obtenerInmueblesAlquilados(token);
        inm.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){

                    inmuebles.postValue(response.body());

                }else{

                }

            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // TODO: Implement the ViewModel
}