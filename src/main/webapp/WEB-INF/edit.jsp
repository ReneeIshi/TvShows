<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit a TV Show</title>
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
		<h1>Edit ${show.title} </h1>
		<div class="row">
			<!-- Start of the form to add a new show -->
			<div class="col">
			<form:form method="POST" action="/edit/${show.id}" modelAttribute="show">
				<input type="hidden" name="_method" value="put">
				<div class="form-group">
					<form:label path="title">Title</form:label>
					<form:errors class="text-danger" path="title"/>
					<form:input class="form-control" path="title"/>					
				</div>
				<div class="form-group">
					<form:label path="network">Network</form:label>
					<form:errors class="text-danger" path="network"/>
					<form:input class="form-control" path="network"/>					
				</div>
				<div class="form-group">
					<form:label path="description">Description</form:label>
					<form:errors class="text-danger" path="description"/>
					<form:input class="form-control" path="description"/>					
				</div>	
					<!-- field to capture the id but making it a hidden field -->		
					<form:input type="hidden" value="${userLoggedIn.id}" path="user"/>
					<input type="submit" class="btn btn-primary" value="Submit"/>
			</form:form>
			<form:form action="/delete/${id}" method = "delete">
				<button>Delete</button>			
			</form:form>
			</div>
		
		</div>
	
	</div>

</body>
</html>