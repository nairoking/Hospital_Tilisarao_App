package com.example.inmobiliariamiguel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inmobiliariamiguel.modelo.Propietario;
import com.example.inmobiliariamiguel.modelo.Token;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText etUser, etPass;
    private LoginViewModel lv;
    private SensorManager sensorManager;
    private LeeSensor leeSensor;
    private Llamada llamada;
    private int bandera = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);}

        lv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        obtenerSensores();
        inicializarVista();
        lv.inicioAutomatico();
        lv.getTokenMD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                lv.token(token);
            }
        });
        lv.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
        lv.getPropietario().observe(this, new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
    @Override
    protected void onResume() {
        super.onResume();
        obtenerSensores();
        llamada = new Llamada();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(leeSensor);
    }

    private void inicializarVista() {
        login = findViewById(R.id.btIngresar);
        etUser =findViewById(R.id.etUsuarioLogin);
        etPass =findViewById(R.id.etContraseñaLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.iniciarSesion(etUser.getText().toString(),etPass.getText().toString());
            }
        });
    }
    public void obtenerSensores(){

        leeSensor = new LeeSensor();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        //List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if(listaSensores.size()>0){
            sensorManager.registerListener(leeSensor,listaSensores.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
    }
    public class Llamada extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
    private class LeeSensor implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //tvlista.setText("x="+sensorEvent.values[0]+" y="+sensorEvent.values[1]+" z="+sensorEvent.values[2]);
            //tvlista.setText("x="+sensorEvent.values[0]+"");
            if (bandera == 0 && sensorEvent.values[0] >= 20 || sensorEvent.values[0] <= -20) {
                Uri tel = Uri.parse("tel:2664000000");
                startActivity(new Intent(Intent.ACTION_CALL, tel));
                bandera = 1;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    }