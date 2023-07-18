package org.com.Servlet.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public
class DButils {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String url="jdbc:mariadb://localhost:3306/test";
    static final String username="root";
    static final String password="root";
    static Connection cn;

   public static Connection getConnection() throws SQLException, ClassNotFoundException {
       Class.forName("org.mariadb.jdbc.Driver");
        cn =  DriverManager.getConnection(url,username,password);
        return cn;
   }

}
