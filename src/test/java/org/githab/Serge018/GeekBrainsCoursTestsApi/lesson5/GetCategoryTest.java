package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.CategoryService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.GetCategoryResponse;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;


public class GetCategoryTest
{
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() throws IOException
    {
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest()
    {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();
        GetCategoryResponse body = response.body();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(body.getId(), CoreMatchers.equalTo(1));
        assertThat(body.getTitle(), CoreMatchers.equalTo("Food"));
        body.getGetProductResponses().forEach(product -> assertThat(product.getCategoryTitle(), CoreMatchers.equalTo("Food")));

    }
}
