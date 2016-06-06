<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
<!--

function check(){
	if(window.confirm('実行してよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は送信を実行
	}
	else{ // 「キャンセル」時の処理
		return false; // 送信を中止
	}

}
-->
</script>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:if test="${ loginUser.branchId >= 10 }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="header">
	<c:if test="${ not empty loginUser }">
		<a href="signup">ユーザー登録</a>
		<a href="home">戻る</a>
	</c:if>
</div>
<table>
  <tr>
    <td>ログインID</td>
    <td>アカウント</td>
    <td>支店</td>
    <td>部署・役職</td>
    <td>状態</td>
    <td>削除</td>
    <td>編集</td>
  </tr>

	
	<c:forEach items="${users}" var="user">
		<tr>
			<td><div class="loginId"><c:out value="${user.loginId}" /></div></td>
			<td><div class="account"><c:out value="${user.account}" /></div></td>
			<td><div class="branchId"><c:out value="${user.branchName}" /></div></td>
			<td><div class="positionId"><c:out value="${user.positionName}" /></div></td>
			<td><form action="management" method="post" onClick="return check()">
			<input type = "hidden" name = "loginId" value = "${user.loginId}"/>
			<c:if test="${ user.status == 1 }">
				<p><input type="submit" value="停止中" ></p> 
				<input type = "hidden" name = "status" value = 2>
			</c:if>
			<c:if test="${ user.status == 2 }">
				<p><input type="submit" value="利用可能" ></p>
				<input type = "hidden" name = "status" value = 1>
			</c:if>
			</form></td>
			<td><form action="deleteUser" method="post" onClick="return check()"> 
				<input type = "hidden" name = "loginId" value = "${user.loginId}">
				<p><input  type="submit" value="削除"></p>
			</form></td>
				
			<td><form action="setting" method="get"> 
				<input type = "hidden" name = "id" value = "${user.id}">
				<p><input  type="submit" value="編集"></p>
			</form></td>
					
		</tr>
	</c:forEach>
</table>
<div class="copyright">Copyright(c)Moriyama Naoki</div>
</body>
</html>