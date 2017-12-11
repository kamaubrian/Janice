package com.brian.Model;
import com.brian.Model.Utils.Utils;
import java.sql.*;
/*
    Author: Brian Kamau
 */
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

    protected Base(){
        String sql;
        try{
            getConnection();
            statement = connection.createStatement();
            sql="CREATE TABLE IF NOT EXISTS User(" +
                    "ID INT NOT NULL AUTO_INCREMENT," +
                    "Chat_ID INT NOT NULL," +
                    "Username VARCHAR(35) NOT NULL," +
                    "Created_At TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "PRIMARY KEY(ID))";
            statement.addBatch(sql);
            statement.executeBatch();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean getConnection() throws SQLException{
        try{

            connection=DriverManager.getConnection(url,username,password);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean closeConnection() throws SQLException{
        if(!connection.isClosed()){
            connection.close();
        }
        return true;
    }

}
