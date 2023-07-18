package org.com.Servlet.login.database.Password;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.Servlet.login.web.Login.JavaObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register1")//"/register1"
public class RegisterServletPass extends HttpServlet {
    @Override
    protected
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        PrintWriter out = resp.getWriter();
        System.out.println("in register servlet");
        //collecting details
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String city = req.getParameter("city");
        System.out.println("collected details");
        System.out.println("collected city "+city);


        try {
            //Connect to the database
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("creating connection ");
            Connection con;
            con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/test", "root", "root");
            System.out.println("created connection 1");
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
            System.out.println("created connection 2");
            //Insert the user details into DB
            PreparedStatement preparedStatement = con.prepareStatement("insert into registeruser(name,password,email,city) values(?,?,?,?);");
            System.out.println("inserted query");
            preparedStatement.setString(1, name);
       //     preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, city);
            System.out.println("storing details "+name+"   "+password+"  "+ email +"  "+city+"   ");
           // preparedStatement.executeUpdate();
            //  //calling method
            System.out.println("calling method");
            String encryptedpassword =   encryptPassword(password);
            System.out.println("return from encryptedpassword method");

            preparedStatement.setString(2,encryptedpassword );
            preparedStatement.executeUpdate();
            //close DB connection
            System.out.println("details stored in database : "+ name +" "+ password +" "+ encryptedpassword +" "+ city +" "+ email);
            con.close();
            //send response to client
            resp.setContentType("text/html");

            System.out.println("registration successful !");
            out.println("<html><body>");
            out.println("<h2>registration successful !</h2>");
            out.println("</body></html>");
         //   resp.sendRedirect("RegisterSuccess.jsp");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        /* MessageDigest instance for MD5. */
        System.out.println("in encrypt password method");
        MessageDigest m = MessageDigest.getInstance("MD5");

        /* Add plain-text password bytes to digest using MD5 update() method. */
        m.update(password.getBytes());
        System.out.println("Add plain-text password bytes to digest using MD5 update() method");
        /* Convert the hash value into bytes */
        byte[] bytes = m.digest();
        System.out.println("Convert the hash value into bytes ");
        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
        StringBuilder s = new StringBuilder();
        System.out.println("The bytes array has bytes in decimal form. Converting it into hexadecimal format.");
        for(int i=0; i< bytes.length ;i++)
        {
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        /* Complete hashed password in hexadecimal format */
        String encryptedpassword = s.toString();
        System.out.println("Complete hashed password in hexadecimal format "+encryptedpassword);

        return encryptedpassword;

    }

    @Override
    protected
    void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        //Read the json data from request body
        BufferedReader reader=new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder jsonBody = new StringBuilder();
        String line;
        while ((line = reader.readLine())!= null){
            jsonBody.append(line);
        }
        System.out.println("Read the json data from request body");
        reader.close();
        //Convert JSON Object to Java Object
        Gson gson = new Gson();
        JavaObject javaObject= gson.fromJson(jsonBody.toString(),JavaObject.class);
        System.out.println("Convert JSON Object to Java Object");
        //Storing the Java Object in the DB
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw = resp.getWriter();

        try {
            //Establish DB connection
            try {


                connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
                //create PreparedStatement
                System.out.println("Establish DB connection");
                String query = " update registeruser set name=?,password=?,email=?,city=? where id=?;";
                preparedStatement=connection.prepareStatement(query);

                //Set the parameters values
                preparedStatement.setString(1,javaObject.toString());
                preparedStatement.setString(2,javaObject.toString());
                preparedStatement.setString(3,javaObject.toString());
                preparedStatement.setString(4,javaObject.toString());

                preparedStatement.executeUpdate();
                System.out.println("Set the parameters values");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                //send a response
                resp.getWriter().println(" User Register successfully..");
                resp.getWriter().write(" User Register successfully..");
                 pw.println("User Register successfully..");
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write(" User Not Register... ");
                pw.println("User Not Register... ");
            }finally {
                try{
                    if(preparedStatement!= null){
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
