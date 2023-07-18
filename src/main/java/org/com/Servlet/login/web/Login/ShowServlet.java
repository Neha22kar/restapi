package org.com.Servlet.login.web.Login;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="ShowServlet",urlPatterns = "/ShowServlet")
public
class ShowServlet extends HttpServlet {
private static final  long serialVersionUID=1L;

private final Gson gson = new Gson();
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTP-8");
    //Establish connection
    Connection con = null;
    try {
        Class.forName("org.mariadb.jdbc.Driver");
       con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
        String query ="Select * from registeruser where id=?";

        PreparedStatement stmt = con.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();
        //create JSON Object
        JSONArray jsonArray = new JSONArray();
        while (rs.next())
        {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("id",rs.getString("id"));
            jsonObject.put("name",rs.getString("name"));
            jsonObject.put("password",rs.getString("password"));
            JSONArray put = jsonArray.put(jsonObject);
        }
        out.println(jsonArray.toString());

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } finally {
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
//    System.out.println("Select query");
//    User user = new User();
//    user.setId("1");
//    user.setName("Ram");
//    user.setPassword("1234");
//    String userJsonString = this.gson.toJson(user);

    try {
        out = response.getWriter();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


//    out.println(userJsonString);
//    out.flush();
}
}
