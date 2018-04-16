<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div style="padding-left:50px;font-family:monospace;">
        <h2>Edit my account</h2>
        User: ${currentUser.username},${currentUser.paymentMethod}
        <form action="/edit-myaccount" method="POST">
            <div style="width: 100px; text-align:left;">
                <div style="padding:10px;">
                    Shipping Address: <textarea name="shippingAddress" cols="40" rows="5">${currentUser.shippingAddress}</textarea>
                </div>
                <div style="padding:10px;">
                    Payment Method: 
                    <select name="paymentMethod" id="paymentMethod">
  						<option value="payPal">PayPal</option>
  						<option value="visa">Visa </option>
  						<option value="masterCard">Master Card</option>
					</select>
					<script type="text/javascript">
							var temp = ${currentUser.paymentMethod};
							var mySelect = document.getElementById('paymentMethod');
							
							for(var i, j = 0; i = mySelect.options[j]; j++) {
							    if(i.value == temp) {
							        mySelect.selectedIndex = j;
							        break;
							    }
							}
					</script>
                </div>
                <div style="padding-left:10px;">
                    <input type="submit" value="Submit" />
                </div>
            </div>
        </form>
    </div>
    <div>
    	<br>
    	<div style="padding-left:60px;">
    	<form action="/logout" method="POST">
    		<input type="submit" value="Logout" />
    	 </form>
    	 </div>
    </div>
</body>

</html>