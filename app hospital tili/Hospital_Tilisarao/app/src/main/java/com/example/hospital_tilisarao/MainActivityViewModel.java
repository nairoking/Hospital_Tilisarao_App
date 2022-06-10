package com.example.hospital_tilisarao;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.DnsResolver;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.Modelo.Usuario;
import com.example.hospital_tilisarao.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mensaje;
    private MutableLiveData<Paciente> paciente ;
    private MutableLiveData<String> tokenMD ;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        paciente =  new MutableLiveData<>();
        mensaje =  new MutableLiveData<>();
        tokenMD = new MutableLiveData<>();
    }

    public LiveData<String> getMensaje(){
        if(mensaje==null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }
    public MutableLiveData<String> getTokenMD() {
        return tokenMD;
    }

    public MutableLiveData<Paciente> getPropietario() {
        return paciente;
    }

    public void token(String token){

        retrofit2.Call<Paciente> callProp;
        callProp = ApiRest.getMyApiClient().obtenerUsuario("Bearer "+token);
        callProp.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(retrofit2.Call<Paciente> call, Response<Paciente> response) {
                if (response.isSuccessful()) {
                    Paciente p = response.body();
                    if (p != null) {
                        paciente.postValue(p);
                    }
                }else{
                    mensaje.setValue("Usuario y/o Contraseña incorrecto!!!");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Paciente> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }

    public void iniciarSesion(String email , String clave){
        Usuario user = new Usuario(email,clave);
        retrofit2.Call<String> callTok;
        callTok = ApiRest.getMyApiClient().login(user);

        callTok.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = context.getSharedPreferences("datos",0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body());
                    editor.commit();
                    tokenMD.postValue(response.body());

                }else{
                    Toast.makeText(context, "Ocurrio un error inesperado "+ response, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }



        });
    }

    public void inicioAutomatico() {
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        if(token!=null) {
            retrofit2.Call<Paciente> callProp;
             callProp = ApiRest.getMyApiClient().obtenerUsuario(token);
            callProp.enqueue(new Callback<Paciente>() {

                @Override
                public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                    if (response.isSuccessful()) {

                        Paciente p = response.body();

                        if (p != null) {

                            paciente.postValue(p);

                        } else {

                        }
                    }
                }

                @Override
                public void onFailure(Call<Paciente> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
