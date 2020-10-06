package kz.lib_mob_client.controller;

import java.util.List;

import kz.lib_mob_client.entity.AccessToken;
import kz.lib_mob_client.entity.AllBook;
import kz.lib_mob_client.entity.ExhibitionRespond;
import kz.lib_mob_client.entity.News;
import kz.lib_mob_client.entity.RegulatoryDocumentation;
import kz.lib_mob_client.entity.ReportRequest;
import kz.lib_mob_client.entity.SearchRequest;
import kz.lib_mob_client.entity.SearchRespond;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.entity.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {

    @POST("/oauth/token")
    public Call<AccessToken> login(@Query("grant_type") String grant_type, @Query("username") String user, @Query("password") String password);

    @POST("/oauth/token")
    public Call<AccessToken> refresh(@Query("grant_type") String grant_type, @Query("refresh_token") String refresh_token);

    @POST("/user/register")
    public Call<ResponseBody> doRegistration(@Body UserCreationRequest userCreationRequest);

    @POST("/user/userinfo")
    public Call<UserInfo> getUserInfo(@Query("username") String username);

    @POST("regdoc/viewall")
    public Call<List<RegulatoryDocumentation>> getRegDocList(@Query("category") int category);

    @POST("allbook/viewbystatus")
    public Call<List<AllBook>> getAllBookByStatus(@Query("status") int status);

    @GET("files/getfile")
    public Call<ResponseBody> getFile(@Query("filepath") String filepath, @Query("fileName") String fileName);

    @POST("search/searchallbyname")
    public Call<List<SearchRespond>> searchAllByName(@Query("name") String name);

    @POST("search/searchbycategory")
    public Call<List<SearchRespond>> searchByCategory(@Body SearchRequest searchRequest);

    @POST("/news/listnews")
    public Call<List<News>> getNews();

    @POST("news/newstextbyid")
    public Call<ResponseBody> getNewsText(@Query("id") int id);

    @POST("report/get")
    public Call<ResponseBody> getReport(@Body ReportRequest reportRequest);

    @GET("exhibition/getall")
    public Call<List<ExhibitionRespond>> getAllExhibitions();
}
