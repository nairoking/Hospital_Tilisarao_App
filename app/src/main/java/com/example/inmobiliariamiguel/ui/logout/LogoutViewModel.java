package com.example.inmobiliariamiguel.ui.logout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.inmobiliariamiguel.R;

public class LogoutViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<AlertDialog.Builder> mutableLiveData;

    public LogoutViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    // TODO: Implement the ViewModel
    public LiveData<AlertDialog.Builder> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }


    public void mostrarDialogo(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle("Titulo")
                .setMessage("Desea cerrar la app")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);

                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Navigation.findNavController((Activity) context, R.id.nav_host_fragment_content_main).navigate(R.id.nav_inicio);

            }
        });
        mutableLiveData.setValue(alert);
    }

}