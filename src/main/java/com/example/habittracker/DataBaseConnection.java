package com.example.habittracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection DataBaseLink;
    public  Connection getDBConnection(){
        String DataBaseName="ht2";
        String DataBaseUser="root";
        String DataBasePassword="root";
        String Url="jdbc:mysql://localhost/"+DataBaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DataBaseLink= DriverManager.getConnection(Url,DataBaseUser,DataBasePassword);

        }catch (Exception e){
            e.printStackTrace();
        }
        return DataBaseLink;
    }
}
