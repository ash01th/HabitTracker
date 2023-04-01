package com.example.habittracker;

import java.io.*;
import java.sql.*;
import java.util.logging.*;

public class MyLogger {
    private static final Logger logger = Logger.getLogger("MyLogger");

    public static void main() throws Exception {
        // Set up a file handler to log to a file
        FileHandler handler = new FileHandler("mylogfile.log");
        logger.addHandler(handler);

        // Set the log level to ALL to log all messages
        logger.setLevel(Level.ALL);

        // Set up the database connection
        String url = "jdbc:mysql://localhost/ht2";
        String user = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);

        // Set up the SQL query
        String query = "SELECT * FROM data";

        // Execute the query
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        // Write the query result to the log file
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                sb.append(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
            }
            logger.info(sb.toString());
        }

        // Close the database connection
        conn.close();
    }
}

