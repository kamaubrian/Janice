package com.brian.Model.Utils;

import java.sql.SQLException;

public interface Utils {

    boolean getConnection() throws SQLException;
    boolean closeConnection() throws SQLException;
}
