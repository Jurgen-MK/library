package kz.lib_mob_client.controller;

import java.util.List;


import kz.lib_mob_client.entity.RegulatoryDocumentation;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.entity.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LibApi {

    @POST("/user/userinfo")
    public Call<UserInfo> getUserInfo(@Header("Authorization") String token, @Query("username") String username);

    @POST("/user/register")
    public Call<ResponseBody> doRetister(@Header("Content-Type") String token, @Body UserCreationRequest userCreationRequest);

    @POST("regdoc/viewall")
    public Call<List<RegulatoryDocumentation>> getRegDocList(@Header("Authorization") String token);

}
