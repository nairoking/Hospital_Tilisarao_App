package com.example.inmobiliariamiguel.request;

import com.example.inmobiliariamiguel.modelo.Contrato;
import com.example.inmobiliariamiguel.modelo.Inmueble;
import com.example.inmobiliariamiguel.modelo.Inquilino;
import com.example.inmobiliariamiguel.modelo.Pago;
import com.example.inmobiliariamiguel.modelo.Propietario;
import com.example.inmobiliariamiguel.modelo.Token;
import com.example.inmobiliariamiguel.modelo.Usuario;
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
    public static final String UrlBase="http://192.168.1.100:45456/api/";
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


        //propietarios
        @PUT("propietarios/actualizar")
        Call<Propietario> actualizarPropietario(@Header("Authorization") String token, @Body Propietario propietario);

        @GET("propietarios/")
        Call<Propietario> obtenerUsuario(@Header("Authorization") String token);

        @POST("propietarios/login")
        Call<String> login (@Body Usuario user);

        //inmueble
        @GET("inmuebles/obtener")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

        @GET("inmuebles/contrato")
        Call<List<Inmueble>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

        @GET("inquilinos/{id}")
        Call<Inquilino> obtenerInquilino(@Header("Authorization") String token,@Path("id") int idInmueble);

        @FormUrlEncoded
        @PUT("inmuebles/{id}")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token,@Path("id") int idInmueble,@Field("estado") int estado);

        //contrato
        @GET("contrato/{id}")
        Call<Contrato> obtenerContratos(@Header("Authorization") String token,@Path("id") int idInmueble);

        @GET("pago/{id}")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token,@Path("id") int idContrato);

    }





}

