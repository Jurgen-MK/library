package kz.lib_mob_client.auth_utils;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.AccessToken;
import kz.lib_mob_client.network.ServiceAuth;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class TokenAuthenticator implements Authenticator {

    private TokenManager tokenManager;
    private static TokenAuthenticator INSTANCE;

    private TokenAuthenticator(TokenManager tokenManager){
        this.tokenManager = tokenManager;
    }

    public static synchronized TokenAuthenticator getInstance(TokenManager tokenManager){
        if (INSTANCE == null){
            INSTANCE = new TokenAuthenticator(tokenManager);
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {

        if (responseCount(response) >= 2){
            return null;
        }

        AccessToken token = tokenManager.getToken();

        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class);
        Call<AccessToken> call = serviceApi.refresh("refresh_token", token.getRefresh_token());

        retrofit2.Response<AccessToken> res = call.execute();
        if (res.isSuccessful()){
            AccessToken newAccessToken = res.body();
            tokenManager.saveToken(newAccessToken);
            Log.i("REFRESH_ACCESS_TOKEN", newAccessToken.getAccess_token());
            return  response.request().newBuilder().header("Authorization", "Bearer " + newAccessToken.getAccess_token()).build();
        } else {
            return null;
        }
    }

    private int responseCount(Response response){
        int result = 1;
        while ((response = response.priorResponse()) != null){
            result++;
        }
        return result;
    }
}
