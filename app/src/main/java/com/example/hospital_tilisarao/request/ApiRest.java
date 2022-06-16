package com.example.hospital_tilisarao.request;

import android.support.v4.media.MediaDescriptionCompat;

import com.example.hospital_tilisarao.Modelo.HistorialMedico;
import com.example.hospital_tilisarao.Modelo.Medico;
import com.example.hospital_tilisarao.Modelo.Paciente;
import com.example.hospital_tilisarao.Modelo.Turno;
import com.example.hospital_tilisarao.Modelo.TurnoView;
import com.example.hospital_tilisarao.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiRest {
    public static final String UrlBase="http://192.168.1.100:45455/api/";
    private static PostInterface postInterface;


    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PostInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UrlBase)
                .client(getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }



    public interface PostInterface{


        @GET("paciente")
        Call<Paciente> obtenerUsuario(@Header("Authorization") String token);

        @GET("medico/getall")
        Call<List<Medico>> obtenerMedicos(@Header("Authorization") String token);

        @POST("paciente/login")
        Call<String> login (@Body Usuario user);

        @PUT("paciente/actualizar")
        Call<Paciente> actualizarPaciente(@Header("Authorization") String token, @Body Paciente paciente);

        @POST("paciente")
        Call<Paciente>altaPaciente(@Body Paciente paciente);

        @GET("turno/turnopendiente")
        Call<List<Turno>> obtenerTurnosPendientes(@Header("Authorization") String token);

        @POST("turno")
        Call<Turno> altaTurno (@Header("Authorization") String token, @Body Turno turno);

        @POST("turno/turnoDisponible")
        Call<List<Turno>> turnosDisponibles(@Header("Authorization") String token,@Body Turno turno);

        @GET("itemhistorial")
        Call<List<HistorialMedico>> obtenerHistorial(@Header("Authorization") String token);

    }

}
