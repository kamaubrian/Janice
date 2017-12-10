package com.brian.Model;

import java.sql.SQLException;

public class User extends Base {

    public boolean addBotUser(long chatID,String username) throws SQLException{
        String sql;
        boolean success=true;
        try{
            getConnection();
            sql="INSERT INTO USER(Chat_ID,Username) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,chatID);
            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
            if(preparedStatement.executeUpdate()>0){
               success= true;
            }else {
                success=false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }


        return success;
    }
}
