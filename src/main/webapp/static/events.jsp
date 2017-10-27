<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Events:</h2>
<c:forEach items="${events}" var="event">
    <p>Name: ${event.eventName} | Type of event: ${event.event_type}</p>
</c:forEach>
</body>
</html>
