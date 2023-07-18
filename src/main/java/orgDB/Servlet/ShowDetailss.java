////public class ShowDetailss extends HttpServlet {
//    public
//    void doGet(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("ShowDetailss  called");
//
//        response.setContentType("text/html");
//        try (PrintWriter out = response.getWriter()) {
//            String id = request.getParameter("id");
//            Class.forName("org.mariadb.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
//            PreparedStatement ps =
//                    con.prepareStatement("select * from registeruser where id=?");
//            ps.setString(1, id);
//            out.print("<table width=50% border=1>");
//            out.print("<caption>Employee Details:</caption>");
//            ResultSet rs = ps.executeQuery();
//            /* Printing column names */
//            out.print("</br></br>");
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int total = rsmd.getColumnCount();
//            out.print("<tr>");
//            for (int i = 1; i <= total; i++) {
//                out.print("<th>" + rsmd.getColumnName(i) + "</th>");
//            }
//            out.print("</tr>");
//            /* Printing result */
//            while (rs.next()) {
//                out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + " </td><td>" + rs.getString(3) + "<tr><td>" + rs.getString(4) + "<tr><td>" + rs.getString(5) + "</td></tr>");
//            }
//            out.print("</table>");
//        } catch (Exception e2) {
//            e2.printStackTrace();
//        }
//    }
//}

//    // Method to handle POST method request.
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        doGet(request, response);
//    }
//}
