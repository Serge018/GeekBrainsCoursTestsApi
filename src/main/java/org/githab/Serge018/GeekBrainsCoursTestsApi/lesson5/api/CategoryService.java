package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.GetCategoryResponse;

public interface CategoryService
{
    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}
