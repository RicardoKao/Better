package aiinno.com.better.service;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import aiinno.com.better.model.Ret;
import aiinno.com.better.model.Plan;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import aiinno.com.better.service.BaseInterceptor.AuthInterceptor;
import aiinno.com.better.service.SignService;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lbk on 2016/10/30.
 */
public class PlanService {
    public static final String API_URL = "https://better.aiinno.com";

    public static Ret AddPlan(Plan plan, String token) throws IOException{
        AuthInterceptor authi = new AuthInterceptor();
        authi.setToken(token);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(authi)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(okHttpClient)
                .build();
        PlanAPI pubapi = retrofit.create(PlanAPI.class);
        Ret res = pubapi.AddPlan(
                plan.getName(),
                plan.getTitle(),
                plan.getYourdeclear(),
                plan.getRules(),
                String.valueOf(plan.getDays()),
                String.valueOf(plan.getComplated_days()),
                String.valueOf(plan.getFee()),
                plan.getImg(),
                plan.getStart().toString(),
                plan.getTags()
        ).execute().body();
        return res;
    }
/*
    public void GetPlan() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlanAPI serviceApi = retrofit.create(PlanAPI.class);
        serviceApi.downloadPicFromNet()
                .subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody arg0) {
                        if(true) {//保存图片成功
                            Bitmap bitmap = BitmapFactory.decodeFile(getExternalFilesDir(null) + File.separator + "baidu.png");
                            return bitmap;//返回一个bitmap对象
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(new Subscriber<Bitmap>() {
                    ProgressDialog dialog = new ProgressDialog();
                    @Override
                    public void onStart() {
                        dialog.show();
                        super.onStart();
                    }
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }
                    @Override
                    public void onError(Throwable arg0) {
                        Log.d(TAG, "onError ===== " + arg0.toString());
                    }
                    @Override
                    public void onNext(Bitmap arg0) {
                        imageIv.setImageBitmap(arg0);
                    }
                });
    }
    */

    public static void main(String... args) throws IOException{
        /*
        SignService s = new SignService();
        Ret authinfo = s.WechatAuth("xiaotingv6",API_URL);
        System.out.println(authinfo.data);
        Plan plan = new Plan();
        plan.setName("Great Plan");
        plan.setTitle("hello world!");
        plan.setYourdeclear("we will change the world!");
        ArrayList<String> rules = new ArrayList<>();
        rules.add(0,"good good study,day day up!");
        rules.add(1,"never give up!");
        plan.setRules(rules);
        plan.setDays(10);
        plan.setComplated_days(0);
        plan.setFee(50);
        plan.setImg("studytest.png");
        plan.setStart("nowdays");
        ArrayList<String> tags = new ArrayList<>();
        tags.add(0,"great");
        tags.add(1,"hello");
        plan.setTags(tags);
        Ret addplaninfo = AddPlan(plan,authinfo.data);
        System.out.println(addplaninfo.data);
        */
        SignService s = new SignService();
        Ret authinfo = s.WechatAuth("xiaotingv6",API_URL);
        System.out.println(authinfo.data);
        //ArrayList<Plan> plans = GetPlan(authinfo.data);
        //System.out.println(plans.get(0).getName());
    }
}
