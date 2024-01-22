package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api;

import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.GetProductResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService
{
    @POST("products")
    Call<GetProductResponse> createProduct(@Body GetProductResponse createGetProductResponseRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("products")
    Call<GetProductResponse> modifyProduct(@Body GetProductResponse modifyGetProductResponseRequest);

    @GET("products/{id}")
    Call<GetProductResponse> getProductById(@Path("id") int id);

    @GET("products")
    Call<ResponseBody> getProducts();
}
