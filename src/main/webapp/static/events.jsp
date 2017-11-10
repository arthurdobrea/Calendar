<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <title>All events</title>
</head>
<body>
<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/createEvent" class="btn">Create new event</a>
<a href="/userPage" class="btn">User Page</a>
<a href="/events" class="btn">All events</a>
<a href="/logout" class="btn">Logout</a>

<form method="POST" action="${contextPath}" class="form-signin">
    <input name="filterByKeyword" type="text" class="form-control" placeholder="Filter by keyword" autofocus="true"/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
</form>

<h2>Events:</h2>
<c:forEach items="${events}" var="event">
    <p>Name: ${event.eventName} | Type of event: ${event.eventType.view()} |
        <a href="/participants/${event.eventName}"> participants</a>
    </p>
    <p> <c:forEach items="${event.getParticipants()}" var="participant">
        <p>${participant.username}</p>
    </p>
    </c:forEach>
    <%-- Output tags of event--%>
    <p>Tag:
    <c:forEach items="${event.tags}" var="tag">
        | ${tag.tag} |
    </c:forEach>
    </p>
    <br>
</c:forEach>
</body>
</html>
