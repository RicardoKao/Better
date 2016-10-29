package aiinno.com.better.service;

import aiinno.com.better.model.Ret;
import aiinno.com.better.model.WechatUser;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import aiinno.com.better.service.BaseInterceptor.AuthInterceptor;

/**
 * Created by lbk on 2016/10/29.
 */
public class SignService {
    public static final String API_URL = "https://better.aiinno.com";

    public static Ret WechatAuth(String wechatid, String apiurl)throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PublicAPI pubapi = retrofit.create(PublicAPI.class);
        Ret authinfo = pubapi.WechatAuth(wechatid).execute().body();
        return authinfo;
    }
    public static Ret QiNiuAuth(String pname,String token) throws IOException{
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
        PublicAPI pubapi = retrofit.create(PublicAPI.class);
        Ret res = pubapi.QiNiuAuth("text.png").execute().body();
        return res;
    }

    public static void main(String... args) throws IOException, InterruptedException {
        Ret authinfo = WechatAuth("xiaotingv6",API_URL);
        System.out.println(authinfo.data);
        Ret qiniuauthinfo = QiNiuAuth("test2.png",authinfo.data);
        System.out.println(qiniuauthinfo.data);

    }
}
