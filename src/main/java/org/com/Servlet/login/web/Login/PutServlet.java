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
import java.sql.*;

@WebServlet("/PutServlet")
public class PutServlet extends HttpServlet {
    Connection connection = null;
    PreparedStatement preparedStatement=null;
   @Override
    protected
     void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        System.out.println("in doPut");
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
                connection=DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","root");
               //create PreparedStatement
                System.out.println("Connection created");
                String query = " update registeruser set  name=?,password=?,email=?,city=?  where id=?;";
                System.out.println("Query");
                 preparedStatement=connection.prepareStatement(query);
                System.out.println("Query");
                //Set the parameters values
                preparedStatement.setString(5,javaObject.getId());
                preparedStatement.setString(1,javaObject.getName());
                preparedStatement.setString(2,javaObject.getPassword());
                preparedStatement.setString(3,javaObject.getEmail());
                preparedStatement.setString(4,javaObject.getCity());
                preparedStatement.executeUpdate();
                System.out.println("Set the parameters values"+ javaObject.getName());
                resp.setStatus(HttpServletResponse.SC_OK);
                //send a response
                resp.getWriter().write("Data Updated Successfully");
           } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("Data Not Updated ");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try{
                    if(preparedStatement!= null){
                    preparedStatement.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);


            }

    }
}
}






//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        response.setCharacterEncoding("UTP-8");
//        //Establish connection
//        Connection con = null;
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
//            String query = " update registeruser set name=?, password=? where id=?;";
//            //           " update registeruser set name='nita', password='nita' where id=2;"
//            PreparedStatement stmt = conn.prepareStatement(query);
//            System.out.println("Updated");
//            ResultSet rs = stmt.executeQuery();
//            //create JSON Object
//            JSONArray jsonArray = new JSONArray();
//            while (rs.next()) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("id", rs.getString("id"));
//                jsonObject.put("name", rs.getString("name"));
//                jsonObject.put("password", rs.getString("password"));
//                JSONArray put = jsonArray.put(jsonObject);
//            }
//            out.println(jsonArray.toString());
////    System.out.println("Select query");
////    User user = new User();
////    user.setId("1");
////    user.setName("Ram");
////    user.setPassword("1234");
////    String userJsonString = this.gson.toJson(user);
//
//            try {
//                out = response.getWriter();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//
////    out.println(userJsonString);
////    out.flush();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
//----------------------------------------------------------------

