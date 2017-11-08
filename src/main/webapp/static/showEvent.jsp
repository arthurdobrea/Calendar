<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ShowEvent</title>
</head>
<body>
<h2>Event details:</h2>

<form:form method="POST" modelAttribute="eventForm" class="form-signin">
    <h2 class="form-signin-heading"></h2>

    <spring:bind path="id">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="hidden" path="id" class="form-control" placeholder="Id of event"
                        autofocus="true"></form:input>
        </div>
    </spring:bind>
</form:form>

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
