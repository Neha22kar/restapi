package org.com.Servlet.login.web.Login;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    Connection connection = null;
    PreparedStatement preparedStatement=null;

    @Override
    protected
    void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        PrintWriter out=resp.getWriter();
        System.out.println("in doDelete");
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
        System.out.println("jsonBody.toString()"+ jsonBody);
        JavaObject javaObject= gson.fromJson(jsonBody.toString(),JavaObject.class);
        System.out.println("Convert JSON Object to Java Object");
        System.out.println("name: "+ javaObject.getName());


        //Storing the Java Object in the DB

        //Establish DB connection
        try {
            System.out.println("Establish DB connection");
            Class.forName("org.mariadb.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
            //create PreparedStatement
            System.out.println("Connection created");
            String query = " delete from  registeruser where id=?;";
            System.out.println("Query");
            preparedStatement=connection.prepareStatement(query);
            System.out.println("Query");
            //Set the parameters values
            preparedStatement.setString(1,javaObject.getId());
            preparedStatement.executeUpdate();
            System.out.println("Set the parameters values "+ javaObject.getId());
            resp.setStatus(HttpServletResponse.SC_OK);
            //send a response

            System.out.println("Data delete Successfully");
            out.println(";;;;;");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(".....");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Data Not Updated ");
        } catch (ClassNotFoundException e) {
            System.out.println(".....");
            throw new RuntimeException(e);
        }
            }

        }






