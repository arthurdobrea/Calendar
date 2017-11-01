<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowEvent</title>
</head>
<body>
<h2>You have created the following event:</h2>

    <p>Name: ${eventForm.eventName} <br>
        Type: ${eventForm.eventType}<br>
        Location: ${eventForm.location}<br>
        Start time: ${eventForm.startTime}<br>
        End time: ${eventForm.endTime}<br>
        Description:${eventForm.description}<br>
        Created at: ${eventForm.eventCreated}<br>
        Created by: ${eventForm.author.fullName}<br>
        Will be attended by:<br>
        <c:forEach items="${eventForm.participants}" var="user">
            <p>Name: ${user.fullName} </p>
        </c:forEach>



    </p>

</body>
</html>
