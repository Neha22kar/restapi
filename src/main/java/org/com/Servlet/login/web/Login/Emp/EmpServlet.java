//package org.com.Servlet.login.web.Login.Emp;
//
//import com.google.gson.Gson;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.com.Servlet.login.web.Login.JavaObject;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.sql.*;
//@WebServlet("/Employee")
//public class EmpServlet extends HttpServlet{
//
////----------------------------
//@Override
//protected
//void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    super.doPost(req, resp);
//    PrintWriter out = resp.getWriter();
//    System.out.println("in register servlet");
//    //collecting details
//    String name = req.getParameter("name");
//    String password = req.getParameter("password");
//    String email = req.getParameter("email");
//    String city = req.getParameter("city");
//    System.out.println("collected details");
//    System.out.println("collected city "+city);
//
//
//    try {
//        //Connect to the database
//        Class.forName("org.mariadb.jdbc.Driver");
//        System.out.println("creating connection ");
//        Connection con;
//        con = DriverManager.getConnection(
//                "jdbc:mariadb://localhost:3306/test", "root", "root");
//        System.out.println("created connection 1");
//        con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
//        System.out.println("created connection 2");
//        //Insert the user details into DB
//        PreparedStatement preparedStatement = con.prepareStatement("insert into registeruser(name,password,email,city) values(?,?,?,?);");
//        System.out.println("inserted query");
//        preparedStatement.setString(1, name);
//        //     preparedStatement.setString(2, password);
//        preparedStatement.setString(3, email);
//        preparedStatement.setString(4, city);
//        System.out.println("storing details "+name+"   "+password+"  "+ email +"  "+city+"   ");
//        // preparedStatement.executeUpdate();
//        //  //calling method
//        System.out.println("calling method");
//        String encryptedpassword =   encryptPassword(password);
//        System.out.println("return from encryptedpassword method");
//
//        preparedStatement.setString(2,encryptedpassword );
//        preparedStatement.executeUpdate();
//        //close DB connection
//        System.out.println("details stored in database : "+ name +" "+ password +" "+ encryptedpassword +" "+ city +" "+ email);
//        con.close();
//        //send response to client
//        resp.setContentType("text/html");
//
//        System.out.println("registration successful !");
//        out.println("<html><body>");
//        out.println("<h2>registration successful !</h2>");
//        out.println("</body></html>");
//        //   resp.sendRedirect("RegisterSuccess.jsp");
//
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    } catch (ClassNotFoundException e) {
//        throw new RuntimeException(e);
//    } catch (NoSuchAlgorithmException e) {
//        throw new RuntimeException(e);
//    }
//
//}
//
//    private String encryptPassword(String password) throws NoSuchAlgorithmException {
//        /* MessageDigest instance for MD5. */
//        System.out.println("in encrypt password method");
//        MessageDigest m = MessageDigest.getInstance("MD5");
//
//        /* Add plain-text password bytes to digest using MD5 update() method. */
//        m.update(password.getBytes());
//        System.out.println("Add plain-text password bytes to digest using MD5 update() method");
//        /* Convert the hash value into bytes */
//        byte[] bytes = m.digest();
//        System.out.println("Convert the hash value into bytes ");
//        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
//        StringBuilder s = new StringBuilder();
//        System.out.println("The bytes array has bytes in decimal form. Converting it into hexadecimal format.");
//        for(int i=0; i< bytes.length ;i++)
//        {
//            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//        }
//
//        /* Complete hashed password in hexadecimal format */
//        String encryptedpassword = s.toString();
//        System.out.println("Complete hashed password in hexadecimal format "+encryptedpassword);
//
//        return encryptedpassword;
//
//    }
//    //-----------------------------------------------
//    private Gson gson = new Gson();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        response.setCharacterEncoding("UTP-8");
//        //Establish connection
//        Connection con = null;
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
//            String query ="Select * from registeruser";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            //create JSON Object
//            JSONArray jsonArray = new JSONArray();
//            while (rs.next())
//            {
//                JSONObject jsonObject =new JSONObject();
//                jsonObject.put("id",rs.getString("id"));
//                jsonObject.put("name",rs.getString("name"));
//                jsonObject.put("password",rs.getString("password"));
//                JSONArray put = jsonArray.put(jsonObject);
//            }
//            out.println(jsonArray.toString());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            if(con != null){
//                try{
//                    con.close();
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }
////    System.out.println("Select query");
////    User user = new User();
////    user.setId("1");
////    user.setName("Ram");
////    user.setPassword("1234");
////    String userJsonString = this.gson.toJson(user);
//
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
////    out.println(userJsonString);
////    out.flush();
//    }
////----------------------------------------------------
//Connection connection = null;
//    PreparedStatement preparedStatement=null;
//    @Override
//    protected
//    void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
//        System.out.println("in doPut");
//        //Read the json data from request body
//        BufferedReader reader=new BufferedReader(new InputStreamReader(req.getInputStream()));
//        StringBuilder jsonBody = new StringBuilder();
//        String line;
//        while ((line = reader.readLine())!= null){
//            jsonBody.append(line);
//        }
//        System.out.println("Read the json data from request body");
//        reader.close();
//        //Convert JSON Object to Java Object
//        Gson gson = new Gson();
//        System.out.println("jsonBody.toString()"+ jsonBody);
//        JavaObject javaObject= gson.fromJson(jsonBody.toString(),JavaObject.class);
//        System.out.println("Convert JSON Object to Java Object");
//        System.out.println("name: "+ javaObject.getName());
//        //Storing the Java Object in the DB
//
//        //Establish DB connection
//        try {
//            System.out.println("Establish DB connection");
//            Class.forName("org.mariadb.jdbc.Driver");
//            connection=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
//            //create PreparedStatement
//            System.out.println("Connection created");
//            String query = " update registeruser set  name=?,password=?,email=?,city=?  where id=?;";
//            System.out.println("Query");
//            preparedStatement=connection.prepareStatement(query);
//            System.out.println("Query");
//            //Set the parameters values
//            preparedStatement.setString(5,javaObject.getId());
//            preparedStatement.setString(1,javaObject.getName());
//            preparedStatement.setString(2,javaObject.getPassword());
//            preparedStatement.setString(3,javaObject.getEmail());
//            preparedStatement.setString(4,javaObject.getCity());
//            preparedStatement.executeUpdate();
//            System.out.println("Set the parameters values"+ javaObject.getName());
//            resp.setStatus(HttpServletResponse.SC_OK);
//            //send a response
//            resp.getWriter().write("Data Updated Successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("Data Not Updated ");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try{
//                if(preparedStatement!= null){
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//
//
//            }
//
//        }
//
//    }
//    //------------------------------------------------
//    @Override
//    protected
//    void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
//        System.out.println("in doDelete");
//        //Read the json data from request body
//        BufferedReader reader=new BufferedReader(new InputStreamReader(req.getInputStream()));
//        StringBuilder jsonBody = new StringBuilder();
//        String line;
//        while ((line = reader.readLine())!= null){
//            jsonBody.append(line);
//        }
//        System.out.println("Read the json data from request body");
//        reader.close();
//        //Convert JSON Object to Java Object
//        Gson gson = new Gson();
//        System.out.println("jsonBody.toString()"+ jsonBody);
//        JavaObject javaObject= gson.fromJson(jsonBody.toString(),JavaObject.class);
//        System.out.println("Convert JSON Object to Java Object");
//        System.out.println("name: "+ javaObject.getName());
//        //Storing the Java Object in the DB
//
//        //Establish DB connection
//        try {
//            System.out.println("Establish DB connection");
//            Class.forName("org.mariadb.jdbc.Driver");
//            connection= DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
//            //create PreparedStatement
//            System.out.println("Connection created");
//            String query = " delete from  registeruser where id=?;";
//            System.out.println("Query");
//            preparedStatement=connection.prepareStatement(query);
//            System.out.println("Query");
//            //Set the parameters values
//            preparedStatement.setString(1,javaObject.getId());
//            preparedStatement.executeUpdate();
//            System.out.println("Set the parameters values "+ javaObject.getId());
//            resp.setStatus(HttpServletResponse.SC_OK);
//            //send a response
//            resp.getWriter().write("Data delete Successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("Data Not Updated ");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try{
//                if(preparedStatement!= null){
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//
//
//            }
//
//        }
//
//    }
//
//
//
//}
//
//
