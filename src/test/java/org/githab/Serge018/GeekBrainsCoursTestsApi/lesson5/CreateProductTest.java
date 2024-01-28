package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.util.List;


public class CreateProductTest extends AbstractTestMiniMarket2
{
    private static SqlSession session;
    db.dao.ProductsMapper productsMapper;
    db.model.ProductsExample example;
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    static int id;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        session = getSession();
    }


    @BeforeEach
    void setUp()
    {
        product = new Product()
            .withTitle(faker.food().ingredient())
            .withCategoryTitle("Food")
            .withPrice((int) (Math.random() * 10000));

        productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        example = new db.model.ProductsExample();
    }


    @Test
    @DisplayName("Тест создания продукта")
    void createProductInFoodCategoryTest() throws IOException
    {
        Response<Product> response = productService.createProduct(product).execute();
        // Сохраняем идентификатор созданного продукта для его удаления по завершению тестов
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        // Проверка наличия в БД продукта с уникальным идентификатором созданного
        example.createCriteria().andIdEqualTo((long) id);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        assertThat(list.size(), CoreMatchers.is(1));
    }


    @SneakyThrows
    @AfterEach
    void tearDown()
    {
        // Удаляем созданный продукт
        productsMapper.deleteByPrimaryKey((long) id);
        session.commit();
    }
}
