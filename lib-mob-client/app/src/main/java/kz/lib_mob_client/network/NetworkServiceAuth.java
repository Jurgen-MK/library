package kz.lib_mob_client.network;

import kz.lib_mob_client.controller.LibAuthApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkServiceAuth {
    private static NetworkServiceAuth mInstance;

    private static final String BASE_URL_REG = "http://192.168.1.100:9000";
    private Retrofit mRetrofit;
    private String accessToken;

    public NetworkServiceAuth() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_REG)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NetworkServiceAuth getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceAuth();
        }
        return mInstance;
    }

    public LibAuthApi getJSONAuthApi() {
        return mRetrofit.create(LibAuthApi.class);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
