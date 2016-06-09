<%@ page language = "java" contentType = "text/html; charset  =  UTF-8"
	pageEncoding = "UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.io.*"%>
<%@ page isELIgnored = "false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = UTF-8">
<title>掲示板</title>
<link href = "CSS/style.css" rel = "stylesheet" type = "text/css">
</head>

<body>

	<div class = "head-text">
		<c:if test = "${ not empty loginUser }">
			<h2>
				<c:out value = "${ loginUser.account }" />
				がログイン中
			</h2>
			<a href = "posting">新規投稿</a>
			<c:if test = "${ loginUser.positionId == 1 }">
				<a href = "management">ユーザー管理</a>
			</c:if>
			<a href = "logout">ログアウト</a>
		</c:if>
	</div>
	<br>

	<c:if test = "${ not empty messages }">
		<div class = "messages">
			<ul>
				<c:forEach items = "${ messages }" var = "message">
					<li><c:out value = "${ message }" /><br>
				</c:forEach>
			</ul>
		</div>
		<c:remove var = "messages" scope = "session" />
	</c:if>

	<form action = "home" method = "get">
	
	<div Align = "left">
		カテゴリー<br> <select name = "category">
			<option value = "">全てを表示</option>
			<c:forEach items = "${ category }" var = "category">
				<option <c:if test = "${ SearchCategory == category }">selected</c:if>>
				<c:out value = "${ category }" /></option>
			</c:forEach>
		</select>
	</div>
	
	<div Align = "left">日付<br></div>
		<select name="fromYear">
			<c:forEach begin = "${ firstYear }" end = "${ lastYear }" var = "i">
				<option <c:if test = "${ i == firstYear }">selected</c:if>>
					<c:out value = "${ i }" /></option>
			</c:forEach>
		</select>年 <select name="fromMonth">
			<c:forEach begin = "1" end = "12" var = "i">
				<option <c:if test = "${ i == firstMonth }">selected</c:if>>
					<c:out value = "${ i }" /></option>
			</c:forEach>
		</select>月 <select name="fromDay">
			<c:forEach begin = "1" end = "31" var = "i">
				<option <c:if test = "${ i == firstDay }">selected</c:if>>
					<c:out value = "${ i }" /></option>
			</c:forEach>
		</select>日 から 
		
		<select name = "toYear">
			<c:forEach begin = "${ firstYear }" end = "${ lastYear }" var = "i">
				<option <c:if test = "${ i == lastYear }">selected</c:if>>
					<c:out value = "${ i + currentYear }" /></option>
			</c:forEach>
		</select>年 <select name = "toMonth">
			<c:forEach begin = "1" end = "12" var = "i">
				<option <c:if test = "${ i == lastMonth }">selected</c:if>>
					<c:out value = "${ i }" /></option>
			</c:forEach>
		</select>月 <select name = "toDay">
			<c:forEach begin = "1" end = "31" var = "i">
				<option <c:if test = "${ i == lastDay }">selected</c:if>>
					<c:out value = "${ i }" /></option>
			</c:forEach>
		</select>日
		<div class = "botton"><input type = "submit" value = "検索"></div>
	</form><br>

	<c:if test = "${ empty postings }">該当する記事は0件でした</c:if>
	
	<c:forEach items = "${ postings }" var = "posting">
		<div class = "body-text">
			<div Align = "left">
				カテゴリー:
				<c:out value = "${ posting.category }" />
				&nbsp; 投稿者:
				<c:out value = "${ posting.account }" />
				&nbsp; 件名:
				<c:out value = "${ posting.subject }" />
			</div>
			<div Align = "left">
				日付:<fmt:formatDate value = "${ posting.date }" pattern = "yyyy年MM月dd日 HH時mm分" />
			</div>
			<br>
			<div Align = "left" class = big-text>
				本文<br>
				<c:forEach items = "${ fn: split(posting.body,'<br>') }" var = "body">
					&nbsp;<c:out value = "${body}" />
					<br>
				</c:forEach>
			</div>
			<form action = "deletePosting" method = "post">
				<input type = "hidden" name = "id" value = "${ posting.id }">
				<c:if test = "${ loginUser.branchId != 1 && loginUser.positionId == 3 && loginUser.branchId == posting.branchId }">
					<input type = "submit" value = "削除">
				</c:if>
				<c:if test = "${ loginUser.positionId == 2 }">
					<input type = "submit" value = "削除">
				</c:if>
			</form>
		</div>
		<br>
		
		<div class = "comment-text"> 
			<c:forEach items = "${ comments }" var = "comment">
				<c:choose>
					<c:when test = "${ comment.postingId == posting.id }">
						<div Align = "left">
							名前:<c:out value = "${ comment.account }" />
							&nbsp; コメント<br>
							<c:forEach items = "${ fn: split(comment.body, '<br>') }" var = "body">
								<div class = "comments">
								&nbsp;<c:out value = "${body}" /><br>
								</div>
							</c:forEach>
						</div>
						<form action = "deleteComment" method = "post">
							<input type = "hidden" name = "id" value = "${ comment.id }">
							<c:if test = "${ loginUser.branchId !=  1 && loginUser.positionId == 3 && loginUser.branchId == comment.branchId }">
								<input type = "submit" value = "削除">
							</c:if>
							<c:if test = "${ loginUser.positionId == 2 }">
								<input type = "submit" value = "削除">
							</c:if>
						</form><br>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>

		<form action = "comment" method = "post">
			<div Align = "left">
				コメント(500文字以下)<br><input type = "hidden" name = postingId value = "${ posting.id }">
				<textarea name = "comment" class = "comment-box"></textarea>
				<br><input type = "submit" value = "コメント">
			</div>
		</form><br>
	</c:forEach>
	<div class = "copyright">Copyright(c)Moriyama Naoki</div>
	</body>
</html>
