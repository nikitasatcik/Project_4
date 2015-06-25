<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Регистрация</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div style="text-align: right">
	<a class="link-cancel" href="logout">Вийти</a>
</div>
	<div id="wrapper">
		<div class="container">
		<h1>Регистрация</h1>
		<h3>
			<c:if test="${not empty passwordNotMatch and passwordNotMatch eq 'true'}">
		      Пароли не совпадают
		    </c:if>
		    <c:if test="${not empty userNotCreated and userNotCreated eq 'true'}">
		       Пользователь уже зарегистрирован
		    </c:if>
		</h3>
		<form action="check-registration" method="post">
			<table style="margin: auto; text-align: left">
				<tr>
					<td>E-mail:</td>
					<td><input name="email" value="${param.email}" type="email"
					size="35" required /></td>
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input name="password" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Подтверждение пароля:</td>
					<td><input name="passwordConfirm" type="password" size="35"
						maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Фамилия:</td>
					<td><input name="surname" value="${param.surname}"
					type="text" size="35" maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Имя:</td>
					<td><input name="name" value="${param.name}"
					type="text" size="35" maxlength="35" required /></td>
				</tr>
				<tr>
					<td>Телефон:</td>
					<td><input name="phone" value="${param.phone}"
					type="tel" pattern='\d{12}' size="12" maxlength="12"
					required /></td>
				</tr>
			</table>
			<table style="margin: auto">
				<tr>
					<td><input type="submit" class="button-create" name="create" value="Зарегистрировать" /></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>