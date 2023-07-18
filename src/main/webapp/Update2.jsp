<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Update</title>
</head>
<body>
 <div align="center">
  <h1>Employee Register Form</h1>
   Update details by Username & password:
   <br><br><br>
  <form action="/patchServlet" method="patch">
     Id:<input type='text' name="id"><br><br><br>
     username:<input type='text' name="name"><br><br><br>
    <button type="submit">Submit</button>
  </form>

  </div>
</body>
</html>