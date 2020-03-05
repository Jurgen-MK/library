package kz.lib_mob_client.controller;

import kz.lib_mob_client.entity.UserCreationRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LibAuthApi {

    @POST("/user/register")
    public Call<ResponseBody> doRegistration(@Header("Content-Type") String token, @Body UserCreationRequest userCreationRequest);

}
