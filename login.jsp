<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<c:if test = "${not empty errorMessages}">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${errorMessages}" var = "message">
			 	<li> <c:out value = "${message}" />
			 </c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessaes" scope = "session"/>
</c:if>

<div class = "login-contents">
	<div class = "login">
		<form action = "login" method = "post"> <br />
			<p><label for = "loginId">ログインID</label></p>
			<input name = "loginId" id = "loginId" class = "login"/><br />

			<p><label for = "password">パスワード</label></p>
			<input name = "password" type = "password" id = "password" class = "login"/> <br />
	
			<p><input type = "submit" value = "ログイン" class = "loginText"/> <br /></p>
		</form>
	</div> 
	
	<div class = "copyright"></div>
</div>
</body>
</html> 