<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration and Login</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- <link rel="stylesheet" href="/css/main.css"/> -->
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class = "container">
		<h1 class="text-info">TV Shows Database</h1>
		<div class="row">
			<!-- The start of the registration form -->
			<div class="col"> <!-- Placing in it's own column -->
			<h1> Register </h1>
			<form:form method="POST" action="/register" modelAttribute="newUser">
				<div class="form-group">
					<form:label path="userName">Name:</form:label>
					<form:errors class="text-danger" path="userName"/>
					<form:input class="form-control" path="userName"/>
				</div>
				<div class="form-group">
					<form:label path="email">Email:</form:label>
					<form:errors class="text-danger" path="email"/>
					<form:input class="form-control" path="email"/>
				</div>
				<div class="form-group">
					<form:label path="password">Password:</form:label>
					<form:errors class="text-danger" path="password"/>
					<form:password class="form-control" path="password"/>
				</div>
				<div class="form-group">
					<form:label path="confirm">Confirm Password:</form:label>
					<form:errors class="text-danger" path="confirm"/>
					<form:password class="form-control" path="confirm"/>
				</div>
				<button class = "btn btn-primary float-end m-2">Register</button>
			
			</form:form>
			
			</div>
			
			<!-- Start of the Login Form -->
			<div class="col">
			<h1>Login</h1>
			<form:form action="/login" modelAttribute="newLogin">
				<div class="form-row">
					<form:errors path="email" class="text-danger"/>
					<form:label path="email">Email:</form:label>
					<form:input type="text" path="email" class="form-control"/>					
				</div>
				<div class="form-row">
					<form:errors path="password" class="text-danger"/>
					<form:label path="password">Password:</form:label>
					<form:password path="password" class="form-control"/>					
				</div>
				<button class = "btn btn-primary float-end m-2">Login</button>
			</form:form>
			</div>
	
		</div>
	</div>

</body>
</html>