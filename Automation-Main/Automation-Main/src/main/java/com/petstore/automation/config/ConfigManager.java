package com.petstore.automation.config;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter.Config;
import java.util.Properties;

public class ConfigManager{
    private static final Properties properties=new Properties();
    static {
        try(InputStream input =ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")){
            if (input==null){
                throw new RuntimeException("unable to find config.properties");
            }
            properties.load(input);

        }
        catch(IOException e){
            throw new RuntimeException("Failed to load configuration properties",e);
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
    public static int getIntProperty(String key,int defaultValue){
        String Value=properties.getProperty(key);
        return value!=null? Integer.parseInt(Value) :defaultValue;

    }
    public static boolean getBooleanProperty(String key,boolean defaultValue){
        String value=properties.getProperty(key);
        return value!=null ?Boolean.parseBoolean(value) : defaultValue;
    }
}