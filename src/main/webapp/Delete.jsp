<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Delete Employee</title>
</head>
<body>

 <form action="deleteServlet" method="delete">
   Enter your Employee ID:
  <input type="text" name="id"/>
  <br/>
  <input type="submit" value="Delete"/>
  </form>
</body>
</html>