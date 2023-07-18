package org.com.Servlet.login.web.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

@Override
    protected
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
            final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
            final String DB_URL = "jdbc:mariadb://localhost:3306/test";
        Connection con = null;
        Statement stmt = null;
        PrintWriter pw = resp.getWriter();//get the stream to write the data

    try {
            //STEP 2: Register JDBC driver
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            try {
                con = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/test", "root", "root");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Connected database successfully...");

//            int id = Integer.parseInt(req.getParameter("id"));

            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String city = req.getParameter("city");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = con
                    .prepareStatement("insert into registeruser(name,password,email,city) values(?,?,?,?);");
//            .prepareStatement("insert into registeruser(name,password,email,city) values(?,?,?,?);");
//            preparedStatement.setInt(1, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, city);
            preparedStatement.executeQuery();
            System.out.println(" User Register successfully...");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }


}