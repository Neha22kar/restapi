package org.com.Servlet.login.web.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login1")
public class LoginServlet extends HttpServlet {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://192.168.100.174/db";
    @Override
    protected
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("called");

        String username = req.getParameter("name");
        String password = req.getParameter("password");

        //  Database credentials
        final String USER = "root";
        final String PASS = "root";
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw = resp.getWriter();//get the stream to write the data


        Connection con = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/test", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            boolean flag = true;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from registeruser");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                if (rs.getString(2).equals(username) && rs.getString(3).equals(password)) {
                    flag = false;
                    pw.println("<html><body>");
                    pw.println("Welcome to servlet");
                    pw.println("<input type=\"button\" value=\"Add User\" onclick=\"window.location='Register.jsp'\" >");
                    pw.println("<input type=\"button\" value=\"Show Details\" onclick=\"window.location='ShowDetails.jsp'\" >");

                }
            }

            if (flag) {
                pw.println("<html><body>");
                pw.println("Wrong credential");
                pw.println("</body></html>");

            }

            System.out.println("Creating table in given database...");


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected
    void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);

        String username = req.getParameter("name");
        String password = req.getParameter("password");

        //  Database credentials
        final String USER = "root";
        final String PASS = "root";
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw = resp.getWriter();//get the stream to write the data


        Connection con = null;
        Statement stmt = null;

    }
}




