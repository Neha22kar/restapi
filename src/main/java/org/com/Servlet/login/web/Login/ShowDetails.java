package org.com.Servlet.login.web.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.Servlet.login.database.DButils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/ShowDetails")
public class ShowDetails extends HttpServlet {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://192.168.100.174/db";
    @Override
    protected
    void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        System.out.println("SHow Details");
//        resp.sendRedirect("ShowDetails.jsp");
        try(PrintWriter pw= resp.getWriter();){
          Connection con =  DButils.getConnection();

//            PreparedStatement ps =con.prepareStatement("select * from registeruser;");
            PreparedStatement ps =con.prepareStatement("select * from registeruser where id=?");
            ResultSet rs= ps.executeQuery();

            while (rs.next()){
                System.out.println("id: "+rs.getString("id")+", name "+rs.getString("name")+",password "+rs.getString("password")+",email "+rs.getString("email")+",city "+rs.getString("city"));
            }
            pw.write("in do get methods");
           pw.write("id: "+rs.getString("id")+", name "+rs.getString("name")+",password "+rs.getString("password")+",email "+rs.getString("email")+",city "+rs.getString("city"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}




