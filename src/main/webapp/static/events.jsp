<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <title>All events</title>
</head>
<body>
<link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/createEvent" class="btn">Create new event</a>
<a href="/events" class="btn">All events</a>
<a href="/logout" class="btn">Logout</a>
<p><p>
<h2>Events:</h2>
<c:forEach items="${events}" var="event">
    <p>Name: ${event.eventName} | Type of event: ${event.eventType}
       <a href="/updateEvent?eventId=${event.id}" class="btn">Update</a>
        <a href="/deleteEvent?eventId=${event.id}" class="btn">Delete</a>

    </p>
</c:forEach>
</body>
</html>
