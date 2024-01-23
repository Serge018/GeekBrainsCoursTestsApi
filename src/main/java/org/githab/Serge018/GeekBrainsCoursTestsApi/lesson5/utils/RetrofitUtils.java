package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.io.IOException;


import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

@UtilityClass
public class RetrofitUtils
{
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    // Собственный перехвадчик
    // LoggingInterceptor logging = new LoggingInterceptor();

    public Retrofit getRetrofit() throws IOException
    {
        // Ставим уровень логирования, чтобы логировались запросы и ответы
        logging.setLevel(BODY);
        // Добавляем перехватчик в okhttp-клиент
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ConfigUtils.getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            // Добавляем okhttp-клиент к retrofit-клиенту
            .client(httpClient.build())
            .build();

        return retrofit;
    }
}