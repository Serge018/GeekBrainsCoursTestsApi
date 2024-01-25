package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api;

import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService
{
    @POST("products")
    Call<Product> createProduct(@Body Product createGetProductResponseRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyGetProductResponseRequest);

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @GET("products")
    Call<ResponseBody> getProducts();
}
