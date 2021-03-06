<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>

<c:import url="header.jsp"/>

<a href="/welcome" class="btn_calendar">Home</a>
<a href="/index" class="btn_calendar">Calendar</a>
<a href="/userControlPanel" class="btn_calendar">User Panel</a>
<a href="/createEvent" class="btn_calendar">Create new event</a>
<a href="/events" class="btn_calendar">All events</a>
<a href="/logout" class="btn_calendar">Logout</a>

<h2>Events by tag:</h2>
Fullname
<c:forEach items="${fullname}" var="user">
    ${user.fullName}<br>
</c:forEach>

<c:forEach items="${tags}" var="tag">
    <p>Tag: ${tag.tag} |
        <a href="/updateTag?tagId=${tag.id}"> update</a>|
        <a href="/delete/${tag.id}"> delete me!</a>
    </p>
    <c:forEach items="${tag.events}" var="event">
        ${event.title}<br>
    </c:forEach>
</c:forEach>

<h2>User By Meeting:</h2>
<c:forEach items="${UserEvents}" var="user">
    ${user.username}<br>
</c:forEach>

<h2>User By Tag:</h2>
<c:forEach items="${UserTags}" var="user">
    ${user.username}<br>
</c:forEach>
<a href="/create-tag" class="btn">Create new tag</a>
</body>
</html>
