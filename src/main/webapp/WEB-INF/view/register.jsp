<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
<div style="padding-left:50px;font-family:monospace;">
        <h2>Register User</h2>
        <form action="/register" method="POST" modelAttribute="registerForm">
            <div style="width: 100px; text-align:left;">
                <div style="padding:10px;">
                    Username: <input name="username" />
                </div>
                <div style="padding:10px;">
                    Password: <input type="password" name="password" />
                </div>
                <div style="padding:20px;text-align:center">
                    <input type="submit" value="Submit" />
                </div>
            </div>
        </form>
    </div>
</body>
</html>