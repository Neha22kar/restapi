//package org.com.Servlet.login.database;
//
//
//public class LoginDao {
//
////    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
////        boolean status = false;
////
////        Class.forName("org.mariadb.jdbc.Driver");
////
////
////        try (Connection connection = DriverManager
////                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root");
////
////             int id=req.getParameter("id");
////             String username=req.getParameter("username");
////             String password=req.getParameter("password");
////             String email=req.getParameter("email");
////             String city=req.getParameter("city");
////             // Step 2:Create a statement using connection object
////             PreparedStatement preparedStatement = connection
////                     .prepareStatement("insert into registeruser values(?,?,?,?,?)")) {
////            preparedStatement.setInt(1, id);
////            preparedStatement.setString(2,username);
////            preparedStatement.setString(3,password);
////
////            preparedStatement.setString(4,email);
////            preparedStatement.setString(5,city);
////
////
////
////            System.out.println(preparedStatement);
////            ResultSet rs = preparedStatement.executeQuery();
////            status = rs.next();
////
////        } catch (SQLException e) {
////            // process sql exception
////            printSQLException(e);
////        }
////        return status;
////    }
////
////    private void printSQLException(SQLException ex) {
////        for (Throwable e: ex) {
////            if (e instanceof SQLException) {
////                e.printStackTrace(System.err);
////                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
////                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
////                System.err.println("Message: " + e.getMessage());
////                Throwable t = ex.getCause();
////                while (t != null) {
////                    System.out.println("Cause: " + t);
////                    t = t.getCause();
////                }
////            }
////        }
////    }
//}