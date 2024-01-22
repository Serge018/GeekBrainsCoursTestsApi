package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils;

import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
//import okhttp3.logging.HttpLoggingInterceptor;
//import okhttp3.OkHttpClient;
//import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import java.io.IOException;


@UtilityClass
public class RetrofitUtils
{
//    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//    LoggingInterceptor logging2 = new LoggingInterceptor();
//    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public Retrofit getRetrofit() throws IOException
    {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ConfigUtils.getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit;
    }
}