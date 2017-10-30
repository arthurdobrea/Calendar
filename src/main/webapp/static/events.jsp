<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All events</title>
</head>
<body>
<h2>Events:</h2>

<c:forEach items="${events}" var="event">
    <form:form method="POST" modelAttribute="eventForm" class="form-signin">
    <p>Name: ${event.eventName} | Type of event: ${event.eventType}
        <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
        </p>
    </form:form>
</c:forEach>
</body>
</html>
