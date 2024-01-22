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

}
