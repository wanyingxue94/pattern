<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
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
<body>
<h1>Book review</h1>
<ul>
  <li>Title: ${book.title}</li>
  <li>Author: ${book.author}</li>
  <li>Topic: ${book.topic}</li>
</ul>
<table id="review">
    <thead>
        <tr>
            <th>Comment</th>
            <th>Rate</th>
            <th>Review By</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment.comment}</td>
                <td>${comment.rate}</td>
                <td>${comment.username}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
</body>

</html>