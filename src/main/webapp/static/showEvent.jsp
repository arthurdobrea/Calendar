<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ShowEvent</title>
    <script src="/resources/js/jquery.min.js"></script>
</head>
<body>
<h2>Event details:</h2>

                <form:form method="POST" modelAttribute="eventForm" class="form-signin">
                    <h2 class="form-signin-heading"></h2>

                    <spring:bind path="id">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="hidden" path="id" class="form-control eventId" placeholder="Id of event"
                                        autofocus="true"></form:input>
                        </div>
                    </spring:bind>
                </form:form>

                <p>
                    Name: ${eventForm.title} <br>
                    Type: ${eventForm.eventType}<br>
                    Location: ${eventForm.location}<br>
                    Start time: ${eventForm.start}<br>
                    End time: ${eventForm.end}<br>
                    Description:${eventForm.description}<br>
                    Created at: ${eventForm.eventCreated}<br>
                    <%--Created by: ${eventForm.author.fullName}<br>--%>
                    <%--Will be attended by:<br>--%>
                <ul id="participantsList"></ul>

                <script>
                        $.get("/getParticipantsByEvent", {eventId: $(".eventId").attr("value")}, function(data) {
                            console.log(data);

                            $.each(data, function(i, user) {
                                $("#participantsList").append('<li>' + user.firstname + " " + user.lastname + "</li>");
                            });
                            $(document).ready(function(){
                        });
                    });
                </script>

                <form>
                    <input type="button" value="Back to User Page"
                           onclick="window.location.href='/userPage'" />
                </form>

</body>
</html>

