package com.brian.Model;
import com.brian.Model.Utils.Utils;

import java.sql.*;
//Defines the database Connection and Schemas for Mysql
public abstract class Base implements Utils {

    public static String username = "root";
    public static String password = "wamatu";
    public static String url ="jdbc:mysql://localhost:3306";
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;

    @Override
    public boolean getConnection() throws SQLException{
        return true;
    }

    @Override
    public boolean closeConnection() throws SQLException{
        return true;
    }

}
