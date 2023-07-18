package org.com.Servlet.login.database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login3")
public class PasswordEncriptionUtils extends HttpServlet {
    @Override
    protected
    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String username=req.getParameter("name");
        String password=req.getParameter("password");

        //Encrypt The password using MD5
        String encryptedPassword = encryptedPassword(password);
        //Check if the username and password match
        if(validateLogin(username,encryptedPassword)){
            resp.sendRedirect("loginSuccess.jsp");//Redirect to success page
        }else {
            resp.sendRedirect("Failure.jsp");//Redirect to failure page
        }
    }

    private
    String encryptedPassword(String password) {
        try{
            MessageDigest md =MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b: digest){
                StringBuilder append = sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        }catch (NoSuchAlgorithmException e){
         e.printStackTrace();
        }
        return null;
    }
    public boolean validateLogin(String name,String password){

        return name.equals("admin")&&password.equals("5f4dcc3b5aa765d618327deb882cf99");
    }
}
