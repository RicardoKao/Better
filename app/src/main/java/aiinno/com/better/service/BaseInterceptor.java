package aiinno.com.better.service;

import java.io.IOException;

import aiinno.com.better.model.User;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import aiinno.com.better.service.JsonConverters.Json;

/**
 * Created by lbk on 2016/10/29.
 */
public class BaseInterceptor{

    public interface Service {
        @GET("/")
        Call<ResponseBody> exampleJson();

        @GET("/") @Json
        Call<User> exampleJson2();
    }
    static public class AuthInterceptor implements Interceptor {
        String token;
        public void setToken(String token) {
            this.token = token;
        }
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl url = original.url().newBuilder()
                    .build();
            if (token == null) {
                token = "";
            }
            Request request = original.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Authorization", "JWT " + token)
                    .method(original.method(), original.body())
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    }
    public static void main(String... args) throws IOException, InterruptedException {
        MockWebServer server = new MockWebServer();
        server.start();
        server.enqueue(new MockResponse().setBody("{\"nickName\": \"Jason\"}"));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(okHttpClient)
                .build();
        Service pop = retrofit.create(Service.class);
        Response<ResponseBody> res = pop.exampleJson().execute();
        RecordedRequest chunkedRequest = server.takeRequest();
        System.out.println(
                "@Chunked @Body Transfer-Encoding: " + chunkedRequest.getHeader("Transfer-Encoding"));
    }
}
