package kz.lib_mob_client.controller;

import java.util.List;


import kz.lib_mob_client.entity.AllBook;
import kz.lib_mob_client.entity.News;
import kz.lib_mob_client.entity.RegulatoryDocumentation;
import kz.lib_mob_client.entity.SearchRequest;
import kz.lib_mob_client.entity.SearchRespond;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.entity.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LibApi {

    @POST("/user/userinfo")
    public Call<UserInfo> getUserInfo(@Header("Authorization") String token, @Query("username") String username);

    @POST("/user/register")
    public Call<ResponseBody> doRetister(@Header("Content-Type") String token, @Body UserCreationRequest userCreationRequest);

    @POST("regdoc/viewall")
    public Call<List<RegulatoryDocumentation>> getRegDocList(@Header("Authorization") String token, @Query("category") int category);

    @POST("allbook/viewbystatus")
    public Call<List<AllBook>> getAllBookByStatus(@Header ("Authorization") String token, @Query("status") int status);

    @GET("files/getfile")
    public Call<ResponseBody> getFile(@Header("Authorization") String token, @Query("filepath") String filepath, @Query("fileName") String fileName);

    @POST("search/searchallbyname")
    public Call<List<SearchRespond>> searchAllByName(@Header("Authorization") String token, @Query("name") String name);

    @POST("search/searchbycategory")
    public Call<List<SearchRespond>> searchByCategory(@Header("Authorization") String token, @Body SearchRequest searchRequest);

    @POST("news/listnews")
    public Call<List<News>> getNewsList(@Header("Authorization") String token);

    @POST("news/newstextbyid")
    public Call<ResponseBody> getNewsText(@Header("Authorization") String token, @Query("id") int id);


}
