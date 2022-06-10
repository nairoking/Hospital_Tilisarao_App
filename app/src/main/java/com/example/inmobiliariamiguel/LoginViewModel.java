package com.example.inmobiliariamiguel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariamiguel.modelo.Propietario;
import com.example.inmobiliariamiguel.modelo.Token;
import com.example.inmobiliariamiguel.modelo.Usuario;
import com.example.inmobiliariamiguel.request.ApiClient;
import com.example.inmobiliariamiguel.request.ApiRest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mensaje;
    private MutableLiveData<Propietario> propietario ;
    private MutableLiveData<String> tokenMD ;
    private MutableLiveData<Boolean> mensajeB ;

    private MutableLiveData<Boolean> llamar ;

    private boolean estado =false;
    private int contador=0;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        propietario =  new MutableLiveData<>();
        mensaje =  new MutableLiveData<>();
        llamar =  new MutableLiveData<>();
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
    public MutableLiveData<Boolean> getLlamar() {
        return llamar;
    }
    public MutableLiveData<Propietario> getPropietario() {
        return propietario;
    }
    public MutableLiveData<Boolean> getMensajeB() {
        return mensajeB;
    }
    public void token(String token){

        Call<Propietario> callProp = ApiRest.getMyApiClient().obtenerUsuario("Bearer "+token);
        callProp.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    Propietario p = response.body();
                    if (p != null) {
                        propietario.postValue(p);
                    }
                }else{
                    mensaje.setValue("Usuario y/o Contraseña incorrecto!!!");
                }
            }
            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void iniciarSesion(String email , String clave){
        Usuario user = new Usuario(email,clave);
        Call<String> callTok = ApiRest.getMyApiClient().login(user);

        callTok.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
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
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void inicioAutomatico() {
        SharedPreferences sp = context.getSharedPreferences("datos",0);
        String token = sp.getString("token","-1");
        if(token!=null) {
            Call<Propietario> callProp = ApiRest.getMyApiClient().obtenerUsuario(token);
            callProp.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {

                        Propietario p = response.body();

                        if (p != null) {

                            propietario.postValue(p);

                        } else {

                        }
                    }else {

                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, "Ocurrio un error inesperado"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


   /* private class UserLogin{
        private String user;
        private String pass;

        public UserLogin(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
    }
*/











    /*public void iniciarSesion(String usuario, String contraseña){
        ApiClient api = ApiClient.getApi();
        Propietario propietarioLogueado = api.login(usuario, contraseña);
        if(propietarioLogueado!=null){
            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);

        }else{
            mensaje.setValue("Usuario y/o Contraseña incorrecto!!!");

        }


    }*/
}
