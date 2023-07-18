//package  org.com.Servlet.login.web.Login;
//
//import com.google.gson.Gson;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//@WebServlet("/UpdateServlet")
//public class UpdateServlet extends HttpServlet {
//    @Override
//    protected
//    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        System.out.println("Connected");
//        resp.setContentType("application/json");
//        PrintWriter pw = resp.getWriter();
//        System.out.println("Put Servlet");
//        Gson gson = new Gson();
//        int i = 0;
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line;
//
//            while ( (line=req.getReader().readLine())!=null){
//                sb.append(line);
//
//            }
//            User user = (User)gson.fromJson(sb.toString(),User.class);
//            Class.forName("org.mariadb.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test?user=root&password=root");
//            PreparedStatement preparedStatement = con.prepareStatement("update registeruser set name=?,city=? where id=1;");
//            preparedStatement.setString(1,user.getName("Ram"));
//            preparedStatement.setString(2,user.getPassword("4321"));
//
//            System.out.println("Connected for Update");
//
//            i =preparedStatement.executeUpdate();
//            if(i>0){
//                resp.setStatus(200);
//            }else {
//                resp.sendError(406,"Cannot find  details to  update user:"+user.getName("Ram"));
//            }
//    } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//}