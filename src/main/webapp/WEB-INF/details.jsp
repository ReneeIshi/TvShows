<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TV Show Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- <link rel="stylesheet" href="/css/main.css"/> -->
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class = "container">
		<a class="d-flex justify-content-end" href="/shows">Back to Dashboard</a>
		<h1>${show.title}</h1>
		<p>Posted by:${show.user.userName}</p>
		<p>Network ${show.network}</p>
		<p>Description: ${show.description}</p>
		
		<c:if test="${show.user.id==userLoggedIn.id }">
			<a href="/edit/${id}">Edit</a>
		</c:if>
	</div>

</body>
</html>