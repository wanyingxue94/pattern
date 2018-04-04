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
<h1>Order Confirmation</h1>
<div>
<h2>Order Code: ${order.id}</h2>
<table id="cart">
    <thead>
        <tr>
            <th>Book</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entry" items="${order.entries}">
            <tr>
                <td>${entry.book.title}</td>
                <td>${entry.quantity}</td>
                <td>${entry.price}</td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
<h2>Total Price: ${order.totalPrice}</h2>
<h2>Ship to: ${order.user.shippingAddress}</h2>
<h2>Payment Method: ${order.user.paymentMethod}</h2>
</div>
</body>
</html>