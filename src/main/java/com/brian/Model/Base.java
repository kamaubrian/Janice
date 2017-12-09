package com.brian.Model;
import com.brian.Model.Utils.Utils;

import java.sql.*;
//Defines the database Connection and Schemas for Mysql
public abstract class Base implements Utils {

    public static String driver = "com.mysql.cj.Driver";
    public static String username = "root";
    public static String password = "wamatu";
    public static String url ="jdbc:mysql://localhost:3306/TelegramBot?useSSL=false";
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;

    @Override
    public boolean getConnection() throws SQLException{
        try{

            connection=DriverManager.getConnection(url,username,password);

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }

        return true;
    }

    @Override
    public boolean closeConnection() throws SQLException{
        if(getConnection()){
            connection.close();
        }
        return true;
    }

}
