<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Горящие туры</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div style="text-align: right">
    <a class="link-cancel" href="logout">Выйти</a>
</div>
<div id="wrapper">
    <div class="container">
    <form action="create-invoice" method="post">

        <table style="margin: auto">
            <tr class="odd">
                <th>Выбор тура</th>
                <th>Код тура</th>
                <th>Код страны</th>
                <th>Название тура</th>
                <th>Описание тура</th>
            </tr>

            <c:forEach var="tour" varStatus="loopStatus" items="${tour}">
                <tr >
                    <td><input type="radio" name="" value="${tour.tour_id}"  /></td>
                    <td><c:out value="${tour.tour_id}"/></td>
                    <td><c:out value="${tour.country_id}"/></td>
                    <td><c:out value="${tour.tour_name}"/></td>
                    <td><c:out value="${tour.tour_description}"/></td>
                </tr>

            </c:forEach>
        </table>
    </form>
</div>
</div>
</body>
</html>