<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Турагенство</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

	<div id="wrapper">
		<div class="container">
		<h1>Добро пожаловать в турагенство</h1>
		<h3>
		   <c:if test="${not empty notActivated and notActivated eq 'true'}">
		      Аккаунт не активирован!
		   </c:if>
		   <c:if test="${not empty notExists and notExists eq 'true'}">
			   Пользователь не зарегистрирован в системе!
		   </c:if>
		</h3>
		<form action="check-login" method="post">
			<table style="margin: auto">
				<tr>
					<td style="text-align: left">E-mail:</td>
					<td><input name="email" type="email" size="24" required /></td>
				</tr>
				<tr>
					<td style="text-align: left">Пароль:</td>
					<td><input name="password" type="password" size="24" maxlength="24" required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-accept" name="userLogin" value="Пользователь" /></td>
					<td><input type="submit" class="button-accept" name="adminLogin" value="Админ" /></td>
				</tr>
			</table>
		</form>

		<form action="registration" method="get">
			<input type="submit" class="button-register"
			name="register" value="Регистрация" />
		</form>
		</div>
	</div>
</body>
</html>