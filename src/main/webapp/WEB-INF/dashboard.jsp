<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TV Shows Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- <link rel="stylesheet" href="/css/main.css"/> -->
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class = "container">
		<a class="d-flex justify-content-end" href="/logout">logout</a>
		<h1>Welcome, ${userLoggedIn.userName}</h1>
		<p>TV Shows</p>
		<table class="table table-sm table-bordered table-striped">
			<tr>
				<th>Show</th>
				<th>Network</th>
				<th>Posted By</th>
			</tr>
			
			<c:forEach items="${allShows}" var="show"> <!-- coming from the controller file -->
				<tr>
					<td><a href="/shows/${show.id}">${show.title}</a></td>
					<td>${show.network}</td>
					<td>${show.user.userName}</td>
				</tr>
			
			</c:forEach>
		</table>
		<form:form method="POST" action="/shows/add" modelAttribute="addShow">
			<input type="submit" class="btn btn-primary" value="Add a Show"/>
		</form:form>
	</div>

</body>
</html>