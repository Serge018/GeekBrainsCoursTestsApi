package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import static org.hamcrest.MatcherAssert.assertThat;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;

import com.github.javafaker.Faker;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;


public class ModifyProductTest extends AbstractTestMiniMarket2
{
    private static SqlSession session;
    db.dao.ProductsMapper productsMapper;
    db.model.ProductsExample example;
    static ProductService productService;
    static int productIdToBeModify;
    static Faker faker = new Faker();
    static String categoryTitle = "Electronic";
    static long categoryId = 2;

    // Поля данных для обновления продукта
    static String newTitle;
    static int newPrice;
    // Поля для исходные данных продукта
    static String oldTitle;
    static int oldPrice;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        productIdToBeModify = ConfigUtils.getProductIdToBeModify();
        session = getSession();
    }


    @BeforeEach
    void setUp()
    {
        // Данные для обновления
        newTitle = faker.commerce().productName();
        newPrice = (int) (Math.random() * 100000000);

        // Производим обращение к БД, сохраняем параметры продукта до его обновления
        productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        example = new db.model.ProductsExample();
        example.createCriteria().andIdEqualTo((long) productIdToBeModify);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        db.model.Products product = list.get(0);
        oldTitle = product.getTitle();
        oldPrice = product.getPrice();
    }


    @Test
    @DisplayName("Тест обновления данных продукта")
    void updateProductData() throws IOException
    {
        Product productToBeModify = new Product()
                .withId(productIdToBeModify)
                .withCategoryTitle(categoryTitle)
                .withTitle(newTitle)
                .withPrice(newPrice);

        Response<Product> response = productService.modifyProduct(productToBeModify).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        db.dao.ProductsMapper productsMapper;
        db.model.ProductsExample example;

        productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        example = new db.model.ProductsExample();
        example.createCriteria().andIdEqualTo((long) productIdToBeModify);
        List<db.model.Products> list = productsMapper.selectByExample(example);
        db.model.Products product = list.get(0);

        assertThat(product.getTitle(), Matchers.equalTo(newTitle));
        assertThat(product.getPrice(), Matchers.equalTo(newPrice));
    }


    @SneakyThrows
    @AfterEach
    void tearDown()
    {
        // Возвращаем продукту изначальные параметры
        db.model.Products product = new db.model.Products();
        product.setId((long) productIdToBeModify);
        product.setTitle(oldTitle);
        product.setCategory_id(categoryId);
        product.setPrice(oldPrice);
        productsMapper.updateByPrimaryKey(product);
        session.commit();
    }
}
