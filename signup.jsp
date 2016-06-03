<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
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
<form action="signup" method="post"><br />
	<label for="loginId">ログインID</label>
	<input name="loginId" value="${insertUser.loginId}" id ="loginId"/> <br />
	
	<label for="password">パスワード</label>
	<input name="password" type="password"/> <br />
	
	<label for="password">パスワード（確認用）</label>
	<input name="checkPassword" type="password"/> <br />
	
	<label for="account">アカウント名</label>
	<input name="account" value="${insertUser.account}"/> <br />
	
	<p><label>支店：
	<select name="branchId">
		<option value="1">本社</option>
		<option value="2">支店Ａ</option>
		<option value="3">支店Ｂ</option>
		<option value="4">支店Ｃ</option>
	</select>
	</label></p>
	
	<p><label>役職：
	<select name="positionId">
		<option value="1">人事総務部</option>
		<option value="2">情報セキュリティ部</option>
		<option value="3">支店長</option>
		<option value="4">社員</option>
	</select>
	</label></p>

	<p><label>状態：
	<select name="status">
		<option value="1">停止中</option>
		<option value="2">活動中</option>
	</select>
	</label></p>
	
	<input type="submit" value="登録" /> <br />
	<a href="./top">戻る</a>
</form>
<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>

