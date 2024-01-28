package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import static org.hamcrest.MatcherAssert.assertThat;

import okhttp3.ResponseBody;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;


public class GetProductTest
{
    static ProductService productService;
    static int productIdToBeRead;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        productIdToBeRead = ConfigUtils.getProductIdToBeRead();
    }


    @Test
    @DisplayName("Тест получения продукта по его идентификатору")
    void getProductById() throws IOException
    {
        Response<Product> response = productService.getProductById(productIdToBeRead).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @DisplayName("Тест получения всех продуктв")
    void getProducts() throws IOException
    {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
