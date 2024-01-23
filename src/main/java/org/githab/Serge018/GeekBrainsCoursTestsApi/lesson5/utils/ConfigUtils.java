package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils;

import lombok.experimental.UtilityClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@UtilityClass
public class ConfigUtils
{
    private final Properties prop = new Properties();
    private InputStream configFile;

    static
    {
        try
        {
            configFile = new FileInputStream("src/main/resources/my.properties");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public String getBaseUrl() throws IOException
    {
        prop.load(configFile);
        return prop.getProperty("url");
    }


    public int getProductIdToBeDeleted() throws IOException, NumberFormatException
    {
        prop.load(configFile);
        String productIdToBeRead = prop.getProperty("productIdToBeDeleted");
        int result = Integer.parseInt(productIdToBeRead);

        return result;
    }


    public int getProductIdToBeRead() throws IOException, NumberFormatException
    {
        prop.load(configFile);
        String productIdToBeRead = prop.getProperty("productIdToBeRead");
        int result = Integer.parseInt(productIdToBeRead);

        return result;
    }


    public int getProductIdToBeModify() throws IOException, NumberFormatException
    {
        prop.load(configFile);
        String productIdToModify = prop.getProperty("productIdToModify");
        int result = Integer.parseInt(productIdToModify);

        return result;
    }
}
