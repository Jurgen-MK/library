package kz.lib_mob_client.network;

import kz.lib_mob_client.controller.LibApi;
import kz.lib_mob_client.entity.UserInfo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkServiceResource {

    private static NetworkServiceResource mInstance;

    private static final String BASE_URL = "http://10.64.2.156:9100";
    private Retrofit mRetrofit;
    private String accessToken;
    private UserInfo userInfo;

    private NetworkServiceResource() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NetworkServiceResource getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceResource();
        }
        return mInstance;
    }

    public LibApi getJSONApi() {
        return mRetrofit.create(LibApi.class);
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

}
