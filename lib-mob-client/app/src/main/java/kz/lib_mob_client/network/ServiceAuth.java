package kz.lib_mob_client.network;

import android.text.TextUtils;
import android.util.Log;

import kz.lib_mob_client.auth_utils.TokenAuthenticator;
import kz.lib_mob_client.auth_utils.TokenInterceptor;
import kz.lib_mob_client.auth_utils.TokenManager;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceAuth {

    public static final String AUTH_API_BASE_URL = "http://192.168.0.100:9000";
    public static final String RES_API_BASE_URL = "http://192.168.0.100:9100";
    public final static String CLIENT_ID = "clientId";
    public final static String CLIENT_SECRET = "secret";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static OkHttpClient.Builder httpClientReg = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor htmlloginterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static Retrofit.Builder authBuilder =
            new Retrofit.Builder()
                    .baseUrl(AUTH_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit.Builder resBuilder =
            new Retrofit.Builder()
                    .baseUrl(RES_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create());


    private static Retrofit authRetrofit = authBuilder.build();
    private static Retrofit resRetrofit = resBuilder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, TokenManager tokenManager) {
        if (tokenManager == null) {
            String basicAuthToken = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
            return createServiceBasicAuth(serviceClass, basicAuthToken);
        }
        return createServiceResource(serviceClass, tokenManager);
    }

    public static <S> S createServiceBasicAuth(
            Class<S> serviceClass, final String basicAuthToken) {
        if (!TextUtils.isEmpty(basicAuthToken)) {
            TokenInterceptor interceptor =
                    new TokenInterceptor(basicAuthToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                authBuilder.client(httpClient.build());
                authRetrofit = authBuilder.build();
            }
        }

        return authRetrofit.create(serviceClass);
    }

    public static <S> S createServiceResource(
            Class<S> serviceClass, TokenManager tokenManager) {
        if (!TextUtils.isEmpty(tokenManager.getToken().getAccess_token())) {
            TokenInterceptor interceptor =
                    new TokenInterceptor("Bearer " + tokenManager.getToken().getAccess_token());
            Log.i("ACCESS_TOKEN", tokenManager.getToken().getAccess_token());
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                httpClient.addInterceptor(htmlloginterceptor);
                resBuilder.client(httpClient.authenticator(TokenAuthenticator.getInstance(tokenManager)).build());
                resRetrofit = resBuilder.build();
            }
        }

        return resRetrofit.create(serviceClass);
    }

    public static <S> S createServiceRegistration(Class<S> serviceClass) {
        TokenInterceptor interceptor =
                new TokenInterceptor(null);
        if (!httpClientReg.interceptors().contains(interceptor)) {
            httpClientReg.addInterceptor(interceptor);

            authBuilder.client(httpClientReg.build());
            authRetrofit = authBuilder.build();
        }

        return authRetrofit.create(serviceClass);
    }
}
