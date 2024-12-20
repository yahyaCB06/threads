package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexion {
    Connection connection ;

    public connexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=  DriverManager.getConnection("jdbc:mysql://localhost:3306/orders&customers","root","");

    }
    public Connection getConnection(){
        return connection;
    }
}
