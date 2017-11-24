<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description">
    <meta name="author">

    <title>Events</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <title>All events</title>
</head>
<body>
<a href="/welcome" class="btn_calendar">Home</a>
<a href="/index" class="btn_calendar">Calendar</a>
<a href="/events" class="btn_calendar">All events</a>
<a href="/userPage" class="btn_calendar">User Page</a>
<a href="/userControlPanel" class="btn_calendar">User Panel</a>
<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
    <a href="/admin" class="btn_calendar">Admin page</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
    <a href="/admin" class="btn_calendar">Admin page</a>
</c:if>
<a href="/logout" class="btn_calendar">Logout</a>

<form method="POST" action="${contextPath}" class="form-signin">
    <input name="filterByKeyword" type="text" class="form-control" placeholder="Filter by keyword" autofocus="true"/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form>

<h2>Events:</h2>
<table class="table table-hover">
    <tr>
        <th>Event</th>
        <th>Type</th>
        <th>Author</th>
        <th>Participants</th>
        <th>Tags</th>
    </tr>
        <c:forEach items="${events}" var="event">
    <tr>
        <td>${event.title}</td>
        <td>${event.eventType.view()}</td>
        <td>${event.author.fullName}</td>
        <td><c:forEach items="${event.getParticipants()}" var="participant">
            <p>${participant.username}</p>
            </c:forEach></td>
    </tr>
    <td><c:forEach items="${event.tags}" var="tag">
        | ${tag.tag} |
    </c:forEach></td>
        </c:forEach>
</table>
</body>
</html>
