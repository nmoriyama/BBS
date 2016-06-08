<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${UpdateUser.account }の設定</title>
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

<div class = "input-text">
	<form action="setting" method="post" enctype="multipart/form-data"><br />
		<input type = "hidden" name = id value = "${UpdateUser.id}">
		<p><label for="loginId">ログインID(6文字以上20文字以下)<br></label>
		<input name="loginId" id="loginId" value = "${UpdateUser.loginId }"/><br></p>
	
		<p><label for="password">パスワード(6文字以上255文字以下)<br></label>
		<input name="password" type="password" id="password"/><br></p>
	
		<p><label for="password">パスワード(確認用)<br></label>
		<input name="checkPassword" type="password"/><br></p>
	
		<p><label for="account">アカウント名<br></label>
		<input name="account" id="account" value = "${UpdateUser.account }"/><br></p>
			
		<c:if test="${ UpdateUser.id != loginUser.id }">
			<p><label>支店<br>
				<select name="branchId">
					<c:forEach items = "${ branches }" var = "branch">
						<option value = "${ branch.id }" <c:if test = "${ UpdateUser.branchId == branch.id }">selected</c:if>>
						<c:out value = "${ branch.name }" /></option>
					</c:forEach>
				</select>
			</label></p>
		</c:if>

		<c:if test="${ UpdateUser.id != loginUser.id }">
			<p><label>役職<br>
				<select name="positionId">
					<c:forEach items = "${ positons }" var = "position">
						<option value = "${ position.id }" <c:if test = "${ UpdateUser.positionId == position.id }">selected</c:if>>
						<c:out value = "${ position.name }" /></option>
					</c:forEach>
				</select>
			</label></p>
		</c:if>
	
		<input type="submit" value="登録" /> <br />
		<a href="./management">戻る</a>
	</form>
	<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>
