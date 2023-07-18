//package TaskServlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class UserServlet extends HttpServlet {
//    private static  final long serialVersionUID = 1L;
//    private Connection connection;
//
//    public UserServlet() {
//        super();
//    }
//    @Override
//    public void init() throws ServletException{
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        String url = "jdbc:mariadb://localhost:3306/test";
//        String username = "root";
//        String password = "root";
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Connected");
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String requestBody = reader.readLine();
//        reader.close();
//
//        //Parse JSON data
//        //Assuming json format like :{"name":"ram","password":"1234"}
//        JSONObject json = new JSONObject(requestBody);
//        String name = json.getString("name");
//        String password = json.getString("password");
//        String email = json.getString("email");
//        String city = json.getString(" city");
//
//        //Insert new user record into the database
//        String query = "insert into registeruser(name,password,email,city) values(?,?,?,?);";
//        PrintWriter writer;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            try {
//
//                preparedStatement.setString(1, name);
//                preparedStatement.setString(2, password);
//                preparedStatement.setString(3, email);
//                preparedStatement.setString(4, city);
//                preparedStatement.executeUpdate();
//                preparedStatement.close();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//
//
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            response.setContentType("application/json");
//            writer = response.getWriter("{\"message\":\"Employee Successfully Created\.");
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        });
//        writer.close();
//        System.out.println(" User Register successfully...");
//
//    } catch (Exception e) {
//           e.printStackTrace();
//            int SC_INTERNAL_SERVER_ERROr = 0;
//        HttpServletResponse response = null;
//        response.setStatus(SC_INTERNAL_SERVER_ERROr);
//        }
//
//    }
//    @Override
//    Protected void doPut(HttpServletRequest request,HttpServletResponse response)
//        throws ServletException,IOException{
//        String Id=request.getParameter("id");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String requestBody = reader.readLine();
//        reader.close();
//
//        // Parse JSON data
//        // Assuming json format like :{"name":"ram","password":"1234"}
//        JSONObject json = new JSONObject(requestBody);
//        String name = json.getString("name");
//        String password = json.getString("password");
//        String email = json.getString("email");
//        String  city = json.getString(" city");
//        String query= "Update registeruser set name=? ,password=?, email=?,city=? where id=?";
//        PreparedStatement preparedStatement = null;
//
//        try {
//            preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, city);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.setContentType("application/json");
//            PrintWriter writer=response.getWriter();
//            writer.println("{\"message\":"Employee updates successfully .\"}");
//            writer.close();
//            System.out.println(" User Updated successfully...");
//        } catch (Exception e) {
//            e.printStackTrace();
//            int SC_INTERNAL_SERVER_ERROr;
//            response.setStatus(SC_INTERNAL_SERVER_ERROr);
//        }
//    }
//    @Override
//    Protected void doDelete(HttpServletRequest request,HttpServletResponse response)
//            throws ServletException,IOException{
//            String Id=request.getParameter("id");
//            String query="Delete from registeruser where id==?";
//            preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1,Integer.parseInt(employeeId);
//              preparedStatement.executeUpdate();
//              preparedStatemant.close();
//
//              response.setStatus(HttpServletResponse.SC_OK);
//              response.setContentType("application/json");
//              Printwriter writer = response.getWriter();
//              writer.println("{"message\":\"Employee deleted successfully.\"}");
//              writer.close();
//              }
//              catch(Exception e){
//              e.printStackTrace();
//              reponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//             }
//             @Override
//              public void destroy(){
//             try{
//                 connection.close();
//             }catch(EXception e){
//                 e.printStackTrace();
//        }
//        }
//

//
//
//            }
//
//}

//package org.com.Servlet.login.web.Login;
//
//import com.google.gson.Gson;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//@WebServlet("/PutServlet")
//public
//class PutServlet extends HttpServlet {
//    private static  final long serialVersionUID = 1L;
//    private Gson gson = new Gson();
//    @Override
//    protected PutServlet void doPut(HttpServlet request,HttpServlet response)
//            throws ServletException, IOException {
//        String Id;
//        Id = request.getParameter("id");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String requestBody = reader.readLine();
//        reader.close();
//
//        // Parse JSON data
//        // Assuming json format like :{"name":"ram","password":"1234"}
//        JSONObject json = new JSONObject(requestBody);
//        String name = json.getString("name");
//        String password = json.getString("password");
//        String email = json.getString("email");
//        String  city = json.getString(" city");
//        String query= "Update registeruser set name=? ,password=?, email=?,city=? where id=?";
//        PreparedStatement preparedStatement = null;
//
//        try {
//            preparedStatement = connection.prepareStatement(query);
//
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, email);
//            preparedStatement.setString(4, city);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.setContentType("application/json");
//            PrintWriter writer=response.getWriter();
//            writer.println("{\"message\":"Employee updates successfully \."}");
//            writer.close();
//            System.out.println(" User Updated successfully...");
//        } catch (Exception e) {
//            e.printStackTrace();
//            int SC_INTERNAL_SERVER_ERROr;
//            response.setStatus(SC_INTERNAL_SERVER_ERROr);
//        }
//    }
