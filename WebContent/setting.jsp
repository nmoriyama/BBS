<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>設定</title>
	<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>


<form action="setting" method="post" enctype="multipart/form-data"><br />
	<input type = "hidden" name = OldLoginId value = "${UpdateLoginId}">
	<label for="loginId">ログインID(6文字以上20文字以下)</label>
	<input name="loginId" id="loginId" value = "${UpdateUser.loginId }"/> <br />
	
	<label for="password">パスワード(6文字以上255文字以下)</label>
	<input name="password" type="password" id="password"/> <br />
	
	<label for="password">パスワード（確認用）</label>
	<input name="checkPassword" type="password"/> <br />
	
	<label for="account">アカウント名</label>
	<input name="account" id="account" value = "${UpdateUser.account }"/> <br />
	
	<p><label>支店：
	<select name="branchId">
		<option value="1" <c:if test="${UpdateUser.branchId == 1}">selected</c:if>>本社</option>
		<option value="2" <c:if test="${UpdateUser.branchId == 2}">selected</c:if>>支店Ａ</option>
		<option value="3" <c:if test="${UpdateUser.branchId == 3}">selected</c:if>>支店Ｂ</option>
		<option value="4" <c:if test="${UpdateUser.branchId == 4}">selected</c:if>>支店Ｃ</option>
	</select> 
	</label></p>
	
	<p><label>役職：
	<select name="positionId">
		<option value="1" <c:if test="${UpdateUser.positionId == 1}">selected</c:if>>人事総務部</option>
		<option value="2" <c:if test="${UpdateUser.positionId == 2}">selected</c:if>>情報セキュリティ部</option>
		<option value="3" <c:if test="${UpdateUser.positionId == 3}">selected</c:if>>支店長</option>
		<option value="4" <c:if test="${UpdateUser.positionId == 4}">selected</c:if>>社員</option>
	</select>
	</label></p>
	
	<input type="submit" value="登録" /> <br />
	<a href="./management">戻る</a>
</form>
<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>
