<%@ page language = "java" contentType = "text/html; charset = UTF-8"
	pageEncoding = "UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
<title>ログイン</title>
<link href = "CSS/style.css" rel = "stylesheet" type = "text/css">
</head>
<body>

	<c:if test = "${ not empty messages }">
		<div class = "messages">
			<ul>
				<c:forEach items = "${ messages }" var = "message">
					<li><c:out value = "${ message }" /><br>
				</c:forEach>
			</ul>
		</div>
		<c:remove var = "messages" scope = "session" />
	</c:if>

	<div class = "input-text">
		<form action = "login" method = "post">
			<br />
			<p>
				<label for = "loginId">ログインID</label>
			</p>
			<input name = "loginId" value = "${ loginId }" /><br />

			<p>
				<label for = "password">パスワード</label>
			</p>
			<input name = "password" type = "password"  /><br />

			<p>
				<input type = "submit" value = "ログイン" /><br />
			</p>
		</form>
		<c:remove var = "loginId" scope = "session" />
	</div>
	
	<div class = "copyright">Copyright(c)Moriyama Naoki</div>
	
</body>
</html>
