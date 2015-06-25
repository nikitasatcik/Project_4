<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Особистий кабінет</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div style="text-align: right">
    <a class="link-cancel" href="logout">Вийти</a>
</div>
<div id="wrapper">
    <div class="container">
        <h3>С возвращением, ${sessionScope.user.name}!</h3>
        <form action="find-tour" method="post">
            <input type="submit" class="button-accept" name="findTour" value="Найти тур"/>
        </form>
    </div>
</div>
</body>
</html>