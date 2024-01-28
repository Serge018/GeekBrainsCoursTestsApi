package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;


public class AbstractTestMiniMarket2
{
    private static SqlSession session = null;
    private static String resource = "mybatis-config.xml";
    private static InputStream inputStream;
    private static SqlSessionFactory sqlSessionFactory;


    @BeforeAll
    static void setSqlSession() throws IOException
    {
        inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }


    static SqlSession getSession()
    {
        return session;
    }


    @AfterAll
    static void closeSqlSession()
    {
        if (session != null)
        {
            session.close();
        }
    }
}
