package org.com.Servlet.login.web.Login;




import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServletwithoutDB extends HttpServlet {


    public
    LoginServletwithoutDB() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException,IOException
    {
        res.setContentType("text/html");//setting the content type
        PrintWriter pw=res.getWriter();//get the stream to write the data

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        String uname="neha";
        String pwrd="neha";

        if(uname.equals(username)&& pwrd.equals(password)) {


//writing html in the stream
            pw.println("<html><body>");
            pw.println("Welcome to servlet");
            pw.println("</body></html>");

        }

        else {
            pw.println("<html><body>");
            pw.println("Wrong credrential");
            pw.println("</body></html>");
        }

        pw.close();//closing the stream
      //  return null;
    }




}