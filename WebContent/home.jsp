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
	<link href="CSS/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
	<c:if test="${ not empty loginUser }">
		<FONT size="5"><c:out value="${loginUser.account}" />
		がログイン中</FONT>
		<a href="posting">新規投稿</a>
		<c:if test="${loginUser.positionId == 1}" >
			<a href="management">ユーザー管理</a>
		</c:if>
		<a href="logout">ログアウト</a>
	</c:if>
</div>

<br>
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
	<div class="errorMessages">
		<ul>
			<c:forEach items="${deleteMessages}" var="message">
				<c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="deleteMessages" scope="session"/>
</c:if>


<c:if test="${ not empty successMessages }">
	<div class="errorMessages">
		<ul>
			
		<c:out value="${successMessages}" />
		</ul>
	</div>
	<c:remove var="successMessages" scope="session"/>
</c:if>
<form action="home" method="get">
<Div Align="left">カテゴリー:

<select name="category">
	<option value="0"> 全てを表示</option>
	<c:forEach items="${category}" var="category">
		<option> <c:out value="${category}" /></option>
	</c:forEach>
</select></Div>
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
				</select>日 から

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
				</select>日
			</td>
		</tr>
	</table>
<div class = "botton"><input type="submit" value="検索"></div>
</form>
<br>
<div class="home">
	<c:forEach items="${postings}" var="posting">
	<div style="border-style: solid ; border-width: 1px;">
		<div class="top">
			<Div Align="left">カテゴリー:<c:out value="${posting.category}" />
			投稿者:<c:out value="${posting.account}" />
			件名:<c:out value="${posting.subject}" /></div>
			<Div Align="left">日付:<fmt:formatDate value="${posting.date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<br>
			<Div Align="left" class = big-text>本文<br><c:out value="${posting.body}" /></div>
			
			<div class="home">
			<br>
			<c:forEach items="${comments}" var="comment">
				<c:choose>
					<c:when test = "${comment.postingId == posting.id}">
						<Div Align="left">名前:<c:out value="${comment.account}" /></div>
						<Div Align="left">&nbsp; コメント:<c:out value="${comment.body}" /></div>
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
				<textarea name="comment"  class="comment-box"></textarea><br>
				<Div Align="center">コメント(500文字以下)<input type="submit" value="コメント"></Div>
			</form>
		</div>
		</div>
		<br>
	</c:forEach>
</div>

<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>
