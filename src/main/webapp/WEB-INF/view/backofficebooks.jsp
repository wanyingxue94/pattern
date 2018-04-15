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
<script>
function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("books");
  switching = true;
  // Set the sorting direction to ascending:
  dir = "asc"; 
  /* Make a loop that will continue until
  no switching has been done: */
  while (switching) {
    // Start by saying: no switching is done:
    switching = false;
    rows = table.getElementsByTagName("TR");
    /* Loop through all table rows (except the
    first, which contains table headers): */
    for (i = 1; i < (rows.length - 1); i++) {
      // Start by saying there should be no switching:
      shouldSwitch = false;
      /* Get the two elements you want to compare,
      one from current row and one from the next: */
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      /* Check if the two rows should switch place,
      based on the direction, asc or desc: */
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
          // If so, mark as a switch and break the loop:
          shouldSwitch= true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++; 
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}
</script>
<body>
<div>
Search Books
<form action="/books/search" method="POST" modelAttribute="registerForm">
<input name="query" />
<select name="searchOn">
  <option value="topic">topic</option>
  <option value="author">author</option>
  <option value="title">title</option>
</select>
<input type="submit" value="Search" />
</form>
</div>
<div>
<table id="books">
    <thead>
        <tr>
         	<th>Image</th>
            <th onclick="sortTable(0)">Title</th>
            <th onclick="sortTable(1)">Author</th>
            <th onclick="sortTable(2)">Topic</th>
            <th onclick="sortTable(3)">Stock</th>
            <th onclick="sortTable(4)">Price</th>
            <th onclick="sortTable(4)">Rate</th>
            <th>Update Stock</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
             	<td><img src="/book/imageDisplay?id=${book.id}" height="42" width="42"/></td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.topic}</td>
                <td>${book.stock}</td>
                <td>${book.price}</td>
                <td>${book.rating}</td>
                <td>
                	<form action="/backoffice/updateStock" method="POST">
                		<input name="updatedStock" />
                		<input type="hidden" name="bookId" value=${book.id} />
                		<input type="submit" value="Update Stock" />
                	</form>
                </td>
            </tr>       
        </c:forEach>
    </tbody>
</table>
</div>
<div>
<br>
 <form action="/backoffice/addbook">
    <input type="submit" value="Add Book" />
 </form>
</div>
<div>
<br>
 <form action="/backoffice/users">
    <input type="submit" value="View Users" />
 </form>
      <div>
    	<br>
    	<form action="/logout" method="POST">
    		<input type="submit" value="Logout" />
    	 </form>
    </div>
</div>
</body>
</html>