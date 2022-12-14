package com.dbautomation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Import url from properties file

public class DBConnection implements AutoCloseable {
    private String url = "jdbc:mysql://" + DBConfigs.dbhost + ":" + DBConfigs.dbport + "/" + DBConfigs.dbschema
            + "?useTimezone=true&serverTimezone=UTC";;

    private Connection connection;
    private Statement stmt;
    private PreparedStatement p;

    public DBConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, DBConfigs.dbusername, DBConfigs.dbpassword);
            stmt = connection.createStatement();
            p = connection.prepareStatement("hello");

        } finally {
            if (connection == null)
                throw new SQLException("Unable to create connection with database");
            if (stmt == null || p == null)
                connection.close();
        }
    }

    // sql connection processes here...

    @Override
    public void close() throws Exception {
        p.close();
        stmt.close();
        connection.close();
    }

}
