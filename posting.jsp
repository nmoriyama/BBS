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
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>


<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="posting-contents">
<div class="posting">
	<c:if test="${ not empty loginUser }">
		<form action="posting" method="post">
			<input type = "hidden" name = "id" value = "${loginUser.id}">
			<p><label for = "subject">件名</label></p>
			<textarea name="subject" cols="1" rows="1" class="comment-box" id = "subject"></textarea>
			<br />
			<p><label for = "body">本文</label></p>
			<textarea name="body" cols="1" rows="5" class="comment-box"></textarea>
			<br />
			<p><label for = "category">カテゴリー</label></p>
			<textarea name="category" cols="1" rows="1" class="comment-box"></textarea>
			<br />
			<p><input type="submit" value="投稿" class ="loginText"></p>
		</form>
		<a href="./top">戻る</a>
	</c:if>
	</div>
</div>


</body>
</html>