package com.dbautomation.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection implements AutoCloseable {

    final String PATH_TO_CONFIG = "config.properties";

    private Properties prop;

    private Connection connection;
    private Statement stmt;
    private PreparedStatement p;

    public DBConnection() throws SQLException, ClassNotFoundException, IOException {

        try (InputStream input = new FileInputStream(PATH_TO_CONFIG)) {
            prop = new Properties();

            prop.load(input);

            // System.out.println(
            //         prop.getProperty("db.host")
            //                 + "\n" + prop.getProperty("db.port"));

            String url = "jdbc:mysql://" + prop.getProperty("db.host") + ":" + prop.getProperty("db.port") + "/"
                    + prop.getProperty("db.schema") + "?useTimezone=true&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, prop.getProperty("db.user"), prop.getProperty("db.password"));
            stmt = connection.createStatement();
            p = connection.prepareStatement("hello");

        } catch(Exception e){
            e.printStackTrace();
        
        } finally {
            if (connection == null)
                throw new SQLException("Unable to create connection with database");
            if (stmt == null || p == null)
                connection.close();
        }
    }

    public void insertSQL(String query) throws SQLException{
        p = connection.prepareStatement(query);
        p.executeUpdate(query);
    }

    @Override
    public void close() throws Exception {
        p.close();
        stmt.close();
        connection.close();
    }

}
