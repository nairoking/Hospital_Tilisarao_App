package com.example.hospital_tilisarao.ui.historial;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.Modelo.HistorialMedico;
import com.example.hospital_tilisarao.request.ApiRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialViewModel extends AndroidViewModel {

    private MutableLiveData<List<HistorialMedico>> historial;
    private Context context;


    public HistorialViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public LiveData<List<HistorialMedico>> getHistorial(){
        if (historial== null){
            historial=new MutableLiveData<>();
        }
        return historial;
    }

    public void cargarHistorial(){
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        Call<List<HistorialMedico>> inm = ApiRest.getMyApiClient().obtenerHistorial(token);
        inm.enqueue(new Callback<List<HistorialMedico>>() {
            @Override
            public void onResponse(Call<List<HistorialMedico>> call, Response<List<HistorialMedico>> response) {
                if(response.isSuccessful()){
                    historial.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<HistorialMedico>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}