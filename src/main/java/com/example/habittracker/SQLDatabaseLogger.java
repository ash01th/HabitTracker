package com.example.habittracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseLogger {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost/ht2";
        String user = "root";
        String password = "root";

        // SQL query to execute
        String query = "SELECT * FROM data";

        // Log file name
        String filename = "log.txt";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // Set up the database connection
            conn = DriverManager.getConnection(url, user, password);

            // Execute the query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Open the file for writing
            writer = new BufferedWriter(new FileWriter(filename, true));

            // Write the query result to the file
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    sb.append(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (writer != null) {
                    writer.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

