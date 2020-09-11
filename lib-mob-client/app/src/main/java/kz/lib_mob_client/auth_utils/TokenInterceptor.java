package kz.lib_mob_client.auth_utils;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private String authToken;

    public TokenInterceptor(String token){
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder;
        if (!TextUtils.isEmpty(authToken)) {
            builder = original.newBuilder()
                    .header("Authorization", authToken);
        } else {
            builder = original.newBuilder()
                    .header("Content-Type", "application/json");
        }

        Request request = builder.build();
        return chain.proceed(request);
    }
}
