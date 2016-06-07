<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>掲示板</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>



<div class="header">
	<c:if test="${ not empty loginUser }">
		<c:out value="${loginUser.account}" />
		がログイン中
		<a href="posting">新規投稿</a>
		<c:if test="${loginUser.positionId == 1}" >
			<a href="management">ユーザー管理</a>
		</c:if>
		<a href="logout">ログアウト</a>
	</c:if>
</div>
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

<c:if test="${ not empty deleteMessages }">
	<div class="deleteMessages">
		<ul>
			<c:forEach items="${deleteMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="search" method="get">
カテゴリー:<textarea name="category" cols="6" rows="1"></textarea>
 <table>
 	<tr>
		<th>日付</th>
			<td>
				<select name="toYear">
					<c:forEach begin="${firstYear }" end="${lastYear }" var="i">
						<option <c:if test="${i == lastYear }">selected</c:if>>
						<c:out value="${i}" /></option>
					</c:forEach>
				</select>年
				
				<select name="toMonth">
					<c:forEach begin="1" end="12" var="i">
						<option <c:if test="${i == lastMonth }">selected</c:if>>
						<c:out value="${i}" /></option>
					</c:forEach>
				</select>月
				
				<select name="toDay">
					<c:forEach begin="1" end="31" var="i">
						<option <c:if test="${i == lastDay }">selected</c:if>>
						<c:out value="${i}" /></option>
					</c:forEach>
				</select>日 ～～

				<select name="fromYear">
					<c:forEach begin="${firstYear }" end="${lastYear }" var="i">
						<option <c:if test="${i == lastYear }">selected</c:if>>
						<c:out value="${i + currentYear}" /></option>
					</c:forEach>
				</select>年
				
				<select name="fromMonth">
					<c:forEach begin="1" end="12" var="i">
						<option <c:if test="${i == firstMonth }">selected</c:if>>
						<c:out value="${i}" /></option>
					</c:forEach>
				</select>月
				
				<select name="fromDay">
					<c:forEach begin="1" end="31" var="i">
						<option <c:if test="${i == firstDay }">selected</c:if>>
						<c:out value="${i}" /></option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
<input type="submit" value="検索">
</form>

<div class="top">
	<c:forEach items="${postings}" var="posting">
		<div class="top">
			件名:<c:out value="${posting.subject}" /><span style="margin-right: 24em;"></span>
			         カテゴリー:<c:out value="${posting.category}" /></div>
			
			投稿者:<c:out value="${posting.account}" /><span style="margin-right: 24em;"></span>
			日付:<fmt:formatDate value="${posting.date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			
			<u><div class="body">本文:<c:out value="${posting.body}" /></div></u>
			
			<div class="top">
			<br>
			<c:forEach items="${comments}" var="comment">
				<c:choose>
					<c:when test = "${comment.postingId == posting.id}">
						<div class="top">名前:<c:out value="${comment.account}" /></div>
						<div class="top">コメント:<c:out value="${comment.body}" /></div>
						<br>
					</c:when>
				</c:choose>
			</c:forEach>
			</div>
			<form action="delete" method="post">
			<input type = "hidden" name = "id" value = "${posting.id}">
			<c:if test="${ loginUser.branchId != 1 && loginUser.positionId == 3 && loginUser.branchId == posting.branchId}">				
				<input type="submit" value="削除">
			</c:if>
			<c:if test="${ loginUser.positionId == 2 }">
				<input type="submit" value="削除">
			</c:if>
			</form>
			

			<form action="comment" method="post">
				<input type = "hidden" name = postingId value = "${posting.id}">
				<textarea name="comment" cols="1" rows="3" class="comment-box"></textarea>
				<input type="submit" value="コメント">
			</form>
		</div>
		
	</c:forEach>
</div>

<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>
