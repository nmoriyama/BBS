<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿</title>
	<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<c:if test="${ not empty messages }">
	<div class="messages">
		<ul>
			<c:forEach items="${messages}" var="message">
				<li><c:out value="${message}" /><br>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="messages" scope="session"/>
</c:if>


<form action="posting" method="post">
	<input type = "hidden" name = "id" value = "${loginUser.id}">
	<p><label for = "subject">件名(50文字以下)</label></p>
	<input name = "subject" value="${subject}" /><br /><br />
		
	<p><label for = "category">カテゴリー(10文字以下)</label></p>
	<input name = "category" value="${category}" /><br /><br />
			
	<p><label for = "body">本文(1000文字以下)</label></p>
	<textarea name="body"  class ="input-box" id = "body"><c:out value="${body}" /></textarea><br />
			
	<p><input type="submit" value="投稿" ></p>
</form>
		
<a href="./home">戻る</a>
<c:remove var="subject" scope="session"/>
<c:remove var="body" scope="session"/>
<c:remove var="category" scope="session"/>
<div class="copyright">Copyright(c)Moriyama Naoki</div>


</body>
</html>