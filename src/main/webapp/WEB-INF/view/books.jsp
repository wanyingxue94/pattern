<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>
<div>
<table id="books">
    <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Topic</th>
            <th>Stock</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.topic}</td>
                <td>${book.stock}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
</div>
<div>
Search Books
<form action="books/search" method="POST" modelAttribute="registerForm">
<input name="query" />
<select name="searchOn">
  <option value="topic">topic</option>
  <option value="author">author</option>
  <option value="title">title</option>
</select>
<input type="submit" value="Search" />
</form>
</div>
</body>
</html>