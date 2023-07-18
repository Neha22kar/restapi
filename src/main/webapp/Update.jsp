<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Update</title>
</head>
<body>
 <div align="center">
  <h1>Employee Register Form</h1>
  <form action="/PutServlet" method="put">
     Id:<input type='text' name="id"><br><br><br>
     username:<input type='text' name="name"><br><br><br>
     password:<input type='text'name="password"><br><br><br>
    email:<input type='text' name="email"><br><br><br>
    city:<input type='text' name="city"><br><br><br>
    <button type="submit">Submit</button>
  </form>
  Update details by Username & password:
 <a href="Update2.jsp"><button type="submit">Submit</button></a>
  </div>
</body>
</html>