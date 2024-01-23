package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils;

import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.io.IOException;


@UtilityClass
public class RetrofitUtils
{
    public Retrofit getRetrofit() throws IOException
    {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ConfigUtils.getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit;
    }
}