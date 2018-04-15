<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<h1>Add new book</h1>
<form method="POST" action="/books/add" enctype="multipart/form-data">
  Title:<br>
  <input type="text" name="title"><br>
  Author:<br>
  <input type="text" name="author"><br>
   Topic:<br>
  <input type="text" name="topic"><br>
   Price:<br>
  <input type="text" name="price"><br>
   Stock:<br>
  <input type="number" name="stock"><br>
   Image:<br>
  <input type="file" name="image"><br>
  <br>
  <br>
  <input type="submit" value="Submit" />
</form>
</div>
    <div>
    	<br>
    	<form action="/logout" method="POST">
    		<input type="submit" value="Logout" />
    	 </form>
    </div>
</body>
</html>