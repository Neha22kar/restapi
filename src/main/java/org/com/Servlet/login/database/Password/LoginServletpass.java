package org.com.Servlet.login.database.Password;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
@WebServlet("/login4")
public class LoginServletpass extends HttpServlet {
    @Override
    protected
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login servlet");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        System.out.println("password from user "+password);
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(password);
            System.out.println("encrypted password "+encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("creating connection ");
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
            System.out.println("created connection 1");
            //Retrieve the user from the database
            PreparedStatement preparedStatement = con.prepareStatement("Select * from registeruser where name=? and password=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,encryptedPassword);
            System.out.println("retrieve details from user  :" + name + "    " +encryptedPassword +"   " +".");
            ResultSet rs = preparedStatement.executeQuery();
            //Check if the user exist
            if(rs.next()){
                //create session to store the username
                HttpSession session=req.getSession();
                session.setAttribute("name",name);
                //Redirect to home page
                resp.sendRedirect("ShowDetails.jsp");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Login Successful");
                System.out.println("show details");

            }else {
                //Send response to client
                resp.setContentType("text/html");
                out.println("<html><body>");
                out.println("<h2>Incorrect username or password!</h2>");
                out.println("<a href=\"Register.jsp\"><button type=\"submit\">Register</button></a>");
                out.println("</body></html>");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("MD5");
        byte[] hashedPassword= md.digest(password.getBytes());
        StringBuilder sb=new StringBuilder();
        for (byte b:hashedPassword){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();
    }

}
