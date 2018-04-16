<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var temp = ${oldcomment.rate};
	var mySelect = document.getElementById('rate');
	for(var i, j = 0; i = mySelect.options[j]; j++) {
		if(i.value == temp) {
			mySelect.selectedIndex = j;
			break;
			}
		}
</script>
<body>
<h2>Add comment</h2>
<div style="padding-left:50px;font-family:monospace;">
        <h2>Book : ${book.title}</h2>
        <form action="/add-comment" method="POST">
            <div style="width: 100px; text-align:left;">
                <div style="padding:10px;">
                    My Comment: <textarea name="comment" cols="40" rows="5"> ${oldcomment.comment}</textarea>
                </div>
                <input type="hidden" name="bookId" value=${book.id} />
                <div style="padding:10px;">
                    Rating: 
                    <select name="rate" id="rate">
  						<option value="1">1</option>
  						<option value="2">2 </option>
  						<option value="3">3</option>
  						<option value="4">4</option>
  						<option value="5">5</option>
					</select>
					
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