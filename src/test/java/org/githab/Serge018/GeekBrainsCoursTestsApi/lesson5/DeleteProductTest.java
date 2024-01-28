package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.CoreMatchers;
import okhttp3.ResponseBody;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;


public class DeleteProductTest extends AbstractTestMiniMarket2
{
    static ProductService productService;
    static int productIdToBeDeleted;
    private static SqlSession session;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        productIdToBeDeleted = ConfigUtils.getProductIdToBeDeleted();
        session = getSession();
    }


    @Test
    @DisplayName("Удаление продукта по его идентификатору")
    void deleteProductById() throws IOException
    {
        Response<ResponseBody> response = productService.deleteProduct(productIdToBeDeleted).execute();
        // Проверка, что запрос выполнился корректно
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.ProductsExample example = new db.model.ProductsExample();
        example.createCriteria().andIdEqualTo((long) productIdToBeDeleted);

        // Проверка, что в БД отстутствует продукт с идентификатором удалённого
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assertThat(list.size(), CoreMatchers.is(0));
    }
}
