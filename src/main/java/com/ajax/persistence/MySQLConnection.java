package com.ajax.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static Connection connection;

    /**
     *
     * @return connection
     */
    public static Connection connect() {
        try {
            connection = getMySQLConnection(
                    DBConstants.HOSTNAME,
                    DBConstants.DBNAME,
                    DBConstants.USERNAME,
                    DBConstants.PASSWORD
            );

            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            System.err.println("Cannot establish database connection.\nAborting...");
            System.exit(1);
        }
        return connection;
    }

    /**
     * Creates mysql connection
     *
     * @return mysql connection
     */
    private static Connection getMySQLConnection(String hostname, String dbname, String username, String password)
            throws SQLException, ClassNotFoundException {
        // load mysql driver
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://" + hostname + ":3306/" + dbname;
        return DriverManager.getConnection(url, username, password);
    }
}
