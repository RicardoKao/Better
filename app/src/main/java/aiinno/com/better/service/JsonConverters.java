/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aiinno.com.better.service;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Both the Gson converter and the Simple Framework converter accept all types. Because of this,
 * you cannot use both in a single service by default. In order to work around this, we can create
 * an @Json and @Xml annotation to declare which serialization format each endpoint should use and
 * then write our own Converter.Factory which delegates to either the Gson or Simple Framework
 * converter.
 */
public final class JsonConverters {
  @Retention(RUNTIME)
  @interface Json {
  }


  static class QualifiedTypeConverterFactory extends Converter.Factory {
    private final Converter.Factory jsonFactory;

    QualifiedTypeConverterFactory(Converter.Factory jsonFactory) {
      this.jsonFactory = jsonFactory;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
        Retrofit retrofit) {
      for (Annotation annotation : annotations) {
        if (annotation instanceof Json) {
          return jsonFactory.responseBodyConverter(type, annotations, retrofit);
        }
      }
      return null;
    }

    @Override public Converter<?, RequestBody> requestBodyConverter(Type type,
        Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
      for (Annotation annotation : parameterAnnotations) {
        if (annotation instanceof Json) {
          return jsonFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations,
              retrofit);
        }
      }
      return null;
    }
  }

  static class User {
    public String name;
  }

  interface Service {
    @GET("/") @Json
    Call<User> exampleJson();
  }

  public static void main(String... args) throws IOException {
    MockWebServer server = new MockWebServer();
    server.start();
    server.enqueue(new MockResponse().setBody("{\"name\": \"Jason\"}"));

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addConverterFactory(new QualifiedTypeConverterFactory(
            GsonConverterFactory.create()))
        .build();
    Service service = retrofit.create(Service.class);

    User user1 = service.exampleJson().execute().body();
    System.out.println("User 1: " + user1.name);
    server.shutdown();
  }
}
