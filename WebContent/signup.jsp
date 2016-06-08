<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
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

<form action="signup" method="post"><br>
	<p><label for="loginId">ログインID(6文字以上20文字以下)<br></label>
	<input name="loginId" value="${loginId}" id ="loginId"/> <br> </p>
	
	<p><label for="password">パスワード(6文字以上255文字以下)<br></label>
	<input name="password" type="password"/> <br> </p>
	
	<p><label for="password">パスワード(確認用)<br></label>
	<input name="checkPassword" type="password"/> <br> </p>
	
	<p><label for="account">アカウント名<br></label>
	<input name="account" value="${account}"/> <br> </p>

	
	
	<p><label>支店<br>
	<select name="branchId">
		<c:forEach items = "${ branches }" var = "branch">
			<option value = "${ branch.id }" <c:if test = "${ branchId  ==  branch.id }">selected</c:if>>
			<c:out value = "${ branch.name }" /></option>
		</c:forEach>
	</select>
	</label></p>

	<p><label>役職<br>
	<select name="positionId">
		<c:forEach items = "${ positons }" var = "position">
			<option value = "${ position.id }" <c:if test = "${ positionId  ==  position.id }">selected</c:if>>
			<c:out value = "${ position.name }" /></option>
		</c:forEach>
	</select>
	</label></p>

	<input type="submit" value="登録" /> <br />
	<a href="./management">戻る</a>
	<c:remove var="loginId" scope="session"/>
	<c:remove var="account" scope="session"/>
	<c:remove var="branchId" scope="session"/>
	<c:remove var="positionId" scope="session"/>
	
</form>
<div class="copyright">Copyright(c)Moriyama Naoki</div>

</body>
</html>

