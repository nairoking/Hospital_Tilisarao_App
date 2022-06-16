package com.example.hospital_tilisarao.ui.historial;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital_tilisarao.Modelo.HistorialMedico;

public class HistorialDetalleViewModel extends AndroidViewModel {

    private MutableLiveData<HistorialMedico> historial;
    private Context context;
    HistorialMedico i;

    public HistorialDetalleViewModel(@NonNull Application application) {
        super(application);
        historial = new MutableLiveData<>();
        context = application.getApplicationContext();
    }
    public LiveData<HistorialMedico> getHistorial() {
        if (historial == null) {
            historial = new MutableLiveData<>();
        }
        return historial;
    }
    public void cargarHistorial(Bundle bundle) {
        i = (HistorialMedico) bundle.getSerializable("historial");
        this.historial.setValue(i);
    }

    // TODO: Implement the ViewModel
}