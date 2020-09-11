package kz.lib_mob_client.controller;

import kz.lib_mob_client.entity.AccessToken;
import kz.lib_mob_client.entity.UserCreationRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LibAuthApi {

    @POST("/user/register")
    public Call<ResponseBody> doRegistration(@Header("Content-Type") String token, @Body UserCreationRequest userCreationRequest);

    @POST("/oauth/token")
    public Call<AccessToken> login(@Query("grant_type") String grant_type, @Query("username") String user, @Query("password") String password);

    @POST("/oauth/token")
    public Call<AccessToken> refresh(@Query("grant_type") String grant_type, @Query("refresh_token") String refresh_token);

}
