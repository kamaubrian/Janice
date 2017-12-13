package com.brian.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotCredentials {
    InputStream inputStream;
    Properties properties = new Properties();
    private File filename = new File("config.properties");

    public String getUsername() throws IOException{
        String username ="";
        try{
            inputStream = new FileInputStream(filename);
            properties.load(inputStream);
            username = properties.getProperty("BOT_USERNAME");
        }catch(Exception ex){
            ex.printStackTrace();

        }finally {
            inputStream.close();
        }
        return username;
    }
    public String getToken() throws IOException{
        String token ="";
        try{
            inputStream = new FileInputStream(filename);
            properties.load(inputStream);
            token = properties.getProperty("BOT_TOKEN");

        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            inputStream.close();
        }
        return token;
    }
}
