package com.ajax.dbm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection;
	private static final String HOSTNAME = "mysql2.cs.stonybrook.edu";
	private static final String DBNAME = "";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	

    public static Connection connect() {
        try {
            connection = getMySQLConnection(HOSTNAME, DBNAME, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(connection == null) {
        	System.out.println("Cannot establish database connection.\nAborting...");
        	System.exit(1);
        }
        return connection;
    }

    /**
     * Creates mysql connection
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
