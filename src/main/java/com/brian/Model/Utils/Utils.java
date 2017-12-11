package com.brian.Model.Utils;

import java.sql.SQLException;

/**
 * Author: Brian Kamau
 */

public interface Utils {

    boolean getConnection() throws SQLException;
    boolean closeConnection() throws SQLException;
}
