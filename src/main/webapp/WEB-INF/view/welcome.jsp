<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<style>
form {
  margin:0 auto;
  width:300px
}
input {
  margin-bottom:3px;
  padding:10px;
  width: 100%;
  border:1px solid #CCC
}
button {
  padding:10px
}
label {
  cursor:pointer
}
#form-switch {
  display:none
}
#register-form {
  display:none
}
#form-switch:checked~#register-form {
  display:block
}
#form-switch:checked~#login-form {
  display:none
}
</style>
</head>

<body>
	<div style = "text-align:center">
	<h1>Welcome</h1>
	</div>
	<input type='checkbox' id='form-switch'>
		<form id='login-form' action="/login" method="POST">
  			<input name="username" type="text" placeholder="Username" required>
  			<input name="password" type="password" placeholder="Password" required>
  			<button type='submit'>Login</button>
  			<label for='form-switch'><span>Register</span></label>
		</form>
		
		<form id='register-form' action="/register" method='post' modelAttribute="registerForm">
  			<input name="username" type="text" placeholder="Username" required>
  			<input name="password" type="password" placeholder="Password" required>
  			<input type="password" placeholder="Re Password" required>
  			<button type='submit'>Register</button>
  			<br>
  			<label for='form-switch'>Already Member?Sign In Now..</label>
		</form>
</body>
</html>