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

<div class="head-text">
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

<form action="home" method="get">
<Div Align="left">カテゴリー:

<select name="category">
	<option value="0"> 全てを表示</option>
	<c:forEach items="${category}" var="category">
		<option <c:if test="${SearchCategory == category }">selected</c:if>> <c:out value="${category}" /></option>
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
	<c:if test="${empty postings }">該当する記事は0件でした。</c:if>
	<c:forEach items="${postings}" var="posting">
	<div style="border-style: double; border-width: 5px;">
		<div Align="left">カテゴリー:<c:out value="${posting.category}" />
		投稿者:<c:out value="${posting.account}" />
		件名:<c:out value="${posting.subject}" /></div>
		<div Align="left">日付:<fmt:formatDate value="${posting.date}" pattern="yyyy年MM月dd日 HH時mm分" /></div>
		<br>
		<div Align="left" class = big-text>本文<br><c:out value="${posting.body}" /></div>
	</div>
	<br>
	<div style="border-style: solid ; border-width: 1px;">
		<c:forEach items="${comments}" var="comment">
			<c:choose>
				<c:when test = "${comment.postingId == posting.id}">
					<div Align="left">名前:<c:out value="${comment.account}" /></div>
					<div Align="left">&nbsp; コメント:<c:out value="${comment.body}" /></div>
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
		<div Align="center">コメント(500文字以下)<input type="submit" value="コメント"></div>
	</form>
		<br>
	</c:forEach>
</div>

<div class="copyright">Copyright(c)Moriyama Naoki</div>
</div>
</body>
</html>
