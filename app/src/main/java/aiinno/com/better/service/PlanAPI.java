package aiinno.com.better.service;

import java.util.ArrayList;

import aiinno.com.better.model.Ret;
import aiinno.com.better.model.Plan;
import aiinno.com.better.service.JsonConverters.Json;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;
import okhttp3.ResponseBody;

/**
 * Created by lbk on 2016/10/30.
 */
public interface PlanAPI {

    @FormUrlEncoded
    @POST("/plans/addPlan/") @Json
    Call<Ret> AddPlan(@Field("name") String name,
                      @Field("title") String title,
                      @Field("declear") String yourdeclear,
                      @Field("rules") ArrayList<String> rules,
                      @Field("days") String days,
                      @Field("complated_days") String complated_days,
                      @Field("fee") String fee,
                      @Field("img") String img,
                      @Field("start") String start,
                      @Field("tags") ArrayList<String> tags);

    @GET("/plans/") @Json
    Call<ArrayList<Plan>> GetPlan();
}
