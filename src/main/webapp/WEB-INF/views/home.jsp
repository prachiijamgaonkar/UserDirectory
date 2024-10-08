
<%@page import="com.edu.myapp.dto.Role"%>
<%@page import="com.edu.myapp.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 20px;
}

nav {
	background-color: #007bff;
	padding: 15px;
	border-radius: 5px;
	display: flex;
	justify-content: space-around;
}

nav a {
	color: white;
	text-decoration: none;
	padding: 10px 15px;
	border-radius: 5px;
	transition: background-color 0.3s;
}

nav a:hover {
	background-color: #0056b3;
}

h3 {
	color: red;
	text-align: center;
}

p {
	width: 600px;
	font-size: 20px;
	text-align: justify;
	font-family: cursive;
	font-style: italic;
}

h1 {
	margin-top: 100px;
}
</style>
</head>
<body>
	<%
	UserDTO user = (UserDTO) request.getAttribute("user");
	if (user.getRole().equals(Role.USER)) {
	%>
	<nav>
		<a href="edit-user">EDIT PROFILE</a> <a href="logout">LOGOUT</a> <a
			href="delete-user">DELETE ACCOUNT</a>
	</nav>
	<div align="center">
		<h1>WELCOME TO HOME PAGE</h1>
		<p>web based application which  enables seamless user management with
			functionalities to add, update, and delete user accounts.
			Designed with an intuitive interface, it simplifies user interactions
			and data management for optimal efficiency.</p>
	</div>
	<%
	} else {
	%>
	<nav>
		<a href="edit-user">EDIT PROFILE</a> <a href="logout">LOGOUT</a> <a
			href="delete-user">DELETE ACCOUNT</a> <a
			href="users">ALL USERS</a>
	</nav>
	<div align="center">
		<h1>WELCOME TO MY PAGE</h1>
		<p>web based application which  enables seamless user management with
			functionalities to add, update, and delete user accounts.
			Designed with an intuitive interface, it simplifies user interactions
			and data management for optimal efficiency..</p>
	</div>
	<%
	}
	%>
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<h3><%=message%></h3>
	<%
	}
	%>
</body>
</html>
