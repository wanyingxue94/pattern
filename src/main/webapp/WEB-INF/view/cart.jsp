<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<h1>My Cart</h1>
<div>
<table id="cart">
    <thead>
        <tr>
            <th>Book</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entry" items="${cart.entries}">
            <tr>
                <td>${entry.book.title}</td>
                <td>${entry.quantity}</td>
                <td>${entry.price}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
<h2>Total Price: ${cart.totalPrice}</h2>
</div>
<div>
 <form action="/books" id="goback">
    <input type="submit" value="Go Back Shopping" />
  </form>
</div>
  <div>
  <form action="/checkout id="checkout">
    <input type="submit" value="Checkout" />
  </form>
</div>
</body>
</html>